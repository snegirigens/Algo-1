package com.sneg.week6;

import com.sneg.utils.ArrayUtils;
import com.sneg.utils.Heap;
import com.sneg.week2.QuickSort;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Author:	sneg
 * Date:	15.06.14
 * Time:	15:06
 */
public class MedianMaintainer {

	private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/Median.txt";		// 6231
//		private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-1.txt";		// 54
//		private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-2.txt";		// 23
//		private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-3.txt";		// 55
//		private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-4.txt";		// 148
//		private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-5.txt";		// 82
//		private static final String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-6.txt";		//

	public static void main (String[] args) throws IOException {
		int[] array = loadValues(input);
//		ArrayUtils.print(array);

		Heap <Integer> lowHeap = new Heap<> (new Comparator <Integer>() {
			public int compare (Integer int1, Integer int2) {
				return int2 - int1;
			}
		});

		Heap <Integer> highHeap = new Heap<> (new Comparator <Integer>() {
			public int compare (Integer int1, Integer int2) {
				return int1 - int2;
			}
		});

		long sumOfMedians = 0;

		for (int i = 0; i < array.length; i++) {
			sumOfMedians += getMedian (array[i], i, lowHeap, highHeap);
		}

		System.out.println ("Sum of Medians = " + (sumOfMedians));
	}

	private static int getMedian (int val, int i, Heap <Integer> lowHeap, Heap <Integer> highHeap) {
		Integer low  = lowHeap.get();
		Integer high = highHeap.get();

		if (i % 2 == 0) {	// Add to low heap
			if (high != null && high < val) {
				lowHeap.add (highHeap.remove());
				highHeap.add (val);
			}
			else {
				lowHeap.add (val);
			}
		}
		else {	// Add to high heap
			if (low != null && low > val) {
				highHeap.add (lowHeap.remove());
				lowHeap.add (val);
			}
			else {
				highHeap.add (val);
			}
		}

		int median = lowHeap.get();

//		if (i < 30) {
//			System.out.println (
//				(i+1) + ". " + lowHeap.toString() + " -- " + highHeap.toString() + " == " + median
//			);
//		}

		return median;
	}

	private static int[] loadValues (String file) throws IOException {
		List<String> lines = Files.readAllLines(new File(file).toPath(), Charset.defaultCharset());

		int[] array = new int [lines.size()];

		for (int i = 0; i < lines.size(); ++i) {
			array[i] = Integer.parseInt(lines.get(i));
		}

		Set <Integer> set = new HashSet<>();
		for (int i : array) {
			if (set.contains (i)) {
				System.out.println (i);
			}
			else {
				set.add (i);
			}
		}

		return array;
	}
}
