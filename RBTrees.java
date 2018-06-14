package com.wangguowei.properties;

public class RBTrees<T extends Comparable<T>> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private static class RBNode<T extends Comparable<T>> {
		T value;
		RBNode<T> parent;
		RBNode<T> lChild;
		RBNode<T> rChild;
		boolean color;

		public RBNode(T value) {
			this.value = value;
			this.color = RED;
			this.parent = null;
			this.lChild = null;
			this.rChild = null;
		}

		@Override
		public String toString() {
			if (this.color == RED) {
				return "(RED," + this.value + ")";
			} else {
				return "(BLACK," + this.value + ")";
			}
		}
	}

	RBNode<T> root;

	public RBTrees() {
		root = null;
	}

	/**
	 * ���� �����Ĺ����ǽ�x����������x��ʱ����ת��ʹ��x����������Ϊx�ĸ��� ����ʾ��ͼ(�Խڵ�x��������)�� px px / / x y / \
	 * --(x����)-. / \ # lx y x ry / \ / \ ly ry lx ly
	 *
	 * 
	 * @param node
	 */
	private void leftRotate(RBNode<T> node) {
		RBNode<T> rNode = node.rChild;
		node.rChild = rNode.lChild;
		if (rNode.lChild != null) {
			rNode.lChild.parent = node;
		}
		rNode.parent = node.parent;
		if (node == root) {
			this.root = rNode;
		} else {
			if (node.parent.lChild == node) {
				node.parent.lChild = rNode;
			} else {
				node.parent.rChild = rNode;
			}
		}
		rNode.lChild = node;
		node.parent = rNode;
	}

	/**
	 * ���� �����Ĺ����ǽ�x����������x˳ʱ����ת��ʹ��x����������Ϊx�ĸ��� * ����ʾ��ͼ(�Խڵ�y��������)�� py py / / y x / \
	 * --(y����)-. / \ x ry lx y / \ / \ lx rx rx ry
	 * 
	 * @param node
	 */
	private void rightRotate(RBNode<T> node) {
		RBNode<T> lNode = node.lChild;
		node.lChild = lNode.rChild;
		if (lNode.rChild != null) {
			lNode.rChild.parent = node;
		}
		lNode.parent = node.parent;
		if (node == root) {
			root = lNode;
		} else {
			if (node.parent.lChild == node) {
				node.parent.lChild = lNode;
			} else {
				node.parent.rChild = lNode;
			}
		}
		node.parent = lNode;
		lNode.rChild = node;
	}

	/**
	 * ������Ĳ��룺���ܻᵼ�����Ĳ�ƽ�⣬��ʱ����Ҫ����������ת��������ɫ�޸����������Ʋ����޸���
	 * �²���Ľڵ��Ǻ�ɫ�ģ������޸���������������ڵ����ɫΪ�����޸�����������Ҳ����˵��ֻ���ڸ��ڵ�Ϊ��ɫ�ڵ��ʱ������Ҫ�����޸������ġ�
	 * �����޸�������Ϊ���µ���������������²���Ľڵ�ĸ��ڵ㶼�Ǻ�ɫ�ģ� ����ڵ�ҲΪ��ɫ�� ����ڵ�Ϊ�գ����游�ڵ㡢���ڵ���½ڵ㴦��һ��б���ϡ�
	 * ����ڵ�Ϊ�գ����游�ڵ㡢���ڵ���½ڵ㲻����һ��б����
	 * 
	 * @param node
	 */
	public void insert(T value) {
		if (root == null) {
			root = new RBNode<T>(value);
			root.color = BLACK;
		} else {
			RBNode<T> node = root;
			RBNode<T> parent = node;
			while (node != null) {
				parent = node;
				if (value.compareTo(node.value) > 0) {
					node = node.rChild;
				} else if (value.compareTo(node.value) < 0) {
					node = node.lChild;
				}
			}
			RBNode<T> target = new RBNode<T>(value);
			target.parent = parent;
			if (value.compareTo(parent.value) > 0) {
				parent.rChild = target;
			}
			if (value.compareTo(parent.value) < 0) {
				parent.lChild = target;
			}
			insertFixUp(target);
		}
	}

	// �����޸�
	private void insertFixUp(RBNode<T> target) {
		while (target.parent != null && target.parent.color == RED) {
			RBNode<T> parent = target.parent;
			RBNode<T> gparent = target.parent.parent;
			// ������ڵ�Ϊ�游�ڵ������
			if (gparent.lChild == parent) {
				RBNode<T> uncle = gparent.rChild;
				// �������ڵ����ɫΪ��ɫ,������ڵ㣬���ڵ����ɫ��Ϊ��ɫ���游�ڵ���ɫ��Ϊ��ɫ
				if (uncle != null && uncle.color == RED) {
					parent.color = BLACK;
					uncle.color = BLACK;
					gparent.color = RED;
					target = gparent;
				} else /* �������ڵ�Ϊ��ɫ */ {
					// ���target�ڵ�Ϊparent�ڵ���Һ��ӣ��ȶ�parent�ڵ��������ʹ��gparetn��parent��targetλ��һ��(���)�ڵ�
					if (target == parent.rChild) {
						leftRotate(parent);
						target = parent;
					}
					target.parent.color = BLACK;
					gparent.color = RED;
					rightRotate(gparent);
				}
			} else/* ������ڵ�Ϊ�游�ڵ���Һ��� */ {
				RBNode<T> uncle = gparent.lChild;
				if (uncle != null && uncle.color == RED) {
					parent.color = BLACK;
					uncle.color = BLACK;
					gparent.color = RED;
					target = gparent;
				} else {
					if (target == parent.lChild) {
						rightRotate(target.parent);
						target = parent;
					}
					target.parent.color = BLACK;
					gparent.color = RED;
					leftRotate(gparent);
				}
			}
		}
		root.color = BLACK;
	}

	// �ݹ�ǰ�����
	public void preorder(RBNode<T> node) {
		if (node != null) {
			System.out.print("(" + node.color + "," + node.value + ")->");
			preorder(node.lChild);
			preorder(node.rChild);
		}
	}

	// �ݹ��������
	public void inorder(RBNode<T> node) {
		if (node != null) {
			inorder(node.lChild);
			System.out.print("(" + node.color + "," + node.value + ")->");
			inorder(node.rChild);
		}
	}

	// �ݹ�������
	public void postorder(RBNode<T> node) {
		if (node != null) {
			postorder(node.lChild);
			postorder(node.rChild);
			System.out.print("(" + node.color + "," + node.value + ")->");
		}
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
	public void traversal(RBNode<T> node, int i) {
		if (node != null) {
			if (i == 1) {
				System.out.print(node + "->");
			} else {
				traversal(node.lChild, i - 1);
				traversal(node.rChild, i - 1);
			}
		}
	}

	// �ݹ�ʵ�ּ������ĸ߶�
	public int heigh(RBNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return Math.max(heigh(node.lChild) + 1, heigh(node.rChild) + 1);
		}
	}

	// ����node�ڵ�ĺ�̽ڵ�
	public RBNode<T> successor(RBNode<T> node) {
		if (node == null) {
			return null;
		} else {
			RBNode<T> rNode = node.rChild;
			if (rNode != null) {
				while (rNode.lChild != null) {
					rNode = rNode.lChild;
				}
				return rNode;
			} else {
				RBNode<T> parent = node.parent;
				while (parent != null && parent.rChild == node) {
					node = parent;
					parent = parent.parent;
				}
				return parent;
			}
		}
	}

	// ����node�ڵ��ǰ���ڵ�
	public RBNode<T> precursor(RBNode<T> node) {
		if (node == null) {
			return null;
		} else {
			RBNode<T> lNode = node.lChild;
			if (lNode != null) {
				while (lNode.rChild != null) {
					lNode = lNode.rChild;
				}
				return lNode;
			} else {
				RBNode<T> parent = node.parent;
				while (parent != null && parent.lChild == node) {
					node = parent;
					parent = parent.parent;
				}
				return parent;
			}
		}
	}

	// ����ֵΪitem�Ľڵ�
	private RBNode<T> search(T item) {
		RBNode<T> node = root;
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

	private void delete(RBNode<T> node) {
		if (node == null) {
			return;
		}
		if (node.lChild != null && node.rChild != null) {
			RBNode<T> nextNode = successor(node);
			node.value = nextNode.value;
			node = nextNode;
		}
		RBNode<T> tmp = (node.lChild != null) ? node.lChild : node.rChild;
		if (tmp != null) {
			tmp.parent = node.parent;
			if (node == root) {
				root = tmp;
			} else {
				if (node.parent.lChild == node) {
					node.parent.lChild = tmp;
				}
				if (node.parent.rChild == node) {
					node.parent.rChild = tmp;
				}
			}
			node.lChild = node.rChild = node.parent = null;
			if (node.color == BLACK) {
				fixAfterDeletion(tmp);
			}
		} else {
			if (node == root) {
				root = null;
			} else {
				if (node.color = BLACK) {
					fixAfterDeletion(node);
				}
				if (node.parent.lChild == node) {
					node.parent.lChild = null;
				}
				if (node.parent.rChild == node) {
					node.parent.rChild = null;
				}
				node.parent = null;
			}
		}

	}

	// ɾ���޸�
	private void fixAfterDeletion(RBNode<T> tmp) {
		while (tmp != null && tmp.color == BLACK) {
			// ��ɾ���ڵ�Ϊ���ڵ�����ӽڵ�
			if (tmp.parent.lChild == tmp) {
				RBNode<T> parent = tmp.parent;
				// �ھӽڵ�Ϊ�ҽڵ�
				RBNode<T> sibling = parent.rChild;
				// ���һ:�ھӽڵ����ɫΪ��ɫ
				if (sibling.color == RED) {
					sibling.color = BLACK;
					parent.color = RED;
					leftRotate(parent);
					sibling = tmp.parent.rChild;
				}
				// �ڶ���������ھӽڵ�Ϊ��ɫ�����Һ��Ӿ�Ϊ��ɫ�ڵ�
				if (sibling.lChild.color == BLACK && sibling.rChild.color == BLACK) {
					sibling.color = RED;
					tmp = tmp.parent;
				} else {
					// ������������ھӽڵ�Ϊ��ɫ�����ӽڵ�Ϊ��ɫ�ڵ㣬�Һ���Ϊ��ɫ�ڵ�
					if (sibling.rChild.color == BLACK) {
						sibling.color = RED;
						sibling.lChild.color = BLACK;
						rightRotate(sibling);
						sibling = tmp.parent.rChild;
					}
					// ������������ھӽڵ�Ϊ��ɫ���Һ��ӽڵ���ɫΪ��ɫ
					sibling.color = tmp.parent.color;
					sibling.rChild.color = BLACK;
					tmp.parent.color = BLACK;
					leftRotate(tmp.parent);
					tmp = root;
				}
			} else /* ��ɾ���ڵ�Ϊ���ڵ���Һ��ӽڵ� */ {
				RBNode<T> parent = tmp.parent;
				// �ھӽڵ�Ϊ��ڵ�
				RBNode<T> sibling = parent.lChild;
				if (sibling.color == RED) {
					sibling.color = BLACK;
					parent.color = RED;
					rightRotate(parent);
					sibling = tmp.parent.lChild;
				}
				if (sibling.lChild.color == BLACK && sibling.rChild.color == BLACK) {
					sibling.color = RED;
					tmp = tmp.parent;
				} else {
					if (sibling.lChild.color == BLACK) {
						sibling.color = RED;
						sibling.rChild.color = BLACK;
						leftRotate(sibling);
						sibling = tmp.parent.lChild;
					}
					sibling.color = tmp.parent.color;
					sibling.lChild.color = BLACK;
					tmp.parent.color = BLACK;
					rightRotate(tmp.parent);
					tmp = root;
				}
			}
		}
		tmp.color = BLACK;
	}

	public static void main(String[] args) {
		RBTrees<Integer> trees = new RBTrees<Integer>();
		trees.insert(20);
		trees.insert(16);
		trees.order();
		trees.insert(12);
		trees.order();
		trees.insert(9);
		trees.insert(10);
		trees.insert(8);
		trees.order();
		RBNode<Integer> node=trees.search(9);
		trees.delete(node);
		trees.order();
	}
}
