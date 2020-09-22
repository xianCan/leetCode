package leetCode;

/**
 * @author xianCan
 * @date 2020/9/22 21:51
 *
 * 106. 从中序与后序遍历序列构造二叉树（中等）
 *
 *  根据一棵树的中序遍历与后序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    中序遍历 inorder = [9,3,15,20,7]
    后序遍历 postorder = [9,15,7,20,3]
    返回如下的二叉树：

    3
    / \
    9  20
    /  \
    15   7

 */
public class LeetCode106 {

    private int[] inorder;
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return helper(0, inorder.length-1, 0, postorder.length-1);
    }

    private TreeNode helper(int inStart, int inEnd, int postStart, int postEnd){
        if (inStart > inEnd){
            return null;
        }

        int index=0;
        for (int i=inStart; i<=inEnd; i++){
            if (inorder[i] == postorder[postEnd]){
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;

        TreeNode node = new TreeNode(postorder[postEnd]);
        node.left = helper(inStart, inStart+leftSize-1, postStart, postStart+leftSize-1);
        node.right = helper(inStart+leftSize+1, inEnd, postStart+leftSize, postEnd-1);
        return node;
    }
}
