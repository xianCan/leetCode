package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/9/24 20:48
 *
 * 501. 二叉搜索树中的众数（简单）
 *
 *  给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

    假定 BST 有如下定义：

    结点左子树中所含结点的值小于等于当前结点的值
    结点右子树中所含结点的值大于等于当前结点的值
    左子树和右子树都是二叉搜索树
    例如：
    给定 BST [1,null,2,2],

    1
    \
    2
    /
    2
    返回[2].

    提示：如果众数超过1个，不需考虑输出顺序

    进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

 */
public class LeetCode501 {

    private Map<Integer, Integer> map;
    private int max;
    private int len;

    /**
     * 二叉树的做法
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        this.map = new HashMap<>();
        this.max = 0;
        this.len = 0;
        helper(root);
        int[] res = new int[len];
        for (Integer key : map.keySet()){
            if (map.get(key) == max){
                res[--len] = key;
            }
        }

        return res;
    }

    private void helper(TreeNode node){
        if (node == null){
            return;
        }
        int tmp = map.getOrDefault(node.val, 0) + 1;
        if (tmp > max){
            max = tmp;
            len=1;
        } else if (tmp == max){
            len++;
        }
        map.put(node.val, tmp);
        helper(node.left);
        helper(node.right);
    }

    private List<Integer> list = new ArrayList<>();
    private int maxCount=0, count=0;
    private TreeNode pre=null;


    /**
     * 二叉搜索树的做法
     * @param root
     * @return
     */
    public int[] findMode2(TreeNode root){
        dfs(root);
        int[] ans = new int[list.size()];
        for (int i=0; i<ans.length; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(TreeNode node){
        if (node == null)return;

        dfs(node.left);
        if (pre == null){
            count=1;
        } else if (pre.val == node.val){
            count++;
        } else {
            count=1;
        }
        pre = node;

        if (count == maxCount){
            list.add(node.val);
        } else if (count > maxCount){
            maxCount = count;
            list.clear();
            list.add(node.val);
        }
        dfs(node.right);
    }
}
