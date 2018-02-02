package org.personal.base.test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.personal.base.UndirectedGraph;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestUndirectedGraph {
	
	UndirectedGraph ug;
	
	@BeforeClass
	public void setupTests() throws FileNotFoundException, IOException, ParseException {
		ug = new UndirectedGraph("./data/ugraph1.json");
	}
	
	@DataProvider(name="ValidNodes")
	public Object[] provideValidNodes() {
		Object[] vNodes = {1,2,3,4,5,6,7,8,9};
		return vNodes;
	}
	
	@DataProvider(name="InvalidNodes")
	public Object[] provideInvalidNodes() {
		Object[] vNodes = {10,11};
		return vNodes;
	}	
	
	@Test
	public void printNumberOfNodes() {
		System.out.println("number of nodes:"+ug.getSize());
	}

}
