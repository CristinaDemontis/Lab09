package it.polito.tdp.borders.model;

public class Country {
	
	private String StateAbb;
	private Integer CCode; 
	private String StateNme;
	
	public Country(String stateAbb, Integer cCode, String stateNme) {
		super();
		StateAbb = stateAbb;
		CCode = cCode;
		StateNme = stateNme;
	}

	public String getStateAbb() {
		return StateAbb;
	}

	public Integer getCCode() {
		return CCode;
	}

	public String getStateNme() {
		return StateNme;
	} 
	
	
	
	

}
