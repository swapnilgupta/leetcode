package tree;

class LockableTreeNode {

	LockableTreeNode children;
	LockableTreeNode parent;
	boolean isLocked;
	int lockedDescendants;

	public boolean isLocked() {
		return isLocked;
	}

	public boolean lock() {
		if (lockedDescendants > 0 || isLocked) {
			return false;
		}
		LockableTreeNode current = parent;
		while (current != null) {
			if (current.isLocked) {
				return false;
			}
			current = current.parent;
		}
		isLocked = true;
		current = parent;
		while (current != null) {
			current.lockedDescendants++;
			current = current.parent;
		}
		return true;
	}

	public boolean unlock() {
		if (!isLocked) {
			return false;
		}
		LockableTreeNode current = parent;
		while (current != null) {
			current.lockedDescendants--;
			current = current.parent;
		}
		isLocked = false;
		return true;
	}

}

public class LockableTree {

	/*
	Input: N = 7, M = 2, nodes = [‘World’, ‘Asia’, ‘Africa’, ‘China’, ‘India’, ‘SouthAfrica’, ‘Egypt’],
	queries =  [‘1 China 9’, ‘1 India 9’, ‘3 Asia 9’, ‘2 India 9’, ‘2 Asia 9’]
	Output: true true true false true

	 */
	public static void main(String[] args) {
		LockableTreeNode world = new LockableTreeNode();
		LockableTreeNode asia = new LockableTreeNode();
		LockableTreeNode africa = new LockableTreeNode();
		LockableTreeNode china = new LockableTreeNode();
		LockableTreeNode india = new LockableTreeNode();
		LockableTreeNode southAfrica = new LockableTreeNode();
		LockableTreeNode egypt = new LockableTreeNode();

		world.children = asia;
		asia.parent = world;
		asia.children = china;
		china.parent = asia;
		asia.children = india;
		india.parent = asia;
		world.children = africa;
		africa.parent = world;
		africa.children = southAfrica;
		southAfrica.parent = africa;
		africa.children = egypt;
		egypt.parent = africa;

		System.out.println(china.lock());
		System.out.println(india.lock());
		System.out.println(asia.lock());
		System.out.println(india.lock());
		System.out.println(asia.lock());
	}

}
