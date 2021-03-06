package 树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * @author beastars
 */
public class _145_二叉树的后序遍历 {
    private List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root, last = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) { // 左子树压栈
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode node = stack.peek(); // 查看栈顶元素
                // 如果当前节点的右节点和上一次遍历的节点相同，那就表明当前是从右节点过来的了
                if (node.right != null && node.right != last) {
                    // 如果该元素有右子树且没有被遍历过，赋给curr进行遍历
                    curr = node.right;
                } else {
                    // 如果该元素没有右子树或者被遍历过，弹出该元素，并将该元素赋给last，表示是上一次遍历的节点
                    list.add(node.val);
                    last = node;
                    stack.pop();
                }
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null)
            return list;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            boolean flag = (node == stack.peek());
            if (node.right != null && flag) {
                stack.push(node.right);
                stack.push(node.right);
            }
            if (node.left != null && flag) {
                stack.push(node.left);
                stack.push(node.left);
            }
            if (node.left == null && node.right == null) {
                stack.poll();
                list.add(node.val);
            } else if (!flag) {
                list.add(node.val);
            }
        }

        return list;
    }

    /**
     * 利用 Morris 后序遍历二叉树
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    /*public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode node = root;

        while (node != null) {
            if (node.left != null) {
                TreeNode pred = node.left; // 找到前驱节点
                while (pred.right != null && pred.right != node) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    pred.right = node;
                    node = node.left;
                } else {
                    pred.right = null;
                    node = node.right;
                }
            } else {
                node = node.right;
            }
        }

        return list;
    }*/
}
