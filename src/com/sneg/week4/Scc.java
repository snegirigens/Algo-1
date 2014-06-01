package com.sneg.week4;

import com.sneg.utils.graph.directed.Fringe;
import com.sneg.utils.graph.directed.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * Author:        sneg
 * Date:        27.05.14
 * Time:        22:53
 */
public class Scc {
	private static Random _rand = new Random (System.currentTimeMillis ());

	private int _label;
	private Graph.Node _leader;
	private Map <Graph.Node, List <Graph.Node>> _sccs = new TreeMap<> (Graph.COMPARE_BY_ID);


	public static void main (String[] args) throws Exception {
		if (args.length == 0) throw new IllegalArgumentException ("No input file");
		String input = args[0];

//		String input = "D:/Courses/Algorithms-Design/Home/Week-4/SCC.txt";		// [434821, 968, 459, 313, 211]
//		String input = "D:/Courses/Algorithms-Design/Home/Week-4/test-1.txt";	// [3, 3, 2]
//		String input = "D:/Courses/Algorithms-Design/Home/Week-4/test-2.txt";	// [6, 3, 2, 1]
//		String input = "D:/Courses/Algorithms-Design/Home/Week-4/test-3.txt";	// [3, 3, 1, 1]

		System.out.println ("Load graph");
		Graph graph = GraphBuilder.build (input);

//		System.out.println (graph);
//		System.out.println ("\n--------------------\n");
//		System.out.println (graph.reverse().reverse ());

		Scc scc = new Scc();

		System.out.println ("First DFS loop");
		scc.dfsLoop (graph.reverse(), Graph.COMPARE_BY_ID);
//		System.out.println (graph);

		System.out.println ("Second DFS loop");
		scc.dfsLoop (graph.reverse(), Graph.COMPARE_BY_LABEL);
//		System.out.println (graph);

		System.out.println ("Preparing result");
		scc.printAnswer (graph);

//		Map <Graph.Node, List <Graph.Node>> sccs = scc.buildSccs (graph);
//		scc.printSCCS (sccs);
	}

	private void dfsLoop (Graph graph, Comparator <Graph.Node> comparator) {
//		System.out.println (graph);
//		System.out.println ("\n--------------------\n");
		Set <Graph.Node> explored = new HashSet<>();

		_label = 1;

		for (Graph.Node node : graph.getNodes (comparator)) {
			if (explored.contains (node)) continue;

			_leader = node;
//			System.out.println ("Starting DFS from leader " + _leader);
			Fringe fringe = new Fringe();
			fringe.push (node);

			dfs (fringe, explored);
		}
	}

	private void dfs (Fringe fringe, Set <Graph.Node> explored) {
		Graph.Node node = fringe.pop();
		if (node == null) return;
		explored.add (node);

//		System.out.println ("Exploring " + node);

		for (Graph.Edge edge : node.getEdges()) {
			Graph.Node head = edge.getHead();

			if (!explored.contains (head)) {
				fringe.push (head);
				dfs (fringe, explored);
			}
		}

		node.setLabel (_label++);
		node.setLeader (_leader);
	}

	private Map <Graph.Node, List <Graph.Node>> buildSccs (Graph graph) {
		Map <Graph.Node, List <Graph.Node>> sccs = new TreeMap<> (Graph.COMPARE_BY_ID);

		for (Graph.Node node : graph.getNodes (Graph.COMPARE_BY_ID)) {
			Graph.Node leader = node.getLeader();

			List <Graph.Node> scc = sccs.get (leader);
			if (scc == null) {
				scc = new ArrayList<>();
				sccs.put (leader, scc);
			}

			scc.add (node);
		}

		return sccs;
	}

	private void printSCCS (Map <Graph.Node, List <Graph.Node>> sccs) {
		StringBuilder sb = new StringBuilder();

		for (Graph.Node leader : sccs.keySet()) {
			sb.append (leader.getId()).append (": (");

			List <Graph.Node> nodes = sccs.get (leader);
			for (Graph.Node node : nodes) {
				sb.append (node.getId()).append (", ");
			}

			sb.append (")\n");
		}

		System.out.println (sb.toString());

	}

	public void printAnswer (Graph graph) {
		final Map <Graph.Node, Integer> sccs = new HashMap<>();

		for (Graph.Node node : graph.getNodes (Graph.COMPARE_BY_ID)) {
			Graph.Node leader = node.getLeader();

			Integer count = sccs.get (leader);
			count = count != null ? count + 1 : 1;

			sccs.put (leader, count);
		}

		List <Integer> sizes = new ArrayList<> (sccs.values());
		Collections.sort (sizes);
		Collections.reverse (sizes);
		System.out.println (sizes.size() > 5 ? sizes.subList (0, 5) : sizes);
	}
}