package com.sneg.week5;

import com.sneg.utils.Heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:	sneg
 * Date:	05.06.14
 * Time:	23:56
 */
public class ShortestPath {
	public static void main (String[] args) throws Exception {
		String input = "D:/Courses/Algorithms-Design/Home/Week-5/dijkstraData.txt";	// 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
//		String input = "D:/Courses/Algorithms-Design/Home/Week-5/test-1.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-5/test-2.txt";

		System.out.println ("Load graph");
		Graph graph = GraphBuilder.build (input);
		System.out.println (graph);

		ShortestPath sp = new ShortestPath (graph);
		long start = System.currentTimeMillis();
		sp.processGraph ("1");
		long end = System.currentTimeMillis();

//		System.out.println ("------------------");
//		System.out.println (graph);
//
//		for (Graph.Node node : graph.getNodes()) {
//			System.out.println (node.getId() + " --> " + node.getScore());
//		}

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
		System.out.println ("Processing time: " + (end - start) + " ms.");
	}

	private final Graph _graph;
	private final Map <String, Graph.Node> _explored = new HashMap<>();

	private final Heap <Graph.Node> _candidateNodes = new Heap<> (new Comparator <Graph.Node>() {
		public int compare (Graph.Node node1, Graph.Node node2) {
			return node1.getScore() - node2.getScore();
		}
	});

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
//		while (_explored.size() < _graph.getNodes().size() || _candidateNodes.size() > 0) {
		while (true) {
			processNode (node);

			if (_candidateNodes.size() > 0) {
				node = _candidateNodes.remove();
			} else {
				break;
			}
		}
	}

	private void processNode (Graph.Node node) {
		if (_explored.containsKey (node.getId())) return;

		_explored.put (node.getId(), node);

		for (Graph.Edge edge : node.getEdges()) {
			Graph.Node other = edge.getOtherEnd (node);

			if (!_explored.containsKey (other.getId())) {
				int provisionalScore = node.getScore() + edge.getLength();
				other.setScore (Math.min (other.getScore (), provisionalScore));

				_candidateNodes.add (other);
			}
		}
	}
}
