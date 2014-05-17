package com.sneg.week1;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * Author:	sneg
 * Date:	05.05.14
 * Time:	22:25
 */
public class Inversions {
	private static long _inversions = 0;

	public static void main (String[] args) throws Exception {
		if (args.length < 1) {
			throw new IllegalStateException ("Usage: MergeSort <input file>");
		}

		int[] numbers = readInput (args[0]);
		System.out.println ("Read " + numbers.length + " numbers");
		sort (numbers);

		System.out.println ("Inversions = " + _inversions);
	}

	private static int[] readInput (String file) throws Exception {
		List<String> lines = Files.readAllLines (new File (file).toPath (), Charset.defaultCharset ());

		int[] numbers = new int[lines.size()];

		for (int i = 0; i < lines.size(); ++i) {
			numbers[i] = Integer.valueOf (lines.get (i));
		}

		return numbers;
	}

	private static int[] sort (int[] array) {
		if (array.length < 2) return array;

		int half = array.length / 2;

		int[] left = sort (Arrays.copyOfRange (array, 0, half));
		int[] right = sort (Arrays.copyOfRange (array, half, array.length));

		int[] result = new int[array.length];
		int i = 0, j = 0;

		for (int k = 0; k < array.length; ++k) {
			if (i >= left.length) {
				int b = right[j];
				result[k] = b;
				++j;
			}
			else if (j >= right.length) {
				int a = left[i];
				result[k] = a;
				++i;
			}
			else {
				int a = left[i];
				int b = right[j];

				if (a <= b) {
					result[k] = a;
					++i;
				}
				else {
					result[k] = b;
					++j;
					_inversions += left.length - i;
				}
			}
		}

		return result;
	}
}
