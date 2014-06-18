package com.sneg.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Author:	sneg
 * Date:	13.09.13
 * Time:	9:18
 */
public class ArrayUtils {
	private static final Random RAND = new Random();

	/**
	 * Creates sorted array with elements from 0 to size-1
	 */
	public static int[] init (int size) {
		return create (size);
	}

	/**
	 * Creates unsorted array of size <code>size</code> having <b>unique</b> elements from 0 to <code>scope</code>
	 * @param size size of the array
	 * @param scope the maximum element in the array
	 * @return unsorted array of size <code>size</code> having elements from 0 to <code>scope</code>
	 */
	public static int[] initUnique (int size, int scope) {
		int[] array = new int [size];

		Set <Integer> created = new HashSet <Integer> (size);

		for (int i = 0; i < size; ++i) {
			int e = RAND.nextInt (scope);
			if (!created.contains (e)) {
				created.add (e);
				array [i] = e;
			}
			else {
				--i;	// Once more
			}
		}

		return array;
	}

	/**
	 * Creates unsorted array of size <code>size</code> having <b>random</b> elements from 0 to <code>scope</code>
	 * @param size size of the array
	 * @param scope the maximum element in the array
	 * @return unsorted array of size <code>size</code> having elements from 0 to <code>scope</code>
	 */
	public static int[] initRandom (int size, int scope) {
		int[] array = new int [size];

		for (int i = 0; i < size; ++i) {
			int e = RAND.nextInt (scope);
			array [i] = e;
		}

		return array;
	}

	/**
	 * Init rows*cols matrix with elements 1 <= X <= 9
	 */
	public static int[][] initMatrix (int rows, int cols) {
		int[][] matrix = new int [rows][cols];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				matrix[i][j] = RAND.nextInt (9) + 1;
			}
		}

		return matrix;
	}

	public static int[] create (int capacity) {
		return create (capacity, 0);
	}

	public static int[] create (int capacity, int shift) {
		int[] array = new int[capacity];

		for (int i = 0; i < array.length; i++) {
			array[i] = i + shift;
		}

		return array;
	}

	public static int[] shuffle (int capacity) {
		return shuffle (capacity, 0);
	}

	public static int[] shuffle (int capacity, int shift) {
		int[] array = create (capacity, shift);
		return shuffle (array);
	}

	public static int[] shuffle (int[] array) {
		for (int i = 1; i < array.length; i++) {
			int j = RAND.nextInt (i+1);
			swap (array, i, j);
		}

		return array;
	}

	public static void swap (int[] array, int i, int j) {
		int temp = array [i];
		array [i] = array [j];
		array [j] = temp;
	}

	public static String createString (int length) {
		int offset	= 97;
		int size	= 26;

		char[] chars = new char [length];

		for (int i = 0; i < length; ++i) {
			int r = RAND.nextInt (size);
			chars[i] = Character.toChars (offset + r)[0];
		}

		return String.valueOf (chars);
	}

	public static void print (int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append ('[');

		for (int i = 0; i < array.length; i++) {
			if (i > 0) sb.append (", ");
			sb.append (array[i]);
		}

		sb.append (']');
		System.out.println (sb);
	}

	public static void print (int[] array, int start, int end) {
		StringBuilder sb = new StringBuilder ();
		for (int i = start; i < end; i++) {
			sb.append (sb.length () == 0 ? "[" : ", ");
			sb.append (array[i]);
		}
		sb.append ("]");
		System.out.println (sb.toString ());
	}

	public static void print (int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print (matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static String toString (int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append ('[');

		for (int i = 0; i < array.length; i++) {
			if (i > 0) sb.append (", ");
			sb.append (array[i]);
		}

		sb.append (']');
		return sb.toString();
	}
}
