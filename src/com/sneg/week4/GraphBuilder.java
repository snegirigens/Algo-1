package com.sneg.week4;

import com.sneg.utils.graph.directed.Graph;

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
	private static final Pattern PATTERN = Pattern.compile ("^(\\d+)\\s+(\\d+)\\s*$");

	public static Graph build (String file) throws Exception {
		List <String> lines = Files.readAllLines (new File (file).toPath(), Charset.defaultCharset());

		Graph graph = new Graph();

		int i = 0;
		for (String line : lines) {
			if (++i % 10000 == 0) System.out.print ("\r" + (i / 10000));

			Matcher matcher = PATTERN.matcher (line);
			if (!matcher.matches()) throw new IllegalStateException ("Wrong input " + line);

			String number1 = matcher.group (1);
			String number2 = matcher.group (2);

			Graph.Node tail = graph.getNode (number1);
			Graph.Node head = graph.getNode (number2);

			if (tail == null) {
				tail = new Graph.Node (number1);
				graph.addNode (tail);
			}

			if (head == null) {
				head = new Graph.Node (number2);
				graph.addNode (head);
			}

			Graph.Edge edge = new Graph.Edge (tail, head);
			tail.addEdge (edge);
		}

		System.out.print ('\n');

		return graph;
	}
} 
