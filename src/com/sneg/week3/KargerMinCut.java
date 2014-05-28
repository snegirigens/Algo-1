package com.sneg.week3;

import com.sneg.utils.Graph;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Author:	sneg
 * Date:	27.05.14
 * Time:	22:53
 */
public class KargerMinCut {
	private static Graph _graph;

	public static void main (String[] args) throws Exception {
		String input = "D:/Courses/Algorithms-Design/Home/Week-3/kargerMinCut.txt";
		_graph = buildGraph (input);

		System.out.println (_graph);
	}

	private static Graph buildGraph (String file) throws Exception {
		List <String> lines = Files.readAllLines (new File (file).toPath(), Charset.defaultCharset());

		Graph graph = new Graph();

		int j = 0;
		for (String line : lines) {
			String[] numbers = line.split ("\\s+");
			Graph.Node node1 = new Graph.Node (numbers[0]);
			graph.addNode (node1);

			for (int i = 1; i < numbers.length; ++i) {
				Graph.Node node2 = new Graph.Node (numbers[i]);
				Graph.Edge edge  = new Graph.Edge (Integer.toString (++j), node1, node2);
				node1.addEdge (edge);
				graph.addEdge (edge);
			}
		}

		return graph;
	}

	private static Graph contract (Graph graph) {

	}
}
