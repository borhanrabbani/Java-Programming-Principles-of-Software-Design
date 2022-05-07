package com.week3.randomtext;

import edu.duke.FileResource;

public class Tester {
	
	public void testGetFollows() {
		MarkovOne mo = new MarkovOne();
		String str = "this is a test yes this is a test.";
		mo.setTraining(str);
		String key = "es";
		System.out.println(mo.getFollows(key));
		System.out.println(mo.getFollows(key).size());

	}
	
	public void testGetFollowsWithFile() {
		MarkovOne mo = new MarkovOne();
		FileResource f = new FileResource();
		
		mo.setTraining(f.asString());
		String key ="th";
		System.out.println(mo.getFollows(key));
		System.out.println(mo.getFollows(key).size());
	}

	public static void main(String[] args) {
		Tester test = new Tester();
		test.testGetFollowsWithFile();
	}

}
