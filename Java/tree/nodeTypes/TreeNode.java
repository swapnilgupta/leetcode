package tree.nodeTypes;

public class TreeNode {
	public int val;
	public TreeNode left, right, random;

	public TreeNode(int value) {
		val = value;
		left = right = random = null;
	}
}
