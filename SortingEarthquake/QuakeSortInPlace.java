package com.sorting.earthquake;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
    	int deepestIndex = from;
    	for(int i=from+1; i<quakeData.size(); i++) {
    		if(quakeData.get(i).getDepth() > quakeData.get(deepestIndex).getDepth()) {
    			deepestIndex = i;
    		}
    	}
    	
    	return deepestIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
    	int passes=0;
    	for(int i=0; i<in.size(); i++) {
    		if(passes==70)
    			break;
    		int deepIdx = getLargestDepth(in,i);
    		passes++;
    		QuakeEntry qi = in.get(i);
    		QuakeEntry qdeep = in.get(deepIdx);
    		in.set(i, qdeep);
    		in.set(deepIdx, qi);
    	}
    }
    
    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
    	for(int i=0; i<quakeData.size()-1-numSorted; i++) {
    		QuakeEntry q1 = quakeData.get(i);
    		QuakeEntry q2 = quakeData.get(i+1);
    		
    		if(q1.getMagnitude()>q2.getMagnitude()) {
    			quakeData.set(i, q2);
    			quakeData.set(i+1, q1);
    		}
    	}
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
    	in.forEach(System.out::println);
    	for(int i=0; i<in.size()-1; i++) {
    		this.onePassBubbleSort(in,i);
    		System.out.println("Printing Quakes after pass" + i);
    		in.forEach(System.out::println);
    	}
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
    	for(int i=0; i<quakes.size()-1; i++) {
    		if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude()) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
    	int passes =0;
    	for(int i=0; i<in.size()-1; i++) {
    		onePassBubbleSort(in,i);
    		passes++;
    		if(checkInSortedOrder(in)) {
    			break;
    		}
    	}
    	
    	System.out.println("Passes needed:"+ passes);
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
    	System.out.println("Passes needed:"+ sortMagnitudeWithPasses(in) );
    }
    
    private int sortMagnitudeWithPasses(ArrayList<QuakeEntry> in) {
    	int passes =0;
    	for(int i=0; i<in.size()-1; i++) {
    		int minIdx = getSmallestMagnitude(in,i);
    		QuakeEntry qi = in.get(i);
    		QuakeEntry qmin = in.get(minIdx);
    		in.set(i, qmin);
    		in.set(minIdx, qi);
    		passes +=1;
    		if(checkInSortedOrder(in)) {
    			break;
    		}
    	}
    	
    	return passes;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:/Users/Implemention Eng/eclipse-workspace/SortingEarthquake/src/data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
//        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
//        sortByLargestDepth(list);
//        sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
//        for (QuakeEntry qe: list) { 
//            System.out.println(qe);
//        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
