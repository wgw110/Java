package com.wangguowei.properties;

import java.util.Random;


public class AVLTrees<T extends Comparable<T>> {
	static class Node<T extends Comparable<T>> {
		private Node<T> lChild;
		private Node<T> rChild;
		private T value;
		private int height;

		public Node(Node<T> lChild, Node<T> rChild, T value, int height) {
			this.lChild = lChild;
			this.rChild = rChild;
			this.value = value;
			this.height = height;
		}

		public Node(T value) {
			this.value = value;
			this.lChild = null;
			this.rChild = null;
		}
	}

	Node<T> root;

	public AVLTrees() {
		root = null;
	}

	public int heigh(Node<T> node) {
		if (node == null) {
			return -1;
		} else {
			return Math.max(heigh(node.lChild) + 1, heigh(node.rChild) + 1);
		}
	}

	/**
	 * LL单旋 在结点node的左孩子结点的左子树中插入元素
	 * 
	 * @param node
	 *            node节点为失衡节点
	 * @return
	 */
	private Node<T> singleRotateLeft(Node<T> node) {
		Node<T> lNode = node.lChild;
		node.lChild = lNode.rChild;
		lNode.rChild = node;
		if (node == root) {
			root = lNode;
		}
		node.height = Math.max(heigh(node.lChild), heigh(node.rChild)) + 1;
		lNode.height = Math.max(heigh(lNode.lChild), node.height) + 1;
		return lNode;
	}

	/**
	 * RR单旋 在节点node的右孩子节点的右子树中插入元素
	 * 
	 * @param node
	 * @return
	 */
	private Node<T> singleRotateRight(Node<T> node) {
		Node<T> rNode = node.rChild;
		node.rChild = rNode.lChild;
		rNode.lChild = node;
		if (node == root) {
			root = rNode;
		}
		node.height = Math.max(heigh(node.lChild), heigh(node.rChild)) + 1;
		rNode.height = Math.max(node.height, heigh(rNode.rChild)) + 1;
		return rNode;
	}

	/**
	 * 左右双旋 在节点node的左孩子节点的右子树中插入元素
	 * 
	 * @param node
	 * @return
	 */
	private Node<T> doubleRotateWithLeft(Node<T> node) {
		node.lChild = singleRotateRight(node.lChild);
		return singleRotateLeft(node);
	}

	/**
	 * 右左双旋 在节点node的右孩子节点的左子树中插入元素
	 * 
	 * @param node
	 * @return
	 */
	private Node<T> doubleRotateWithRight(Node<T> node) {
		node.rChild = singleRotateLeft(node.rChild);
		return singleRotateRight(node);
	}

	/**
	 * 递归创建平衡二叉排序树
	 * 
	 * 在递归创建二叉树的基础上进行改进，当插入节点后，回溯从该节点到根节点的节点，如果有失衡节点，则按照相应的调整策略进行调整
	 * 
	 * @param value
	 * @param node
	 * @return
	 */
	public Node<T> insert(T value, Node<T> node) {
		if (node == null) {
			node = new Node<T>(value);
			if (root == null) {
				root = node;
			}
		} else {
			if (value.compareTo(node.value) < 0) {
				node.lChild = insert(value, node.lChild);
				if (heigh(node.lChild) - heigh(node.rChild) == 2) {
					if (value.compareTo(node.lChild.value) < 0) {
						node = singleRotateLeft(node);
					} else {
						node = doubleRotateWithLeft(node);
					}
				}
			}
			if (value.compareTo(node.value) > 0) {
				node.rChild = insert(value, node.rChild);
				if (heigh(node.rChild) - heigh(node.lChild) == 2) {
					if (value.compareTo(node.rChild.value) < 0) {
						node = doubleRotateWithRight(node);
					} else {
						node = singleRotateRight(node);
					}
				}
			}
		}
		node.height = Math.max(heigh(node.lChild), heigh(node.rChild)) + 1;
		return node;
	}

	public Node<T> delete(T value, Node<T> node) {
		if (node == null) {
			return null;
		} else {
			if (value.compareTo(node.value) < 0) {
				node.lChild = delete(value, node.lChild);
			}
			if (value.compareTo(node.value) > 0) {
				node.rChild = delete(value, node.rChild);
			}
			if (value.compareTo(node.value) == 0) {
				if (node.lChild == null || node.rChild == null) {
					node = (node.lChild != null) ? node.lChild : node.rChild;
				} else {
					Node<T> rNode = node.rChild;
					while (rNode.lChild != null) {
						rNode = rNode.lChild;
					}
					node.value = rNode.value;
					node.rChild = delete(rNode.value, node.rChild);
				}
			}
			if(node!=null) {
				if (heigh(node.rChild) - heigh(node.lChild) == 2) {
					Node<T> tmp = node.rChild;
					if (heigh(tmp.lChild) < heigh(tmp.rChild)) {
						node = singleRotateRight(node);
					} else {
						node = doubleRotateWithRight(node);
					}
				}
				if (heigh(node.lChild) - heigh(node.rChild) == 2) {
					Node<T> tmp = node.lChild;
					if (heigh(tmp.lChild) > heigh(node.rChild)) {
						node = singleRotateLeft(node);
					} else {
						node = doubleRotateWithLeft(node);
					}
				}
				node.height=Math.max(heigh(node.lChild), heigh(node.rChild))+1;
			}
		}
		return node;
	}
	
	// 遍历树的每一层节点
		public void order() {
			int height = heigh(root)+1;
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
		public void preorder(Node<T> node){
			if(node!=null) {
				System.out.print(node.value+"->");
				preorder(node.lChild);
				preorder(node.rChild);
			}
		}
		public void inorder(Node<T> node){
			if(node!=null) {
				inorder(node.lChild);
				System.out.print(node.value+"->");
				inorder(node.rChild);
			}
		}
		public void postorder(Node<T> node){
			if(node!=null) {
				postorder(node.lChild);
				postorder(node.rChild);
				System.out.print(node.value+"->");
			}
		}
	public static void main(String[] args) {
		AVLTrees<Integer> trees = new AVLTrees<>();
		trees.insert(18, trees.root);
		trees.insert(14, trees.root);
		trees.insert(20, trees.root);
		trees.insert(13, trees.root);
		trees.insert(15, trees.root);
		trees.insert(17, trees.root);
		trees.insert(16, trees.root);
		System.out.println("前序遍历");
		trees.preorder(trees.root);
		System.out.println();
		System.out.println("中序遍历");
		trees.inorder(trees.root);
		System.out.println();
		System.out.println("后序遍历");
		trees.postorder(trees.root);
		System.out.println();
		System.out.println("层次遍历");
		trees.order();
		trees.delete(13, trees.root);
		trees.order();
	}
}
