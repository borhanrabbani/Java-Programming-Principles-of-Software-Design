package com.sorting.earthquake.efficientsort;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		int compTitle = q1.getInfo().compareTo(q2.getInfo());
		if(compTitle==0) {
			return Double.compare(q1.getDepth(), q2.getDepth());
		}
		return compTitle;
	}

}
