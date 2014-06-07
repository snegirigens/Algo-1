package com.sneg.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTest {
	@Test
	public void test() {
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
}