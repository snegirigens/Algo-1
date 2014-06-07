package com.sneg.week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Author:	sneg
 * Date:	05.06.14
 * Time:	23:56
 */
public class ShortestPath {
	public static void main (String[] args) throws Exception {
		String input = "D:/Courses/Algorithms-Design/Home/Week-5/dijkstraData.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-5/test-1.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-5/test-2.txt";

		System.out.println ("Load graph");
		Graph graph = GraphBuilder.build (input);
		System.out.println (graph);

		ShortestPath sp = new ShortestPath (graph);
		sp.processGraph ("1");

		System.out.println ("------------------");
//		System.out.println (graph);

		for (Graph.Node node : graph.getNodes()) {
			System.out.println (node.getId() + " --> " + node.getScore());
		}

		String[] targetIds = new String[] { "7","37","59","82","99","115","133","165","188","197" };

		System.out.println ("------------------");
		StringBuilder sb = new StringBuilder();

		for (String id : targetIds) {
			Graph.Node node = graph.getNode (id);
			System.out.println (node + " --> " + node.getScore());
			if (sb.length() > 0) sb.append (",");
			sb.append (node.getScore());
		}

		System.out.println (sb);
	}

	private final Graph _graph;
	private final Map <String, Graph.Node> _explored = new HashMap<>();

	private final Set <Graph.Edge> _crossEdges = new HashSet<>();
	private final Set <Graph.Node> _candidateNodes = new HashSet<>();

	public ShortestPath (Graph graph) {
		_graph = graph;
	}

	public void processGraph (String nodeId) {
		Graph.Node node = _graph.getNode (nodeId);
		if (node == null) throw new IllegalStateException ("Cannot find node " + nodeId);
		node.setScore (0);

		loop (node);
	}

	private void loop (Graph.Node node) {
		Comparator <Graph.Node> nodeComparator = new Comparator <Graph.Node>() {
			public int compare (Graph.Node node1, Graph.Node node2) {
				return node1.getScore() - node2.getScore();
			}
		};

		while (_explored.size() < _graph.getNodes().size()) {
			processNode (node);

			if (_candidateNodes.size() > 0) {
				List <Graph.Node> nodes = new ArrayList<> (_candidateNodes);
				Collections.sort (nodes, nodeComparator);

				node = nodes.get (0);
			}
		}
	}

	private void processNode (Graph.Node node) {
		_explored.put (node.getId(), node);
		_candidateNodes.remove (node);

		for (Graph.Edge edge : node.getEdges()) {
			Graph.Node other = edge.getOtherEnd (node);

			if (_explored.containsKey (other.getId())) {
				_crossEdges.remove (edge);
			}
			else {
				int provisionalScore = node.getScore() + edge.getLength();
				other.setScore (Math.min (other.getScore(), provisionalScore));

				_crossEdges.add (edge);
				_candidateNodes.add (other);
			}
		}
	}
}
