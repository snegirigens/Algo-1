package com.sneg.week2;

import com.sneg.utils.ArrayUtils;

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
public class QuickSort {
	public static void sort (int[] array) {
		sort (array, 0, array.length - 1, new MedianElementPivot());
	}

	private static long _comparisons = 0;

	public static void main (String[] args) throws Exception {
		String input = "D:/Courses/Algorithms-Design/Home/Week-2/QuickSort.txt";

		int[] array = readInput (input);
//		int[] array = {3, 5, 2, 9, 0, 6, 1};
//		int[] array = {1, 5, 2, 9, 0, 6, 3};
//		int[] array = {0, 1, 2, 9, 5, 6, 3};
//		int[] array = {0, 1, 2, 3, 4, 5, 6, 7};
//		int[] array = {7, 6, 5, 4, 3, 2, 1, 0};
//		int[] array = {7, 5, 6, 4, 3, 1, 2, 0};
//		int[] array = {7, 0, 6, 1, 5, 2, 4, 3};

		System.out.println ("Read " + array.length + " numbers");
		ArrayUtils.print (array);

//		sortByFirst (array, 0, array.length-1);
//		sortByLast (array, 0, array.length-1);
//		sort (array, 0, array.length - 1, new FirstElementPivot());
//		sort (array, 0, array.length - 1, new LastElementPivot());
		sort (array, 0, array.length - 1, new MedianElementPivot());

		ArrayUtils.print (array);
		System.out.println ("Comparisons = " + _comparisons);
	}

	private static int[] readInput (String file) throws Exception {
		List <String> lines = Files.readAllLines (new File (file).toPath(), Charset.defaultCharset());

		int[] numbers = new int[lines.size()];

		for (int i = 0; i < lines.size(); ++i) {
			numbers[i] = Integer.valueOf (lines.get (i));
		}

		return numbers;
	}

	private static void sort (int[] array, int start, int end, PivotProvider pivotProvider) {
		if (end - start < 1) return;

//		ArrayUtils.print (array, start, end + 1);
		_comparisons += end - start;

		int pivot = pivotProvider.getPivot (array, start, end);
		swap (array, start, pivot);
		pivot = start;
		int i = start;

		for (int j = start + 1; j <= end; ++j) {
			if (array[j] <= array[pivot]) {
				swap (array, ++i, j);
			}
		}

		swap (array, i, pivot);

		sort (array, pivot, i-1, pivotProvider);
		sort (array, i+1, end, pivotProvider);
	}

	private static void swap (int[] array, int i, int j) {
		int tmp = array[j];
		array[j] = array[i];
		array[i] = tmp;
	}

	private static interface PivotProvider {
		public int getPivot (int[] array, int start, int end);
	}

	public static class FirstElementPivot implements PivotProvider {
		public int getPivot (int[] array, int start, int end) {
			return start;
		}
	}

	public static class LastElementPivot implements PivotProvider {
		public int getPivot (int[] array, int start, int end) {
			return end;
		}
	}

	public static class MedianElementPivot implements PivotProvider {
		public int getPivot (int[] array, int start, int end) {
			int median = getMedian (start, end);
			int pivot;

			if (array[start] < array[end]) {
				pivot = array[median] < array[start] ? start : array[median] < array[end] ? median : end;
			}
			else {
				pivot = array[median] < array[end] ? end : array[median] < array[start] ? median : start;
			}

//			System.out.println ("Start=" + array[start] + ". Median=" + array[median] + ". End=" + array[end] + ". Pivot = " + array[pivot]);
			return pivot;
		}

		private int getMedian (int start, int end) {
			return start + (end - start) / 2;
		}
	}
}
