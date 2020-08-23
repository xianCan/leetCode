package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/8/23 17:33
 *
 * 1008. 先序遍历构造二叉树
 *
 *  返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。

    (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）

     

    示例：

    输入：[8,5,1,7,10,12]
    输出：[8,5,10,1,7,null,12]

     

    提示：

    1 <= preorder.length <= 100
    先序 preorder 中的值是不同的。

 */
public class LeetCode1008 {

    private int idx=0;

    /**
     * 递归：维护一个二元组(lower, upper)代表当前节点可以插入的值的上下界
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);

    }

    private TreeNode helper(int lower, int upper, int[] preorder) {
        if (idx == preorder.length) return null;

        int val = preorder[idx];
        if (val < lower || val > upper) return null;

        idx++;
        TreeNode root = new TreeNode(val);
        root.left = helper(lower, val, preorder);
        root.right = helper(val, upper, preorder);
        return root;
    }

    /**
     * 迭代：使用一个栈模拟递归的过程
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder2(int[] preorder){
        if (preorder == null || preorder.length == 0) return null;
        int length = preorder.length;

        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (int i=1; i<length; i++){
            TreeNode node = stack.peek();
            TreeNode child = new TreeNode(preorder[i]);

            while (!stack.isEmpty() && stack.peek().val < child.val){
                node = stack.pop();
            }

            if (node.val < child.val){
                node.right = child;
            } else {
                node.left = child;
            }
            stack.push(child);
        }
        return root;
    }

    /**
     * 方法三：先对先序遍历排序形成中序遍历，然后根据先序遍历和中序遍历确定一棵树
     * 时间复杂度：O(N log N)，主要是排序需要。
     * 空间复杂度：O(N)
     */
}
