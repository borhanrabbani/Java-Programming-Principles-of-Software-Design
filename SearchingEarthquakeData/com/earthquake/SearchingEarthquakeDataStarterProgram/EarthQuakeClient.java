package com.earthquake.SearchingEarthquakeDataStarterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        
        for(QuakeEntry qe : quakeData) {
        	if(qe.getMagnitude() > magMin) {
        		answer.add(qe);
        	}
        }
        
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe: quakeData) {
        	if(qe.getLocation().distanceTo(from) < distMax) {
        		answer.add(qe);
        	}
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        //coding a magnitude filter
//        for(QuakeEntry qe : list) {
//        	if(qe.getMagnitude()>=5.0) {
//        		System.out.println(qe);
//        	}
//        }
        
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        
        for(QuakeEntry qe: listBig) {
        	System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:/Users/Implemention Eng/eclipse-workspace/Earthquake/src/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> listdis = filterByDistanceFrom(list, 1000000, city);
        System.out.println("Filtering by distance:");
        for(QuakeEntry qe: listdis) {
        	System.out.println(qe.getLocation().distanceTo(city) + " " + qe.getInfo());
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
    	
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe: quakeData) {
    		if(qe.getDepth()>minDepth && qe.getDepth()<maxDepth) {
    			answer.add(qe);
    		}
    	}
    	
    	return answer;
    }
    
    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/Implemention Eng/eclipse-workspace/Earthquake/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size() + " quakes");
        ArrayList<QuakeEntry> depthList = filterByDepth(list,-8000.0, -5000.0);
        
        int count=0;
        
        for(QuakeEntry qe: depthList) {
        	System.out.println(qe);
        	count++;
        }
        
        System.out.println("Found " + count + " Quakes that match that criteria");
        
    	
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	
    	for(QuakeEntry qe : quakeData) {
    		if(where.equals("start")) {
    			if(qe.getInfo().startsWith(phrase)) {
    				answer.add(qe);
    			}
    		}
    		
    		if(where.equals("end")) {
    			if(qe.getInfo().endsWith(phrase)) {
    				answer.add(qe);
    			}
    		}
    		if(where.equals("any")) {
    			if(qe.getInfo().contains(phrase)) {
    				answer.add(qe);
    			}
    		}
    	}
    	
    	return answer;
    }
    
    public void quakesByPhrase() {
    	EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/Implemention Eng/eclipse-workspace/Earthquake/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> phraseFilter = filterByPhrase(list, "any", "Creek");
        int count=0;
        for(QuakeEntry qe: phraseFilter) {
        	
        	System.out.println(qe);
        	count++;
        }
        System.out.println("Found " + count +" quakes");
    }
}
