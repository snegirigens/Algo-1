package com.sneg.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Author:        sneg
 * Date:        28.05.14
 * Time:        23:59
 */
public class GraphBuilder {
	public static Graph clone (Graph graph) {
		Graph graphCopy = new Graph ();

		for (Graph.Node node : graph.getNodes ()) {
			Graph.Node nodeCopy = graphCopy.getNode (node.getId ());

			if (nodeCopy == null) {
				nodeCopy = new Graph.Node (node.getId ());
				graphCopy.addNode (nodeCopy);
			}

			for (Graph.Edge edge : node.getEdges ()) {
				String otherId = edge.getOtherEnd (node).getId ();
				Graph.Node otherCopy = graphCopy.getNode (otherId);

				if (otherCopy == null) {
					otherCopy = new Graph.Node (otherId);
					graphCopy.addNode (otherCopy);
				}

				Graph.Edge edgeCopy = graphCopy.getEdge (edge.getId ());

				if (edgeCopy == null) {
					edgeCopy = new Graph.Edge (edge.getId (), nodeCopy, otherCopy);
					graphCopy.addEdge (edgeCopy);
				}

				nodeCopy.addEdge (edgeCopy);
			}
		}

		return graphCopy;
	}

	public static Graph build (String file) throws Exception {
		List<String> lines = Files.readAllLines (new File (file).toPath (), Charset.defaultCharset ());

		Graph graph = new Graph ();

		int j = 0;
		for (String line : lines) {
			String[] numbers = line.split ("\\s+");
			Graph.Node node = graph.getNode (numbers[0]);

			if (node == null) {
				node = new Graph.Node (numbers[0]);
				graph.addNode (node);
			}

			for (int i = 1; i < numbers.length; ++i) {
				Graph.Node other = graph.getNode (numbers[i]);

				if (other == null) {
					other = new Graph.Node (numbers[i]);
					graph.addNode (other);
				}

				if (Integer.parseInt (node.getId ()) <= Integer.parseInt (numbers[i])) {
					Graph.Edge edge = new Graph.Edge (Integer.toString (++j), node, other);
					node.addEdge (edge);
					graph.addEdge (edge);
				}
				else {
					for (Graph.Edge edge : other.getEdges ()) {
						if (edge.getOtherEnd (other).equals (node)) {
							node.addEdge (edge);
						}
					}
				}
			}
		}

		return graph;
	}
} 
