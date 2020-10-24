package leetCode;

/**
 * @author xianCan
 * @date 2020/10/21 22:27
 *
 * 450. 删除二叉搜索树中的节点（中等）
 *
 *  给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

    一般来说，删除节点可分为两个步骤：

    首先找到需要删除的节点；
    如果找到了，删除它。
    说明： 要求算法时间复杂度为 O(h)，h 为树的高度。

    示例:

    root = [5,3,6,2,4,null,7]
    key = 3

    5
    / \
    3   6
    / \   \
    2   4   7

    给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

    一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
    / \
    4   6
    /     \
    2       7

    另一个正确答案是 [5,2,6,null,4,null,7]。

    5
    / \
    2   6
    \   \
    4   7

 */
public class LeetCode450 {

    /**
     * 利用二叉搜索树的特性
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;                      //1.空节点直接返回
        if (root.val == key){                               // 找到相等的节点了
                                                            //2.左右孩子都为空，直接删除节点，返回null
                                                            //3.左孩子为空，返回右孩子
            if (root.left == null) {
                return root.right;
            } else if (root.right == null){                 //4.右孩子为空，返回左节点
                return root.left;
            } else {                                        //5.左右孩子都不为空，将要删除的节点的左子树放到删除节点
                                                            // 的右子树的最左节点的左孩子的位置
                TreeNode cur = root.right;
                while (cur.left != null){                   //找到右子树最左的孩子
                    cur = cur.left;
                }
                cur.left = root.left;                       //把要删除节点的左子树放到cur的左孩子的位置
                TreeNode ans = root.right;                  //root.right取代原来的root
                root.left = null;                           //用于gc
                root.right = null;                          //用于gc
                return ans;
            }

        }
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);
        return root;
    }

    /**
     * 迭代
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode2(TreeNode root, int key){
        if (root == null) return null;
        TreeNode cur=root, pre=null;

        while (cur != null){
            if (cur.val == key){
                break;
            }
            pre = cur; //记录cur的父节点，用来删除cur
            if (cur.val > key){
                cur = cur.left;
            } else if (cur.val < key){
                cur = cur.right;
            }
        }
        if (pre == null){ //头结点等于key
            return helper(root);
        } else if (pre.left != null && pre.left.val == key){ //判断是删除pre的左节点还是右节点
            pre.left = helper(cur);
        } else if (pre.right != null && pre.right.val == key){
            pre.right = helper(cur);
        }

        return root;
    }

    private TreeNode helper(TreeNode node){
        if (node == null) return null;
        if (node.right == null) return node.left;
        //将节点的左子树挂到右节点的最左孩子的左子树上
        TreeNode cur = node.right;
        while (cur.left != null){
            cur = cur.left;
        }
        cur.left = node.left;
        return node.right;
    }

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7, node6, node7);
        TreeNode node2 = new TreeNode(3, node4, node5);
        TreeNode node1 = new TreeNode(5, node2, node3);
        TreeNode node = new LeetCode450().deleteNode(node1, 5);
        System.out.println(node);
    }
}
