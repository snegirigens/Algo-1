package com.sneg.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author:	sneg
 * Date:	07.06.14
 * Time:	10:39
 */
public class Heap <T> {
	private final List <T> _heap = new ArrayList<>();
	private final Comparator <T> _comparator;
	private int size = 0;

	public Heap() {
		this (null);
	}

	public Heap (Comparator <T> comparator) {
		_comparator = comparator;
	}

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

	public int size() {
		return size;
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
		if (compare (i/2, i) > 0) {
			swap (i/2, i);
			bubbleUp (i/2);
		}
	}

	private void bubbleDown (int i) {
		if (size - ((i + 1) * 2) > 0) {			// 2 children
			int left  = (i + 1) * 2 - 1;
			int right = (i + 1) * 2;
			int child = (compare (left, right) < 0) ? left : right;
			if (compare (i, child) > 0) {
				swap (i, child);
				bubbleDown (child);
			}
		}
		else if (size - ((i + 1) * 2) == 0) {	// 1 child
			int left = (i + 1) * 2 - 1;
			if (compare (i, left) > 0) {
				swap (i, left);
				bubbleDown (left);
			}
		}
	}

	@SuppressWarnings ("unchecked")
	private int compare (int i, int j) {
		if (_comparator != null) {
			return _comparator.compare (_heap.get (i), _heap.get (j));
		}
		else {
			return ((Comparable <T>) _heap.get (i)).compareTo (_heap.get (j));
		}
	}

	private void swap (int i, int j) {
		_heap.set (j, _heap.set (i, _heap.get (j)));
	}
}
