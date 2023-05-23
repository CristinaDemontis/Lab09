package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.CoppieStati;
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
				Country c = new Country(rs.getString("StateAbb"), rs.getInt("ccode"), rs.getString("StateNme"));
				result.add(c); 
			
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Country> loadAllCountriesBeforeAYear(int year) { // da implementare per ottimizzare

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getString("StateAbb"), rs.getInt("ccode"), rs.getString("StateNme"));
				result.add(c); 
			
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<CoppieStati> getCountryPairs(int anno, Map<Integer, Country> countryID) {
		
		String sql = "SELECT con.state1no, con.state2no "
				+ "FROM country c, contiguity con "
				+ "WHERE con.`year`<= ? AND c.CCode = con.state1no AND c.CCode > con.state2no";
		List<CoppieStati> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Integer s1 = rs.getInt("state1no");
				Integer s2 = rs.getInt("state2no");
				CoppieStati c = new CoppieStati(countryID.get(s1),countryID.get(s2), anno);
				result.add(c); 
			}
				
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		
		return result;
	}
}
