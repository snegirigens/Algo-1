package com.sneg.week5;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:        sneg
 * Date:        28.05.14
 * Time:        23:59
 */
public class GraphBuilder {
	private static final Pattern PATTERN1 = Pattern.compile ("\\s+");
	private static final Pattern PATTERN2 = Pattern.compile (",");
//	private static final Pattern PATTERN = Pattern.compile ("^(\\d+)\\s+(\\d+),(\\d+)\\s*$");

	public static Graph build (String file) throws Exception {
		List<String> lines = Files.readAllLines (new File (file).toPath(), Charset.defaultCharset());

		Graph graph = new Graph();

		int j = 0;
		for (String line : lines) {
			String[] elements = PATTERN1.split (line);

			Graph.Node node = graph.getNode (elements[0]);

			if (node == null) {
				node = new Graph.Node (elements[0]);
				graph.addNode (node);
			}

			for (int i = 1; i < elements.length; ++i) {
				String[] numbers = PATTERN2.split (elements[i]);

				Graph.Node other = graph.getNode (numbers[0]);

				if (other == null) {
					other = new Graph.Node (numbers[0]);
					graph.addNode (other);
				}

				if (Integer.parseInt (node.getId()) <= Integer.parseInt (other.getId())) {
					int edgeLength = Integer.parseInt (numbers[1]);
					Graph.Edge edge = new Graph.Edge (Integer.toString (++j), node, other, edgeLength);
					node.addEdge (edge);
					graph.addEdge (edge);
				}
				else {
					for (Graph.Edge edge : other.getEdges()) {
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
