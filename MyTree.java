package com.wangguowei.properties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * 二叉查找树
 * 
 * @author 王国伟
 *
 * @param <T>
 */
public class MyTree<T extends Comparable<T>> {
	Node<T> root = null;
	private Queue<Node<T>> queueFrom = new LinkedList<>();
	private Queue<Node<T>> queueTo = new LinkedList<>();
	private Stack<Node<T>> s1 = new Stack<>();
	private Stack<Node<T>> s2 = new Stack<>();

	private static class Node<T extends Comparable<T>> {
		T value;
		Node<T> lChild;
		Node<T> rChild;

		public Node(T value, Node<T> lChild, Node<T> rChild) {
			this.value = value;
			this.lChild = lChild;
			this.rChild = rChild;
		}
	}

	public MyTree(T[] array) {
		for (T item : array) {
			addNode(item);
		}
	}

	public MyTree() {
		root = null;
	}

	// 添加节点
	private void addNode(T item) {
		if (root == null) {
			root = new Node<T>(item, null, null);
			return;
		}
		if (search(item) != null) {
			return;
		} else {
			Node<T> parent = parent(item);
			if (parent == null) {
				return;
			} else {
				Node<T> node = new Node<T>(item, null, null);
				if (item.compareTo(parent.value) > 0) {
					parent.rChild = node;
				} else {
					parent.lChild = node;
				}
			}
		}
	}

	// 非递归创建二叉查找树
	public Node<T> addNode(T value, Node<T> node) {
		if (node == null) {
			node = new Node<T>(value, null, null);
			if (root == null) {
				root = node;
			}
		} else {
			if (value.compareTo(node.value) > 0) {
				node.rChild = addNode(value, node.rChild);
			}
			if (value.compareTo(node.value) < 0) {
				node.lChild = addNode(value, node.lChild);
			}
		}
		return node;
	}

	// 值为item的节点的父节点 实际上是找到一个叶子节点
	private Node<T> parent(T item) {
		Node<T> node = root;
		while (node != null) {
			Node<T> tmp = node;
			if (item.compareTo(node.value) > 0) {
				node = node.rChild;
				if (node == null) {
					return tmp;
				}
			} else {
				node = node.lChild;
				if (node == null) {
					return tmp;
				}
			}

		}
		return null;
	}

	// 递归前序遍历
	public void preorder(Node<T> node) {
		if (node != null) {
			System.out.print(node.value + "->");
			preorder(node.lChild);
			preorder(node.rChild);
		}
	}

	// 非递归前序遍历 使用栈辅助实现
	/**
	 * 前序遍历 根节点->左子树->右子树 对于树中的每一个节点都可以看作是一个根节点(只不过当叶子结点为根节点时，该节点的左右子树均为null)
	 */
	public void preorder_two() {
		if (root != null) {
			s1.clear();
			s1.push(root);
			while (!s1.isEmpty()) {
				Node<T> node = s1.pop();
				System.out.print(node.value + "->");
				if (node.rChild != null) {
					s1.push(node.rChild);
				}
				if (node.lChild != null) {
					s1.push(node.lChild);
				}
			}
			System.out.println();
		}
	}

	/**
	 * 非递归前序遍历
	 * 
	 */
	public void preorder_three() {
		if (root == null) {
			System.out.println("树为空");
			return;
		}
		Node<T> node = root;
		while (node != null || !s1.isEmpty()) {
			/*
			 * while(node!=null) { System.out.print(node.value+"->"); s1.push(node);
			 * node=node.lChild; } if(!s1.isEmpty()) { node = s1.pop(); node = node.rChild;
			 * }
			 */
			if (node != null) {
				System.out.print(node.value + "->");
				s1.push(node);
				node = node.lChild;
			} else {
				node = s1.pop();
				node = node.rChild;
			}
		}
		System.out.println();
	}

	// 递归中序遍历
	public void inorder(Node<T> node) {
		if (node != null) {
			inorder(node.lChild);
			System.out.print(node.value + "->");
			inorder(node.rChild);
		}
	}

	// 非递归中序遍历
	public void inorder_two() {
		Node<T> node = root;
		s1.clear();
		while (node != null || !s1.isEmpty()) {
			if (node != null) {
				s1.push(node);
				node = node.lChild;
			} else {
				node = s1.pop();
				System.out.print(node.value + "->");
				node = node.rChild;
			}
		}
		System.out.println();
	}

	private List<Node<T>> storeInorder() {
		Node<T> node = root;
		s1.clear();
		List<Node<T>> list=new LinkedList<>();
		while (node != null || !s1.isEmpty()) {
			if (node != null) {
				s1.push(node);
				node = node.lChild;
			} else {
				node = s1.pop();
				list.add(node);
				node = node.rChild;
			}
		}
		return list;
	}
	// 查找某个节点的后继节点
	public  Node<T> successor(T value ){
		 List<Node<T>> list=storeInorder();
		 for(int i=0;i<list.size()-1;i++) {
			 if(list.get(i).value==value ) {
				 return list.get(i+1);
			 }
		 }
		 return null;
	}
	
	// 查找某个节点的前驱节点
	public Node<T> precursor(T value){
		List<Node<T>> list=storeInorder();
		 for(int i=1;i<list.size();i++) {
			 if(list.get(i).value==value ) {
				 return list.get(i-1);
			 }
		 }
		 return null;
	}
	
	// 递归后序遍历
	public void postorder(Node<T> node) {
		if (node != null) {
			postorder(node.lChild);
			postorder(node.rChild);
			System.out.print(node.value + "->");
		}
	}

	// 非递归后序遍历
	public void postorder() {
		Node<T> node = root;
		s1.clear();
		s2.clear(); // 栈s2存储逆后序遍历的结果
		while (node != null || !s1.isEmpty()) {
			if (node != null) {
				s1.push(node);
				s2.push(node);
				node = node.rChild;
			} else {
				node = s1.pop();
				node = node.lChild;
			}
		}
		while (!s2.isEmpty()) {
			System.out.print(s2.pop().value + "->");
		}
		System.out.println();
	}

	// 遍历树的每一层节点
	public void order() {
		int height = heigh(root);
		for (int i = 1; i <= height; i++) {
			System.out.print("第" + (i) + "层节点是:");
			traversal(root, i);
			System.out.println();
		}
	}

	// 递归遍历某一层
	public void traversal(Node<T> node, int i) {
		if (node != null) {
			if (i == 1) {
				System.out.print(node.value + "->");
			} else {
				traversal(node.lChild, i - 1);
				traversal(node.rChild, i - 1);
			}
		}
	}

	// 使用单个队列实现层次遍历，无法确认每层有哪些节点
	public void levelorder() {
		queueFrom.offer(root);
		while (queueFrom.size() > 0) {
			Node<T> node = queueFrom.poll();
			System.out.print(node.value + "->");
			if (node.lChild != null) {
				queueFrom.offer(node.lChild);
			}
			if (node.rChild != null) {
				queueFrom.offer(node.rChild);
			}
		}
		System.out.println();
	}

	// 使用两个队列实现按层次遍历,可以输出每一行的内容
	public void levelorder2() {
		queueFrom.offer(root);
		int level = 1;
		while (queueFrom.size() > 0) {
			queueTo.clear();
			System.out.println("第" + (level++) + "层节点是:");
			while (queueFrom.size() > 0) {
				Node<T> node = queueFrom.poll();
				System.out.print(node.value + "->");
				if (node.lChild != null) {
					queueTo.offer(node.lChild);
				}
				if (node.rChild != null) {
					queueTo.offer(node.rChild);
				}
			}
			System.out.println();
			queueFrom.addAll(queueTo);
		}
	}

	// 查找值为item的节点
	private Node<T> search(T item) {
		Node<T> node = root;
		while (node != null) {
			if (item.compareTo(node.value) > 0) {
				node = node.rChild;
			} else if (item.compareTo(node.value) < 0) {
				node = node.lChild;
			} else {
				return node;
			}
		}
		return null;
	}

	// 删除值为value的节点 非递归实现
	/***
	 * 分三种情况：1：若删除的是叶子结点则直接删除 2：若删除的节点只有左子树或只有右子树，则由该节点的下一个左孩子节点或右孩子节点来代替被删除节点的位置
	 * 3.若删除的节点左右子树均有，那么找出此结点右子树中的最小结点，用以代替要删除的结点，然后删除此最小结点
	 * 或者找出该节点左子树中的最大节点来代替要删除的节点 注意这种情况下找到的那个左子树中的最大节点或右子树中的最小节点可能会有子节点
	 * 左子树中的最大节点对应于可能会有一个左节点，而右子树中的最小节点自然可能会有右节点 这种情况下需要把这个子节点调整到那个最大节点或者最小节点的位置
	 *
	 * @param value
	 */
	public void delete(T value) {
		Node<T> node = search(value);
		if (node != null) {
			if (node.lChild != null) {
				Node<T> rNode = node.lChild;
				Node<T> prev = node.lChild;
				while (rNode.rChild != null) {
					prev = rNode;
					rNode = rNode.rChild;
				}
				node.value = rNode.value;
				if (prev != rNode) {
					prev.rChild = rNode.lChild;
				} else {
					node.lChild = rNode.lChild;
				}
			} else {
				setParent(node);
			}
		}
	}

	// 设置target节点的父节点
	private void setParent(Node<T> target) {
		if (target == root) {
			root = root.rChild;
		} else {
			s1.clear();
			s1.push(root);
			while (!s1.isEmpty()) {
				Node<T> node = s1.pop();
				if (node.lChild != null) {
					s1.push(node.lChild);
					if (node.lChild == target) {
						node.lChild = target.rChild;
						break;
					}
				}
				if (node.rChild != null) {
					s2.push(node.rChild);
					if (node.rChild == target) {
						node.rChild = target.rChild;
						break;
					}
				}
			}
			target = null;
		}
	}

	// 递归删除
	public Node<T> delete2(Node<T> root, T value) {
		if (root == null) {
			return null;
		} else {
			if (root.value.compareTo(value) > 0) {
				root.lChild = delete2(root.lChild, value);
			}
			if (root.value.compareTo(value) < 0) {
				root.rChild = delete2(root.rChild, value);
			}
			if (root.value.compareTo(value) == 0) {
				if (root.lChild == null || root.rChild == null) {
					root = (root.lChild != null) ? root.lChild : root.rChild;
				} else {
					Node<T> tmp = root.rChild;
					while (tmp.lChild != null) {
						tmp = tmp.lChild;
					}
					root.value = tmp.value;
					root.rChild = delete2(root.rChild, tmp.value);
				}
			}
			return root;
		}
	}

	// 递归实现计算树的高度
	public int heigh(Node<T> node) {
		if (node == null) {
			return 0;
		} else {
			return Math.max(heigh(node.lChild) + 1, heigh(node.rChild) + 1);
		}
	}

	public static void main(String[] args) {
		Integer[] array = new Integer[10];
		MyTree<Integer> tree = new MyTree<>();
		tree.addNode(6);
		tree.addNode(1);
		tree.addNode(7);
		tree.addNode(5);
		tree.addNode(9);
		tree.addNode(3);
		tree.addNode(8);
		tree.addNode(10);
		tree.addNode(2);
		tree.addNode(4);
		tree.levelorder();
		tree.levelorder2();
		tree.inorder_two();
		List<Node<Integer>> list=tree.storeInorder();
		for(Node<Integer> node:list) {
			System.out.print(node.value+"->");
		}
		System.out.println();
		
	}
}
