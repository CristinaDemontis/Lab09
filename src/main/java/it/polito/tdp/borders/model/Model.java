package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	List<CoppieStati> coppieStati; 
	List<Country> paesi; 
	Map<Integer, Country> countryID; 
	private Graph<Country, DefaultEdge> grafo;
	Set<Country> setVertici = new HashSet<>(); 

	public Model() {	
	}

	public void creaGrafo(Integer anno) {
		
		grafo = new SimpleGraph<>(DefaultEdge.class); 
		countryID = new HashMap<>();
		BordersDAO dao = new BordersDAO(); 
		this.paesi = dao.loadAllCountries();  // mi da null questo 
		
		for(Country c: this.paesi) {
			countryID.put(c.getCCode(), c);
		}
		
		this.coppieStati = dao.getCountryPairs(anno , countryID);
		
		for(CoppieStati c: coppieStati) {
			setVertici.add(c.getStato1());
			setVertici.add(c.getStato2());
			
		}
		
		
		Graphs.addAllVertices(this.grafo, this.setVertici);
		
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
