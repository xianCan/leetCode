package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/10/14 23:01
 *
 * 589. N叉树的前序遍历（简单）
 *
 *  给定一个 N 叉树，返回其节点值的前序遍历。

    例如，给定一个 3叉树 :

    返回其前序遍历: [1,3,5,6,2,4]。

     

    说明: 递归法很简单，你可以使用迭代法完成此题吗?

 */
public class LeetCode589 {

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res){
        if (root == null) return;

        res.add(root.val);
        if (root.children != null){
            for (Node node : root.children){
                dfs(node, res);
            }
        }
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root){
        List<Integer> res = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }

        while (!stack.isEmpty()){
            Node node = stack.pop();
            res.add(node.val);

            List<Node> children = node.children;

            for (int i=children.size()-1; i>=0; i--){
                stack.push(children.get(i));
            }
        }

        return res;
    }
}
