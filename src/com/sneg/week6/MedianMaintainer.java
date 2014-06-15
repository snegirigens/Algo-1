package com.sneg.week6;

import com.sneg.utils.ArrayUtils;
import com.sneg.utils.Heap;
import com.sneg.week2.QuickSort;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
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

	public static void main (String[] args) throws IOException {
		long start = System.currentTimeMillis();

		String input = "D:/Courses/Algorithms-Design/Home/Week-6/Median.txt";		// 6231
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-1.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-2.txt";		// 23
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-3.txt";		// 148
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-4.txt";		// 82 or 84?
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-5.txt";		//
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test2-6.txt";		//

		int[] array = loadValues (input);
		ArrayUtils.print (array);

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

		int[] medians = new int [array.length];
		long sumOfMedians = 0;

		for (int i = 0; i < array.length; i++) {
			int val = array[i];
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
				medians[i] = lowHeap.get();
			}
			else {	// Add to high heap
				if (low != null && low > val) {
					highHeap.add (lowHeap.remove());
					lowHeap.add (val);
				}
				else {
					highHeap.add (val);
				}
				medians[i] = highHeap.get();
			}

//			medians[i] = lowHeap.get();
			sumOfMedians += medians[i];
//			sumOfMedians = (sumOfMedians + medians[i]) % 10000;

//			if (i < 20) {
//				int[] copy = new int [i + 1];
//
//				for (int j = 0; j <= i; ++j) {
//					copy[j] = array[j];
//				}
//
//				QuickSort.sort (copy);
//
//				ArrayUtils.print(copy);
//				System.out.println("Median = " + medians[i] + " (" + copy[(i + 1)/2] + ")");
//			}
		}

		ArrayUtils.print (medians);

		int sum = 0;
		for (int median : medians) {
			sum += median;
		}

		long end = System.currentTimeMillis();
		System.out.println ("\nSum of Medians = " + sumOfMedians + " (" + (end - start) + " ms.) " + sum);
		System.out.println ("Num of medians = " + medians.length);

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
