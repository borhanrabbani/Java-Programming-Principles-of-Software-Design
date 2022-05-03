package com.earthquake.filter.starter;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {
	
	private ArrayList<Filter> matchAll;
	
	

	public MatchAllFilter() {
		matchAll = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter f) {
		matchAll.add(f);
	}
	


	@Override
	public boolean satisfies(QuakeEntry qe) {
		for(Filter fe: matchAll) {
			if(!fe.satisfies(qe)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String getName() {
		ArrayList<String> allFilterName = new ArrayList<String>();
		for(Filter f: matchAll) {
			allFilterName.add(f.getName());
		}
		
		String name ="";
		for(int i=0; i<allFilterName.size(); i++) {
			name = name + " " + allFilterName.get(i);
		}
		
		
		return name;
	}

}
