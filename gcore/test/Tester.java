package gcore.test;

import java.util.ArrayList;
import java.util.Random;

import gcore.bits.Bit;
import gcore.bits.BitSequence;
import gcore.struct.trees.AbstractBinaryTree;

public class Tester {
	public static void main(String[] args) {

		// LazySequence<Integer> range = LazySequences.range(500);
		//
		// // make two graphs with disjoint elements
		// UndirectedGraph<Integer> a =
		// UndirectedGraphs.createNull(range.filter(i -> i < 250).toArray(new
		// Integer[0]));
		// UndirectedGraph<Integer> b =
		// UndirectedGraphs.createNull(range.filter(i -> i >= 250).toArray(new
		// Integer[0]));
		//
		// UndirectedGraph<Integer> graph = UndirectedGraphs.join(a, b);
		//
		// System.out.println("First Test Done");
		//
		// System.out.println(graph.isConnected());
		
		BitSequence x = new BitSequence(new byte[] { 0, 4 });

		StringBuilder sb = new StringBuilder();
		for (Bit current : x) {
			sb.append(current.toString());
		}

		System.out.println(sb.reverse());
	}

	public static int hash(int[] array) {
		int results = 0;
		for (int i : array) {
			results += i;
			results += 1234567;
		}

		return results;
	}

	public static int fibonacci(int a) {
		if (a == 1 || a == 2)
			return 1;
		return fibonacci(a - 1) + fibonacci(a - 2);
	}

	public static double treeScorer(AbstractBinaryTree<Integer> tree) {
		int total = 0;
		for (int i = 0; i < 1000000; i++) {
			tree.clear();
			randomFill(tree, 63);
			int depth = tree.depth();
			total += depth;
		}

		tree.clear();
		double score = 1 / (total / 1000000.0 / 5);

		return score;
	}

	public static void randomFill(AbstractBinaryTree<Integer> tree, int maxValue) {
		ArrayList<Integer> range = new ArrayList<>();
		for (int i = 0; i < maxValue; i++) {
			range.add(i);
		}

		Random r = new Random();
		for (int i = 0; i < maxValue; i++) {
			Integer next = range.get(r.nextInt(range.size()));
			range.remove(next);
			tree.add(next);
		}
	}
}
