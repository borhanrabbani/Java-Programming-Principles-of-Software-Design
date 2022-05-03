package com.earthquake.filter.starter;

public class PhraseFilter implements Filter {

	private String where;
	private String phrase;
	
	
	public PhraseFilter(String where, String phrase) {
		this.where = where;
		this.phrase = phrase;
	}


	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(where.equals("start")) {
			if(qe.getInfo().startsWith(phrase)) {
				return true;
			}
		}
		if(where.equals("end")) {
			if(qe.getInfo().endsWith(phrase)) {
				return true;
			}
		}
		if(where.equals("any")) {
			if(qe.getInfo().contains(phrase)) {
				return true;
			}
		}
		
		return false;
	}


	@Override
	public String getName() {
		return "PhraseFilter";
	}

}
