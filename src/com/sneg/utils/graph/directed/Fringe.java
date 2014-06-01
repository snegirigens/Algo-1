package com.sneg.utils.graph.directed;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Author:	sneg
 * Date:	31.05.14
 * Time:	9:45
 */
public class Fringe {
	private Queue <Graph.Node> _fringe = new LinkedList<>();

	public Graph.Node pop() {
		return _fringe.poll();
	}

	public void push (Graph.Node node) {
		_fringe.offer (node);
	}
}
