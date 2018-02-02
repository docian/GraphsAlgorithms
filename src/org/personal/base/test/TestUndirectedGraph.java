package org.personal.base.test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.personal.base.UndirectedGraph;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestUndirectedGraph {
	
	UndirectedGraph ug;
	ArrayList adjList1;
	ArrayList adjList2;
	
	@BeforeClass
	public void setupTests() throws FileNotFoundException, IOException, ParseException {
		ug = new UndirectedGraph("./data/ugraph1.json");
		adjList1 = new ArrayList<Integer[]>();
		adjList2 = new ArrayList<Integer[]>();
		adjList1.add(new Integer(1));
		adjList1.add(new Integer(2));
		adjList1.add(new Integer(5));
		adjList1.add(new Integer(6));		
		adjList1.add(new Integer(7));
		adjList1.add(new Integer(9));
		
		adjList2.add(new Integer(3));
		adjList2.add(new Integer(4));
		adjList2.add(new Integer(8));
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
	
	@DataProvider(name="AdjiacencyLists")
	public Object[][] provideAdjiacencyLists(){		
		Object[][] o = {{adjList1,new Integer(1)},{adjList2,new Integer(2)}};		
		return o;		
	}
	
	@Test(dataProvider="AdjiacencyLists")
	public void testGetAdjiacencyList(ArrayList<Integer> adjLists, Integer node) {
		assertEquals(adjLists, ug.getAdjiacencyList(node));
	}

}
