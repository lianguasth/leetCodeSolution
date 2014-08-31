package binTree;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	/**
	 * Definition for binary tree public class TreeNode { int val; TreeNode
	 * left; TreeNode right; TreeNode(int x) { val = x; left = null; right =
	 * null; } }
	 */

	public List<TreeNode> generateTrees(int n) {
		if(n == 0){
			List<TreeNode> t = new ArrayList<TreeNode>();
			t.add(new TreeNode(0));
			System.out.println(t);
			return t;
		}
		calTree(n);
		return makeTreesFromSerializedTrees();
	}

	
	List<Integer> numberOfTrees;
	List<List<List<Integer>>> history;
	/* 
	 * try to use the thinking of dynamic programming to the best
	 */
	public void initNumber(int n){
		numberOfTrees = new ArrayList<Integer>();
		numberOfTrees.add(1);
		for(int i = 1; i <= n; i++){
			int total = 0;
			for(int j = 0; j < i; j++){
				int left = numberOfTrees.get(j);
				int right = numberOfTrees.get(i-j-1);
				total+=left*right;
			}
			numberOfTrees.add(total);
		}
		System.out.println(numberOfTrees);
	}
	/** 
	 * apply dynamic programming method to solve the problem
	 * calculate every form of tree with element size k(k < n)
	 * save form into history and reuse it next time!
	 * @param n
	 * the number of elements in the tree
	 */
	public void calTree(int n){
		history = new ArrayList<List<List<Integer>>>();
		//add zero
		List<Integer> zeroList = new ArrayList<Integer>();
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(zeroList);
		history.add(lists);
		for(int i = 1; i <= n; i++){
			List<List<Integer>> numberList= new ArrayList<List<Integer>>();
			for(int j = 0; j < i; j++){
				ArrayList<List<Integer>> leftList = (ArrayList<List<Integer>>) history.get(j);
				ArrayList<List<Integer>> rightList = (ArrayList<List<Integer>>) history.get(i-j-1);
//				System.out.println("rightList" + rightList);
				for(int p = 0; p < rightList.size(); p++){
					List<Integer> castedList = cast(rightList.get(p), j+2);
					for(int q = 0; q < leftList.size(); q++){
						List<Integer> newLeftList = new ArrayList<Integer>();
						newLeftList.addAll(leftList.get(q));
						List<Integer> newRightList = new ArrayList<Integer>();
						newRightList.addAll(castedList);		
						List<Integer> tmp = new ArrayList<Integer>();
						tmp.add(j+1);
						tmp.addAll(newLeftList);
						tmp.addAll(newRightList);
//						System.out.println(tmp);
						numberList.add(tmp);
					}
				}
			}
			history.add(numberList);
		}
	}
	
	public List<Integer> cast(List<Integer> order, int a){
		int delta = a-1;
		List<Integer> newOrder = new ArrayList<Integer>();
		for(int i = 0; i < order.size(); i++){
			newOrder.add(order.get(i)+delta);
		}
//		System.out.println("newOrder" + newOrder);
		return newOrder;
	}
	
	public void printSerializedTree(){
		for(List<List<Integer>> lists : history){
			System.out.println("when n = " + history.indexOf(lists));
			for(List<Integer> list: lists){
				System.out.println("form "+ lists.indexOf(list) +":" +list);
			}
		}
	}
	
	public List<TreeNode> makeTreesFromSerializedTrees(){
		List<List<Integer>> serializedTrees = history.get(history.size()-1);
		List<TreeNode> trees = new ArrayList<TreeNode>();
		for(List<Integer> sTree : serializedTrees){
			trees.add(makeTreeFromSerializedTree(sTree));
		}
		return trees;
//		for(TreeNode t: trees){
//			printTree(t);
//			System.out.printf("\n");
//		}
	}
	
	public TreeNode makeTreeFromSerializedTree(List<Integer> sTree){
		TreeNode root = new TreeNode(sTree.get(0));
		
		for(Integer e : sTree){
			TreeNode tmp = root;
			while(tmp != null){
				if(e < tmp.val){
					if(tmp.left == null){
						//got there
						tmp.left = new TreeNode(e);
					}else{
						tmp = tmp.left;
					}
				}else if(e > tmp.val){
					if(tmp.right == null){
						//got there
						tmp.right = new TreeNode(e);
					}else{
						tmp = tmp.right;
					}
				}else{
					break;
				}
			}
		}
		return root;
	}
	
	public void printTree(TreeNode root){
		if(root!=null){
			System.out.printf("\t%d",root.val);
			printTree(root.left);
			printTree(root.right);
		}
		//System.out.printf("\n");
	}
	
	
	public static void main(String argc[]){
		Solution s = new Solution();
		s.generateTrees(0);
//		s.initNumber(6);
//		s.calTree(5);
//		//s.printSerializedTree();
//		s.makeTreesFromSerializedTrees();
	}
	
}
