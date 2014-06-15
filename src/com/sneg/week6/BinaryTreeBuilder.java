package com.sneg.week6;

/**
 * Author:	sneg
 * Date:	15.06.14
 * Time:	13:00
 */
public class BinaryTreeBuilder {
	public static BinaryTree <Long> build (long[] array) {
		BinaryTree <Long> tree = new BinaryTree<>();
		build (array, 0, array.length - 1, tree);
		return tree;
	}

	private static void build (long[] array, int start, int end, BinaryTree <Long> tree) {
		if (end - start < 0) return;

		int index = getMedian(start, end);
		tree.add (array[index]);

		build (array, start, index - 1, tree);
		build (array, index + 1, end, tree);
	}

	private static int getMedian (int start, int end) {
		return start + (end - start) / 2;
	}
}
