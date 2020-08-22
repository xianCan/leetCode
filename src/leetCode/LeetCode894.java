package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/8/22 12:38
 *
 * 894. 所有可能的满二叉树（中等）
 *
 *  满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。

    返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。

    答案中每个树的每个结点都必须有 node.val=0。

    你可以按任何顺序返回树的最终列表。

     

    示例：

    输入：7
    输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
    解释：

     

    提示：

    1 <= N <= 20

 */
public class LeetCode894 {
    /**
     * 暴力递归
     * @param N
     * @return
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N == 1){
            res.add(new TreeNode(0));
            return res;
        }
        if (N % 2 == 0){
            return res;
        }

        int leftNum = 1, rightNum = N-2;

        while (rightNum > 0){
            List<TreeNode> leftTree = allPossibleFBT(leftNum);
            List<TreeNode> rightTree = allPossibleFBT(rightNum);

            for (TreeNode leftNode : leftTree){
                for (TreeNode rightNode : rightTree){
                    TreeNode root = new TreeNode(0);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }

            leftNum += 2;
            rightNum -= 2;
        }

        return res;
    }

    /**
     * 备忘录
     */
    private Map<Integer, List<TreeNode>> memo = new HashMap<>();

    /**
     * 带备忘录的递归，相当于动态规划
     * @param N
     * @return
     */
    public List<TreeNode> allPossibleFBT2(int N) {
        if (memo.containsKey(N)){
            return memo.get(N);
        }

        List<TreeNode> res = new LinkedList<>();
        if (N == 1){
            res.add(new TreeNode(0));
            return res;
        } else if (N % 2 == 1){
            int leftNum = 1, rightNum = N-2;

            while (rightNum > 0){
                List<TreeNode> leftTree = allPossibleFBT2(leftNum);
                List<TreeNode> rightTree = allPossibleFBT2(rightNum);

                for (TreeNode leftNode : leftTree){
                    for (TreeNode rightNode : rightTree){
                        TreeNode root = new TreeNode(0);
                        root.left = leftNode;
                        root.right = rightNode;
                        res.add(root);
                    }
                }

                leftNum += 2;
                rightNum -= 2;
            }
        }

        memo.put(N, res);
        return res;
    }
}
