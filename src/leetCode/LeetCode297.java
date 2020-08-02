package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/8/2 17:12
 *
 * 297. 二叉树的序列化与反序列化（困难）
 *
 *  序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

    请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

    示例: 

    你可以将以下二叉树：

     1
    / \
   2   3
      / \
     4   5

    序列化为 "[1,2,3,null,null,4,5]"
    提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

    说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。

 */
public class LeetCode297 {

    /**
     * 按层遍历
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        String split = ",", nullValue = "#";
        StringBuilder res= new StringBuilder();
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node == null){
                res.append(nullValue).append(split);
                continue;
            }
            queue.offer(node.left);
            queue.offer(node.right);
            res.append(node.val).append(split);
        }
        return res.toString();
    }


    public TreeNode deserialize(String data) {
        if (data == null)
            return null;
        String[] split = data.split(",");
        if (split[0] == null)
            return null;
        int length = split.length;
        TreeNode root = "#".equals(split[0]) ? null : new TreeNode(Integer.parseInt(split[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i=1;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur == null)
                continue;
            cur.left = i < length && "#".equals(split[i]) ? null : new TreeNode(Integer.parseInt(split[i]));
            cur.right = i+1 < length && "#".equals(split[i+1]) ? null : new TreeNode(Integer.parseInt(split[i+1]));
            queue.offer(cur.left);
            queue.offer(cur.right);
            i +=2;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2, node7, node6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node5, node4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(5,node2, node3);

        LeetCode297 leetCode297 = new LeetCode297();
        String serialize = leetCode297.serialize(node1);
        TreeNode deserialize = leetCode297.deserialize(serialize);
        System.out.println(deserialize);
    }
}
