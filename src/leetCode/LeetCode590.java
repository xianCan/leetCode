package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/10 21:02
 *
 * 590. N叉树的后序遍历（简单）
 *
 * 给定一个 N 叉树，返回其节点值的后序遍历。

    例如，给定一个 3叉树 :

    返回其后序遍历: [5,6,3,2,4,1].

     

    说明: 递归法很简单，你可以使用迭代法完成此题吗?

 */
public class LeetCode590 {
    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(Node root, List<Integer> list){
        if (root != null){
            List<Node> children = root.children;
            if (children != null){
                for (Node node : children){
                    postorder(node, list);
                }
            }
            list.add(root.val);
        }
    }

    /**
     * 迭代
     */
    public List<Integer> postorder2(Node root){
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();

        if (root == null)
            return res;
        stack.add(root);

        while (!stack.isEmpty()){
            Node node = stack.pop();
            res.addFirst(node.val);
            List<Node> children = root.children;
            if (children != null){
                for (Node item : children){
                    if (item != null){
                        stack.add(item);
                    }
                }
            }
        }
        return res;
    }
}
