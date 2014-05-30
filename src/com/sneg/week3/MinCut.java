package com.sneg.week3;

import com.sneg.utils.Graph;
import com.sneg.utils.GraphBuilder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Author:        sneg
 * Date:        27.05.14
 * Time:        22:53
 */
public class MinCut {
	private static Random _rand = new Random (System.currentTimeMillis ());

	public static void main (String[] args) throws Exception {
		String input = "D:/Courses/Algorithms-Design/Home/Week-3/kargerMinCut.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-3/test-1.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-3/test-2.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-3/test-4.txt";

		Graph graphOrg = GraphBuilder.build (input);
		System.out.println ("Original : " + graphOrg);

		Graph graph = GraphBuilder.clone (graphOrg);

		int minCut = Integer.MAX_VALUE;
		int count = graph.getNodes ().size ();

		try {
			for (int i = 0; i < count; ++i) {                // count * count * count / 2
				MinCut algo = new MinCut ();
				int cut = algo.findMinCut (graph);
//				System.out.println ("Result:\n" + graph);
				System.out.println ("Cut = " + cut);
				minCut = Math.min (minCut, cut);

				graph = GraphBuilder.clone (graphOrg);
			}

			System.out.println ("MinCut = " + minCut);
		}
		finally {

			System.out.println ("Original : " + graphOrg);
		}
	}

	private int findMinCut (Graph graph) {
		while (graph.getNodes ().size () > 2) {
//			System.out.println (graph);
			contract (graph);
		}

		return graph.getEdges ().size ();
	}

	private Graph contract (Graph graph) {
		Graph.Edge contractedEdge = selectEdge (graph);

//		System.out.println ("Contracting edge " + contractedEdge);

		Graph.Node[] nodes = contractedEdge.getNodes ();
		Graph.Node newNode = new Graph.Node (nodes[0] + "+" + nodes[1]);
		graph.addNode (newNode);

		for (Graph.Edge edge : nodes[0].getEdges ()) {
			edge.replace (nodes[0], newNode);
			newNode.addEdge (edge);
		}

		for (Graph.Edge edge : nodes[1].getEdges ()) {
			edge.replace (nodes[1], newNode);
			newNode.addEdge (edge);
		}

		Set<Graph.Edge> selfEdges = new HashSet<> ();

		for (Graph.Edge edge : newNode.getEdges ()) {
			if (edge.isSelfEdge ()) {
				selfEdges.add (edge);
			}
		}

		for (Graph.Edge edge : selfEdges) {
			newNode.removeEdge (edge);
			graph.removeEdge (edge.getId ());
		}

		graph.removeNode (nodes[0].getId ());
		graph.removeNode (nodes[1].getId ());

		return graph;
	}

	private Graph.Edge selectEdge (Graph graph) {
		Set<Graph.Edge> edges = graph.getEdges ();
		int i = _rand.nextInt (edges.size ());

		for (Iterator<Graph.Edge> iterator = edges.iterator (); iterator.hasNext (); iterator.next ()) {
			if (--i <= 0) {
				return iterator.next ();
			}
		}

		return null;
	}
} 