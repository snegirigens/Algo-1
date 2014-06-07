package com.sneg.utils;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class HeapTest {
	@Test
	public void testComparable() {
		Heap <Integer> heap = new Heap<>();
		System.out.println (heap.toString());

		for (Integer i : new Integer[] {9, 4, 2, 6, 8, 1, 0, 3, 7, 5}) {
			heap.add (i);
			System.out.println (i + " : " + heap.toString ());
		}

		System.out.println ("---------------------------");

		for (Integer i = heap.remove(); i != null; i = heap.remove()) {
			System.out.println (i + " : " + heap.toString());
		}
	}

	@Test
	public void testComparator() {
		Heap <Integer> heap = new Heap<> (new Comparator <Integer>() {
			public int compare (Integer o1, Integer o2) {
				return o2.compareTo (o1);
			}
		});
		System.out.println (heap.toString());

		for (Integer i : new Integer[] {9, 4, 2, 6, 8, 1, 0, 3, 7, 5}) {
			heap.add (i);
			System.out.println (i + " : " + heap.toString ());
		}

		System.out.println ("---------------------------");

		for (Integer i = heap.remove(); i != null; i = heap.remove()) {
			System.out.println (i + " : " + heap.toString());
		}
	}
}