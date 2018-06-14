package com.wangguowei.properties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * ���������
 * 
 * @author ����ΰ
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

	// ��ӽڵ�
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

	// �ǵݹ鴴�����������
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

	// ֵΪitem�Ľڵ�ĸ��ڵ� ʵ�������ҵ�һ��Ҷ�ӽڵ�
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

	// �ݹ�ǰ�����
	public void preorder(Node<T> node) {
		if (node != null) {
			System.out.print(node.value + "->");
			preorder(node.lChild);
			preorder(node.rChild);
		}
	}

	// �ǵݹ�ǰ����� ʹ��ջ����ʵ��
	/**
	 * ǰ����� ���ڵ�->������->������ �������е�ÿһ���ڵ㶼���Կ�����һ�����ڵ�(ֻ������Ҷ�ӽ��Ϊ���ڵ�ʱ���ýڵ������������Ϊnull)
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
	 * �ǵݹ�ǰ�����
	 * 
	 */
	public void preorder_three() {
		if (root == null) {
			System.out.println("��Ϊ��");
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

	// �ݹ��������
	public void inorder(Node<T> node) {
		if (node != null) {
			inorder(node.lChild);
			System.out.print(node.value + "->");
			inorder(node.rChild);
		}
	}

	// �ǵݹ��������
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
	// ����ĳ���ڵ�ĺ�̽ڵ�
	public  Node<T> successor(T value ){
		 List<Node<T>> list=storeInorder();
		 for(int i=0;i<list.size()-1;i++) {
			 if(list.get(i).value==value ) {
				 return list.get(i+1);
			 }
		 }
		 return null;
	}
	
	// ����ĳ���ڵ��ǰ���ڵ�
	public Node<T> precursor(T value){
		List<Node<T>> list=storeInorder();
		 for(int i=1;i<list.size();i++) {
			 if(list.get(i).value==value ) {
				 return list.get(i-1);
			 }
		 }
		 return null;
	}
	
	// �ݹ�������
	public void postorder(Node<T> node) {
		if (node != null) {
			postorder(node.lChild);
			postorder(node.rChild);
			System.out.print(node.value + "->");
		}
	}

	// �ǵݹ�������
	public void postorder() {
		Node<T> node = root;
		s1.clear();
		s2.clear(); // ջs2�洢���������Ľ��
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

	// ��������ÿһ��ڵ�
	public void order() {
		int height = heigh(root);
		for (int i = 1; i <= height; i++) {
			System.out.print("��" + (i) + "��ڵ���:");
			traversal(root, i);
			System.out.println();
		}
	}

	// �ݹ����ĳһ��
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

	// ʹ�õ�������ʵ�ֲ�α������޷�ȷ��ÿ������Щ�ڵ�
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

	// ʹ����������ʵ�ְ���α���,�������ÿһ�е�����
	public void levelorder2() {
		queueFrom.offer(root);
		int level = 1;
		while (queueFrom.size() > 0) {
			queueTo.clear();
			System.out.println("��" + (level++) + "��ڵ���:");
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

	// ����ֵΪitem�Ľڵ�
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

	// ɾ��ֵΪvalue�Ľڵ� �ǵݹ�ʵ��
	/***
	 * �����������1����ɾ������Ҷ�ӽ����ֱ��ɾ�� 2����ɾ���Ľڵ�ֻ����������ֻ�������������ɸýڵ����һ�����ӽڵ���Һ��ӽڵ������汻ɾ���ڵ��λ��
	 * 3.��ɾ���Ľڵ������������У���ô�ҳ��˽���������е���С��㣬���Դ���Ҫɾ���Ľ�㣬Ȼ��ɾ������С���
	 * �����ҳ��ýڵ��������е����ڵ�������Ҫɾ���Ľڵ� ע������������ҵ����Ǹ��������е����ڵ���������е���С�ڵ���ܻ����ӽڵ�
	 * �������е����ڵ��Ӧ�ڿ��ܻ���һ����ڵ㣬���������е���С�ڵ���Ȼ���ܻ����ҽڵ� �����������Ҫ������ӽڵ�������Ǹ����ڵ������С�ڵ��λ��
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

	// ����target�ڵ�ĸ��ڵ�
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

	// �ݹ�ɾ��
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

	// �ݹ�ʵ�ּ������ĸ߶�
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
