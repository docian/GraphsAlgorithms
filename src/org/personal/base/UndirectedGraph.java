package org.personal.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class UndirectedGraph {
	
	private int[] nodes;	
	private Set edges = new HashSet<long[]>();
	private ArrayList adjiacencyLists = new ArrayList<ArrayList>();
	
	private JSONArray varray;

	public UndirectedGraph(String filePath) throws FileNotFoundException, IOException, ParseException{
		Object obj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jo = (JSONObject) obj;
        long size = (long)jo.get("size");
        nodes = new int[(int)size];
        varray = (JSONArray)jo.get("edges");
        Iterator it = varray.iterator();
        JSONArray jsa = new JSONArray() ;
        long[][] iv = new long[(int)size][2];
        for(int k=0;k < varray.size(); k++) {
        	iv[k][0] = (long)(((JSONArray)varray.get(k)).get(0));
        	iv[k][1] = (long)(((JSONArray)varray.get(k)).get(1));        	
        	edges.add(iv[k]);
        }
	}
	
	public int getSize() {
		return nodes.length;
	}
	
	public Set getEdges() {
		return edges;
	}
	
	public boolean isInEdges(int node) {
		Iterator itv = edges.iterator();
		long[] vertex = new long[2];
		while(itv.hasNext()) {
			vertex = (long[])itv.next();
			if((vertex[0]==node)||(vertex[1]==node)) return true;
		}
		return false;
	}
	
	public ArrayList getAdjiacencyList(int node) {
		Iterator ita = adjiacencyLists.iterator();
		while(ita.hasNext()) {
			if(((ArrayList)ita).contains(new Integer(node))) return (ArrayList)ita;
		}
		return null;
	}
	
	
	public ArrayList createNodeAdjList(int node) {
		ArrayList nodeList = new ArrayList<Integer>();
		if(node > nodes.length) return null;
		Iterator it = edges.iterator();
		nodeList.add(new Integer(node));
		while(it.hasNext()) {
			long[] pair = (long[])it.next();
			if(node == pair[0]) nodeList.add(new Integer((int)pair[1]));
			else if(node == pair[1]) nodeList.add(new Integer((int)pair[0]));
		}
		return nodeList;
	}
	
	public ArrayList<Integer> createAdjListsByDFS(){
		boolean[] visited = new boolean[nodes.length];
		ArrayList<Integer> adjLst;
		for(int i = 1; i <= nodes.length; i++) {
			Stack<Integer> q = new Stack<Integer>();
			if(!visited[i-1]) {
				if(q.isEmpty()) adjLst = new ArrayList<Integer>();
				q.push(new Integer(i));	
				visited[i-1] = true;
			}				
			Iterator it = edges.iterator();
			while(it.hasNext()) {
				long[] tmpEdge = (long[])it.next();
				if(i == tmpEdge[0]) {
					adjLst.add(new Integer((int)tmpEdge[1]));
					visited[(int)tmpEdge[1]-1] = true;					
				}
				if(i == tmpEdge[1]) {
					adjLst.add(new Integer((int)tmpEdge[0]));
					visited[(int)tmpEdge[0]-1] = true;					
				}
//				edges.remove(tmpEdge);
				adjiacencyLists.add(adjLst);
			}
		
		}
		return adjiacencyLists;
	}
	
	
	
	public void printEdges() {
		Iterator iter = edges.iterator();
		long[] val = new long[2];
		while(iter.hasNext()){
			val = (long[])iter.next();
			System.out.print("["+val[0]+","+val[1]+"],");
		}
	}
	
	public static void main(String[] s) throws FileNotFoundException, IOException, ParseException{
		UndirectedGraph udg = new UndirectedGraph("./data/ugraph1.json");
		System.out.println(udg.createNodeAdjList(2));
		System.out.println(udg.createNodeAdjList(1));
		System.out.println(udg.createNodeAdjList(10));
		System.out.println(udg.createNodeAdjList(12));
		System.out.println(udg.createAdjListsByDFS());
	}

}
