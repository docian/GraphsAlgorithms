package org.personal.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class UndirectedGraph {
	
	private int[] nodes;	
	private Set vertices = new HashSet<long[]>();
	private List adjiacencyLists = new ArrayList<int[]>();
	
	private JSONArray varray;
	
	public UndirectedGraph(String filePath) throws FileNotFoundException, IOException, ParseException{
		Object obj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jo = (JSONObject) obj;
        long size = (long)jo.get("size");
        nodes = new int[(int)size];
        varray = (JSONArray)jo.get("vertices");
        Iterator it = varray.iterator();
        JSONArray jsa = new JSONArray() ;
        long[][] iv = new long[(int)size][2];
        for(int k=0;k < varray.size(); k++) {
        	iv[k][0] = (long)(((JSONArray)varray.get(k)).get(0));
        	iv[k][1] = (long)(((JSONArray)varray.get(k)).get(1));        	
        	vertices.add(iv[k]);
        }
	}
	
	public int getSize() {
		return nodes.length;
	}
	
	public Set getVertices() {
		return vertices;
	}
	
	public boolean isNodeInVertices(int node) {
		Iterator itv = vertices.iterator();
		long[] vertex = new long[2];
		while(itv.hasNext()) {
			vertex = (long[])itv.next();
			if((vertex[0]==node)||(vertex[1]==node)) return true;
		}
		return false;
	}
	
	public ArrayList isNodeInAdjiacencyLists(int node) {
		
		return null;
	}
	
	public ArrayList createAdjiancencyNodeLists() {
		for(int i: nodes) {
			List adjNodelist = new ArrayList<int[]>();
		}
		return null;
	}
	

	
	public void printVertices() {
		Iterator iter = vertices.iterator();
		long[] val = new long[2];
		while(iter.hasNext()){
			val = (long[])iter.next();
			System.out.print("["+val[0]+","+val[1]+"],");
		}
	}

}
