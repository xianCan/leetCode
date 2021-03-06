package leetCode;

/**
 * @author xianCan
 * @date 2020/8/23 19:44
 *
 * 105. 从前序与中序遍历序列构造二叉树（中等）
 *
 *  根据一棵树的前序遍历与中序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
    返回如下的二叉树：

    3
    / \
    9  20
    /  \
    15   7

 */
public class LeetCode105 {

    /*private int idx=0;
    private int length;
    private int[] preorder;
    private Map<Integer, Integer> map;

    *//**
     * 递归
     * @param preorder
     * @param inorder
     * @return
     *//*
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.length = preorder.length;
        this.preorder = preorder;
        this.map = new HashMap<>();
        for (int i=0; i<inorder.length; i++){
            map.put(inorder[i], i);
        }
        return helper(0, inorder.length-1);
    }

    private TreeNode helper(int i1, int i2){
        if (idx == length) return null;
        if (i1 > i2) return null;

        TreeNode root = new TreeNode(preorder[idx]);
        int mid=map.get(root.val);

        idx++;
        root.left = helper(i1, mid-1);
        root.right = helper(mid+1, i2);
        return root;
    }*/

    private int[] preorder;
    private int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return helper(0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode helper(int preStart, int preEnd, int inStart, int inEnd){
        if (inStart > inEnd){
            return null;
        }

        int index = 0;
        for (int i=inStart; i<=inEnd; i++){
            if (preorder[preStart] == inorder[i]){
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;

        TreeNode node = new TreeNode(preorder[preStart]);
        node.left = helper(preStart+1, preStart+leftSize, inStart, inStart+leftSize-1);
        node.right = helper(preStart+leftSize+1, preEnd, inStart+leftSize+1, inEnd);
        return node;
    }
}
