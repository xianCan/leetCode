package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianCan
 * @date 2021/7/31 17:10
 *
 * 863. 二叉树中所有距离为 K 的结点（中等）
 *
 *  给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

    返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。

     

    示例 1：

    输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
    输出：[7,4,1]
    解释：
    所求结点为与目标结点（值为 5）距离为 2 的结点，
    值分别为 7，4，以及 1



    注意，输入的 "root" 和 "target" 实际上是树上的结点。
    上面的输入仅仅是对这些对象进行了序列化描述。
     

    提示：

    给定的树是非空的。
    树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
    目标结点 target 是树上的结点。
    0 <= K <= 1000.

 */
public class LeetCode863 {

    private Map<TreeNode, TreeNode> map;
    private int k;
    private List<Integer> res;

    //建立父节点索引
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.map = new HashMap<>();
        this.k = k;
        this.res = new ArrayList<>();
        dfs(root, null);
        dfs(target, null,0);
        return res;
    }

    private void dfs(TreeNode node, TreeNode last, int dis){
        if (node == null){
            return;
        } else if (dis == k){
            res.add(node.val);
            return;
        } else if (dis > k){
            return;
        }

        TreeNode father = map.get(node);
        if (father != last){
            dfs(map.get(node), node,dis + 1);
        }

        if (node.left != last){
            dfs(node.left, node, dis + 1);
        }

        if (node.right != last){
            dfs(node.right, node, dis + 1);
        }
    }

    private void dfs(TreeNode node, TreeNode father){
        if (node == null){
            return;
        }
        this.map.put(node, father);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}
