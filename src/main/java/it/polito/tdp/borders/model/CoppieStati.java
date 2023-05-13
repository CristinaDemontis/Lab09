package it.polito.tdp.borders.model;

public class CoppieStati {
	
	private Country stato1; 
	private Country stato2; 
	private Integer year;
	
	public CoppieStati(Country stato1, Country stato2, Integer year) {
		super();
		this.stato1 = stato1;
		this.stato2 = stato2;
		this.year = year;
	}

	public Country getStato1() {
		return stato1;
	}

	public Country getStato2() {
		return stato2;
	}

	public Integer getYear() {
		return year;
	} 
	
	
	
	
	

}
