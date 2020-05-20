package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				String abb = rs.getString("StateAbb");
				int cod = rs.getInt("ccode");
				String nome = rs.getString("StateNme");
				
				result.add(new Country(abb, cod, nome));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	//passato l'anno, prendi gli stati che si collegano fra loro
	public List<Border> getCountryPairs(int anno) {
		
		String sql = "SELECT c.state1no n1, c.state1ab s1, c.state2no n2, c.state2ab s2, year y" + 
				" FROM contiguity c" + 
				" WHERE c.year<= ? AND c.conttype= '1' ";
				//" GROUP BY c.state1no";
		List<Border> confini = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				int n1 = res.getInt("n1");
				String s1 = res.getString("s1");
				int n2 = res.getInt("n2");
				String s2 = res.getString("s2");
				int a = res.getInt("y");
				
				Border btemp = new Border(n1, s1,n2,s2,a);
				
				confini.add(btemp);
			}
			
			if(anno<1816 || anno>2016) {
				throw new NullPointerException("Non e' possibile soddisfare la richiesta per l'anno selezionato. Inserire un anno dal 1816 al 2016.");
			}
			
			conn.close();
			
			return confini;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("ERRORE!\nNon e' possibile soddisfare la richiesta per l'anno selezionato. Inserire un anno dal 1816 al 2016.", e);
		}
		
		//return confini;
	}

}
