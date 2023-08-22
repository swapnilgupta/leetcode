package dp;

import tree.nodeTypes.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTrees {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if(n % 2 == 0) return ans;
        TreeNode node = new TreeNode(0);
        createPossibleFBT(n, node, ans);
        return ans;
    }

    public TreeNode createPossibleFBT(int n, TreeNode root, List<TreeNode> ans) {
        if(n == 1) {
            TreeNode node = new TreeNode(0);
            return node;
        }
        for(int i = 1; i < n - 1; i = i + 2) {
            int j = n - i;

            createPossibleFBT();
        }
    }
}
