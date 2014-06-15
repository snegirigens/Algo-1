package com.sneg.week6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Author:	sneg
 * Date:	14.06.14
 * Time:	9:29
 */
public class TwoSum {
	private static long[] SUMS = new long[] {-10000, 10000};

	public static void main (String[] args) throws IOException {
		long start = System.currentTimeMillis();

		String input = "D:/Courses/Algorithms-Design/Home/Week-6/algo1-programming_prob-2sum.txt";	// Found: 427 targets (7734 ms.)
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test-1.txt";
//		String input = "D:/Courses/Algorithms-Design/Home/Week-6/test-2.txt";

		BinaryTree <Long> numbers = loadValues (input);
//		System.out.println ("Loaded: " + numbers.toString());
//		System.out.println ("Min = " + numbers.min() + ". Max = " + numbers.max());

		Set <Long> targets = new TreeSet<>();

		for (Long number = numbers.min(); number != null; number = numbers.next (number)) {
			long low  = SUMS[0] - number;
			long high = SUMS[1] - number;

			for (Long target = numbers.next (low - 1); target != null && target <= high; target = numbers.next (target)) {
				if (!number.equals (target)) {
					targets.add (number + target);
				}
			}
		}

//		System.out.println (targets);

		long end = System.currentTimeMillis();
		System.out.println ("\nFound: " + targets.size() + " targets (" + (end - start) + " ms.)");
	}

	private static BinaryTree <Long> loadValues (String file) throws IOException {
		List <String> lines = Files.readAllLines(new File(file).toPath(), Charset.defaultCharset());

		long[] array = new long [lines.size()];

		for (int i = 0; i < lines.size(); ++i) {
			array[i] = Long.parseLong (lines.get (i));
		}

		return BinaryTreeBuilder.build (array);
	}
}
