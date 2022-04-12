package com.earthquake.SearchingEarthquakeDataStarterProgram;

import java.util.ArrayList;

public class LargestQuakes {

	public void findLargestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "C:/Users/Implemention Eng/eclipse-workspace/Earthquake/src/data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list  = parser.read(source);
		ArrayList<QuakeEntry> newQuakeData = new ArrayList<QuakeEntry>(list);
		
		int count =0;
		for(QuakeEntry qe: newQuakeData) {
			System.out.println(qe);
			count++;
		}
		
		System.out.println(count);
	}
	
	public void findLargestQuakesTest(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/Implemention Eng/eclipse-workspace/Earthquake/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        int largeQuake = indexOfLargest(quakeData);
        System.out.println("Index with biggest value is = " + largeQuake);
        ArrayList<QuakeEntry> answer = getLargest(quakeData,5);
        for (int i=0; i<answer.size(); i++){
            System.out.println(answer.get(i));
        }
    }
	
	public int indexOfLargest(ArrayList<QuakeEntry> data) {
		int index=0;
		double maxIndex =0;
		for(int i=0; i<data.size(); i++) {
			QuakeEntry quake = data.get(i);
			double magnitude = quake.getMagnitude();
//			System.out.println(magnitude);
			if(magnitude>maxIndex) {
				maxIndex=magnitude;
				index=i;
			}
			
		}
		
		return index;
	}
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		
		for(int i=0; i<howMany; i++) {
			answer.add(copy.get(indexOfLargest(copy)));
			copy.remove(copy.get(indexOfLargest(copy)));
		}
		
		return answer;
	}

}
