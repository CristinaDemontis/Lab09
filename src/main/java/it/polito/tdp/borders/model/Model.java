package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	List<CoppieStati> coppieStati; 
	List<Country> paesi; 
	Map<Integer, Country> countryID; 
	private Graph<Country, DefaultEdge> grafo;

	public Model() {	
	}

	public void creaGrafo(Integer anno) {
		
		countryID = new HashMap<>();
		BordersDAO dao = new BordersDAO(); 
		this.paesi = dao.loadAllCountries();  // mi da null questo 
		
		for(Country c: this.paesi) {
			countryID.put(c.getCCode(), c);
		}
		
		this.coppieStati = dao.getCountryPairs(anno , countryID);
		
		Graphs.addAllVertices(this.grafo, this.paesi);
		
		for(CoppieStati c: coppieStati) {
			Graphs.addEdgeWithVertices(this.grafo, c.getStato1(), c.getStato2());
		}
		
		System.out.println();
		System.out.println("Grafo creato con "+this.grafo.vertexSet().size() +
				" vertici e " + this.grafo.edgeSet().size() + " archi") ;
		System.out.println();
		System.out.println(this.grafo);

		

		
	}

}
