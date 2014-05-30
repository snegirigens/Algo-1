package com.sneg.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author:	sneg
 * Date:	27.05.14
 * Time:	22:53
 */
public class Graph {
	private Map <String, Node> _nodes = new HashMap<>();
	private Map <String, Edge> _edges = new HashMap<>();

	public Set <Node> getNodes() {
		return new HashSet<> (_nodes.values());
	}

	public Node getNode (String id) {
		return _nodes.get (id);
	}

	public void addNode (Node node) {
		_nodes.put (node.getId(), node);
	}

	public boolean removeNode (String id) {
		return _nodes.remove (id) != null;
	}

	public Set <Edge> getEdges() {
		return new HashSet<> (_edges.values());
	}

	public Edge getEdge (String id) {
		return _edges.get (id);
	}

	public void addEdge (Edge edge) {
		_edges.put (edge.getId(), edge);
	}

	public boolean removeEdge (String id) {
		return _edges.remove (id) != null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append ("Graph {\n");

		for (Node node : _nodes.values ()) {
			sb.append ("\t").append (node.getId()).append (": ");

			for (Edge edge : node.getEdges()) {
				sb.append (edge).append (", ");
			}

			sb.append ("\n");
		}

		sb.append ("}");
		return sb.toString();
	}

	public static class Node {
		private final String _id;
		private Set <Edge> _edges = new HashSet<>();

		public Node (String id) {
			_id = id;
		}

		public String getId() {
			return _id;
		}

		public Set <Edge> getEdges() {
			return _edges;
		}

		public void setEdges (Set <Edge> edges) {
			this._edges = edges;
		}

		public void addEdge (Edge edge) {
			_edges.add (edge);
		}

		public boolean removeEdge (Edge edge) {
			return _edges.remove (edge);
		}

		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Node node = (Node) o;
			return _id.equals (node._id);
		}

		public int hashCode() {
			return _id.hashCode();
		}

		public String toString() {
			return _id;
		}
	}

	public static class Edge {
		private final String _id;
		private Node _node1;
		private Node _node2;

		public Edge (String id, Node node1, Node node2) {
			_id = id;
			_node1 = node1;
			_node2 = node2;
		}

		public String getId() {
			return _id;
		}

		public Node[] getNodes() {
			return new Node[] {_node1, _node2};
		}

		public boolean isSelfEdge() {
			return _node1.equals (_node2);
		}

		public void replace (Node orgNode, Node newNode) {
			if (orgNode.equals (_node1)) {
				_node1 = newNode;
			}
			else if (orgNode.equals (_node2)) {
				_node2 = newNode;
			}
			else {
				throw new IllegalStateException ("None of the nodes is " + orgNode);
			}
		}

		public Node getOtherEnd (Node node) {
			if (node.equals (_node1)) {
				return _node2;
			}
			else if (node.equals (_node2)) {
				return _node1;
			}
			else {
				throw new IllegalStateException ("None of the nodes is " + node);
			}
		}

		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Edge edge = (Edge) o;
			return _id.equals (edge._id);
		}

		public int hashCode() {
			return _id.hashCode();
		}

		public String toString() {
			return _id + "(" +_node1.toString() + "-" + _node2.toString() + ")";
		}
	}
}
