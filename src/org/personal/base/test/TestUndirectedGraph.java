package org.personal.base.test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.personal.base.UndirectedGraph;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUndirectedGraph {
	
	UndirectedGraph ug;
	
	@BeforeClass
	public void setupTests() throws FileNotFoundException, IOException, ParseException {
		ug = new UndirectedGraph("./data/ugraph1.json");
	}
	
	@Test
	public void printNumberOfNodes() {
		System.out.println("number of nodes:"+ug.getSize());
	}
	
	@Test
	public void printVerticesArray() {
		System.out.println("Vertices:");
		ug.printVertices();
		System.out.println();		
	}
	@Test 
	public void testNodeInVertices() {
		assertFalse(ug.isNodeInVertices(10));
		assertTrue(ug.isNodeInVertices(11));
		assertTrue(ug.isNodeInVertices(1));
		assertTrue(ug.isNodeInVertices(2));
	}

}
