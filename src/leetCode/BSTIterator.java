package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/8/17 22:18
 *
 * 173. 二叉搜索树迭代器
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

    调用 next() 将返回二叉搜索树中的下一个最小的数。

    示例：

    BSTIterator iterator = new BSTIterator(root);
    iterator.next();    // 返回 3
    iterator.next();    // 返回 7
    iterator.hasNext(); // 返回 true
    iterator.next();    // 返回 9
    iterator.hasNext(); // 返回 true
    iterator.next();    // 返回 15
    iterator.hasNext(); // 返回 true
    iterator.next();    // 返回 20
    iterator.hasNext(); // 返回 false
     

    提示：

    next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
    你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。

 */
public class BSTIterator {

    private Stack<TreeNode> stack;

    /**
     * 受控递归：先把根节点的所有左子节点入栈，然后出栈的时候判断是否存在右子节点，如果存在，将右子节点的全部左子节点入栈，然后重复该过程
     * @param root
     */
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null){
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        if (!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode tmp = node.right;
            while (tmp != null){
                stack.push(tmp);
                tmp = tmp.left;
            }
            return node.val;
        }
        return 0;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack != null && !stack.isEmpty();
    }

    /**
     * 方法二：扁平化二叉搜索树
     *
     * 初始化一个空数组来存放二叉搜索树的中序遍历，然后使用一个指针来遍历
     */
}
