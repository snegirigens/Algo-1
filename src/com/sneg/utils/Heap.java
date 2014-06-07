package com.sneg.utils;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author:	sneg
 * Date:	07.06.14
 * Time:	10:39
 */
public class Heap <T extends Comparable <T>> {
	private final List <T> _heap = new ArrayList<>();
	private int size = 0;

	public void add (T element) {
		_heap.add (element);
		bubbleUp (size++);
	}

	public T get() {
		if (_heap.size() == 0) return null;
		return _heap.get (0);
	}

	public T remove() {
		if (_heap.size() == 0) return null;
		swap (0, size - 1);
		T root = _heap.remove (--size);
		bubbleDown (0);
		return root;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T t : _heap) {
			if (sb.length() > 0) sb.append (", ");
			sb.append (t);
		}
		return "[" + sb.toString() + "]";
	}

	private void bubbleUp (int i) {
		if (i <= 0) return;
		if (_heap.get (i/2).compareTo (_heap.get (i)) > 0) {
			swap (i/2, i);
			bubbleUp (i/2);
		}
	}

	private void bubbleDown (int i) {
		if (size - ((i + 1) * 2) > 0) {			// 2 children
			int left  = (i + 1) * 2 - 1;
			int right = (i + 1) * 2;
			int child = (_heap.get (left).compareTo (_heap.get (right)) < 0) ? left : right;
			if (_heap.get (i).compareTo (_heap.get (child)) > 0) {
				swap (i, child);
				bubbleDown (child);
			}
		}
		else if (size - ((i + 1) * 2) == 0) {	// 1 child
			int left = (i + 1) * 2 - 1;
			if (_heap.get (i).compareTo (_heap.get (left)) > 0) {
				swap (i, left);
				bubbleDown (left);
			}
		}
	}

	private void swap (int i, int j) {
		_heap.set (j, _heap.set (i, _heap.get (j)));
	}
//
//	private boolean hasLeft (int i) {
//		int index = (i + 1) * 2;
//		return index <= size;
//	}
//
//	private boolean hasRight (int i) {
//		int index = (i + 1) * 2;
//		return index <= size + 1;
//	}
}
