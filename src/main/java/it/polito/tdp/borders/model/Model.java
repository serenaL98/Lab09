package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.alg.connectivity.ConnectivityInspector;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private List<Border> confini;
	private Map<Integer, Country> idCountry;
	
	private ConnectivityInspector<Country, DefaultEdge> conn;
	
	//grafo non orientato e non pesato. Verici le nazioni, gli archi i confini
	private SimpleGraph<Country, DefaultEdge> grafo;

	public Model() {
		
		this.dao = new BordersDAO();
		
		this.idCountry = new HashMap<>();

	}
	
	//creo il grafo: ho bisogno dell'anno
	public void creaGrafo(int anno) {
		
		for(Country c: dao.loadAllCountries()) {
			idCountry.put(c.getCodice(), c);
		}
	
		confini = dao.getCountryPairs(anno);
		
		if(confini.isEmpty()) {
			throw new RuntimeException("Non ci sono confini per l'anno selezionato!");
		}
		
		//definisco il grafo
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungo i nodi: NAZIONI coinvolte
		for(Border b: confini) {
			
			Country aa = this.idCountry.get(b.getState1n());
			Country bb = this.idCountry.get(b.getState2n());
			
			if(!grafo.containsVertex(aa) || !grafo.containsVertex(bb)) {
				grafo.addVertex(aa);
				grafo.addVertex(bb);
			}
			
			
			//sggiungo gli archi fra le nazioni
			grafo.addEdge(aa, bb);
			
		}
		
	}

	//stampa Stati e il numero di Stati confinanti
	public String statiEconfini(){
		
		String lista = "";
		
		for(Country c: this.grafo.vertexSet()) {
			lista += c.getNome()+" "+grafo.degreeOf(c)+"\n";
		}
		
		return lista;
	}
	
	public int componenti() {
		
		if(grafo == null) {
			throw new RuntimeException("Non c'è nessun grafo!");
		}
		
		conn = new ConnectivityInspector<>(grafo);
		
		return conn.connectedSets().size();
	}
	
	public List<Country> getAllCountries() {
		return dao.loadAllCountries();
	}
	
	public List<Country> statiVicini(Country stato){
		
		List<Country> lista = new ArrayList<Country>();
		
		BreadthFirstIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<>(grafo, stato);
		
		while(visita.hasNext()) {
			lista.add(visita.next());
		}
		
		return lista;
	}
	
	public List<Country> statiProfondi(Country stato){
		List<Country> lista = new ArrayList<>();
		
		DepthFirstIterator<Country, DefaultEdge> visita = new DepthFirstIterator<Country, DefaultEdge>(grafo, stato);
		
		while(visita.hasNext()) {
			lista.add(visita.next());
		}
		return lista;
	}

	public SimpleGraph<Country, DefaultEdge> getGrafo() {
		return grafo;
	}
	
}
