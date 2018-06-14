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
	 * 左旋 左旋的过程是将x的右子树绕x逆时针旋转，使得x的右子树成为x的父亲 左旋示意图(对节点x进行左旋)： px px / / x y / \
	 * --(x左旋)-. / \ # lx y x ry / \ / \ ly ry lx ly
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
	 * 右旋 右旋的过程是将x的左子树绕x顺时针旋转，使得x的左子树成为x的父亲 * 右旋示意图(对节点y进行左旋)： py py / / y x / \
	 * --(y右旋)-. / \ x ry lx y / \ / \ lx rx rx ry
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
	 * 红黑树的插入：可能会导致树的不平衡，这时就需要对树进行旋转操作和颜色修复（在这里简称插入修复）
	 * 新插入的节点是红色的，插入修复操作如果遇到父节点的颜色为黑则修复操作结束。也就是说，只有在父节点为红色节点的时候是需要插入修复操作的。
	 * 插入修复操作分为以下的三种情况，而且新插入的节点的父节点都是红色的： 叔叔节点也为红色。 叔叔节点为空，且祖父节点、父节点和新节点处于一条斜线上。
	 * 叔叔节点为空，且祖父节点、父节点和新节点不处于一条斜线上
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

	// 插入修复
	private void insertFixUp(RBNode<T> target) {
		while (target.parent != null && target.parent.color == RED) {
			RBNode<T> parent = target.parent;
			RBNode<T> gparent = target.parent.parent;
			// 如果父节点为祖父节点的左孩子
			if (gparent.lChild == parent) {
				RBNode<T> uncle = gparent.rChild;
				// 如果叔叔节点的颜色为红色,把叔叔节点，父节点的颜色设为黑色，祖父节点颜色设为红色
				if (uncle != null && uncle.color == RED) {
					parent.color = BLACK;
					uncle.color = BLACK;
					gparent.color = RED;
					target = gparent;
				} else /* 如果叔叔节点为黑色 */ {
					// 如果target节点为parent节点的右孩子，先对parent节点进行左旋使得gparetn，parent，target位于一侧(左侧)节点
					if (target == parent.rChild) {
						leftRotate(parent);
						target = parent;
					}
					target.parent.color = BLACK;
					gparent.color = RED;
					rightRotate(gparent);
				}
			} else/* 如果父节点为祖父节点的右孩子 */ {
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

	// 递归前序遍历
	public void preorder(RBNode<T> node) {
		if (node != null) {
			System.out.print("(" + node.color + "," + node.value + ")->");
			preorder(node.lChild);
			preorder(node.rChild);
		}
	}

	// 递归中序遍历
	public void inorder(RBNode<T> node) {
		if (node != null) {
			inorder(node.lChild);
			System.out.print("(" + node.color + "," + node.value + ")->");
			inorder(node.rChild);
		}
	}

	// 递归后序遍历
	public void postorder(RBNode<T> node) {
		if (node != null) {
			postorder(node.lChild);
			postorder(node.rChild);
			System.out.print("(" + node.color + "," + node.value + ")->");
		}
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

	// 递归实现计算树的高度
	public int heigh(RBNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return Math.max(heigh(node.lChild) + 1, heigh(node.rChild) + 1);
		}
	}

	// 查找node节点的后继节点
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

	// 查找node节点的前驱节点
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

	// 查找值为item的节点
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

	// 删除修复
	private void fixAfterDeletion(RBNode<T> tmp) {
		while (tmp != null && tmp.color == BLACK) {
			// 待删除节点为父节点的左孩子节点
			if (tmp.parent.lChild == tmp) {
				RBNode<T> parent = tmp.parent;
				// 邻居节点为右节点
				RBNode<T> sibling = parent.rChild;
				// 情况一:邻居节点的颜色为红色
				if (sibling.color == RED) {
					sibling.color = BLACK;
					parent.color = RED;
					leftRotate(parent);
					sibling = tmp.parent.rChild;
				}
				// 第二种情况：邻居节点为黑色且左右孩子均为黑色节点
				if (sibling.lChild.color == BLACK && sibling.rChild.color == BLACK) {
					sibling.color = RED;
					tmp = tmp.parent;
				} else {
					// 第三中情况：邻居节点为黑色且左孩子节点为红色节点，右孩子为黑色节点
					if (sibling.rChild.color == BLACK) {
						sibling.color = RED;
						sibling.lChild.color = BLACK;
						rightRotate(sibling);
						sibling = tmp.parent.rChild;
					}
					// 第四种情况：邻居节点为黑色且右孩子节点颜色为红色
					sibling.color = tmp.parent.color;
					sibling.rChild.color = BLACK;
					tmp.parent.color = BLACK;
					leftRotate(tmp.parent);
					tmp = root;
				}
			} else /* 待删除节点为父节点的右孩子节点 */ {
				RBNode<T> parent = tmp.parent;
				// 邻居节点为左节点
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
