package com.earthquake.filter.starter;

public class DistanceFilter implements Filter {
	
	private Location loc;
	private double maxDist;
	

	public DistanceFilter(double lat, double lon, double maxDist) {
		loc =new Location(lat, lon);
		this.maxDist = maxDist;
	}
	

	@Override
	public boolean satisfies(QuakeEntry qe) {
		return qe.getLocation().distanceTo(loc) <= maxDist;
	}


	@Override
	public String getName() {
		return "DistanceFilter";
	}

}
