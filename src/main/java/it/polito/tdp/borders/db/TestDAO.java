package it.polito.tdp.borders.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.CoppieStati;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();
	
		System.out.println("Lista di tutte le nazioni:");
		List<Country> countries = dao.loadAllCountries();
		
		Map<Integer,Country> countryID = new HashMap<>();
		
		for(Country c: countries) {
			countryID.put(c.getCCode(), c);
		}
		
		List<CoppieStati> coppie = dao.getCountryPairs(1816, countryID);
		System.out.println("Lista di tutte le coppie:");
		System.out.println(coppie);
		System.out.println("il numero degli archi è: "+coppie.size());

		


		
	}
}
