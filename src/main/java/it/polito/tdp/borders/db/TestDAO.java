package it.polito.tdp.borders.db;

import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:\n...tutte le nazioni sono state stampate correttamente...");
		List<Country> countries = dao.loadAllCountries();
		/*
		for(Country c: countries) {
			System.out.println(""+c.toStringBello());
		}
		*/
		
		
		//Map<Integer, Border> confini = dao.getCountryPairs(1990);
		List<Border> confini = dao.getCountryPairs(1990);
		String elenco = "";
		for(Border b: confini) {
			elenco+=b +"\n";
		}
		System.out.println("\n\nLista dei confini nell'anno 1990:\n"+elenco);
		
	}
}