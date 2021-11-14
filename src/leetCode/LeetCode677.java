package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2021/11/14 21:23
 *
 * 677. 键值映射（中等）
 *
 *  实现一个 MapSum 类，支持两个方法，insert 和 sum：

    MapSum() 初始化 MapSum 对象
    void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
    int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
     

    示例：

    输入：
    ["MapSum", "insert", "sum", "insert", "sum"]
    [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
    输出：
    [null, null, 3, null, 5]

    解释：
    MapSum mapSum = new MapSum();
    mapSum.insert("apple", 3);
    mapSum.sum("ap");           // return 3 (apple = 3)
    mapSum.insert("app", 2);
    mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
     

    提示：

    1 <= key.length, prefix.length <= 50
    key 和 prefix 仅由小写英文字母组成
    1 <= val <= 1000
    最多调用 50 次 insert 和 sum

 */
public class LeetCode677 {

    /**
     * 暴力
     */
    class MapSum {
        Map<String, Integer> map;

        public MapSum() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key,val);
        }

        public int sum(String prefix) {
            int res = 0;
            for (String s : map.keySet()) {
                if (s.startsWith(prefix)) {
                    res += map.get(s);
                }
            }
            return res;
        }
    }

    class Node {
        int val;
        Node[] next = new Node[26];
    }

    /**
     * 前缀树
     */
    class MapSum2{
        Node root;
        Map<String, Integer> map;

        public MapSum2() {
            root = new Node();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            Node node = root;
            for (char ch : key.toCharArray()){
                if (node.next[ch - 'a'] == null){
                    node.next[ch - 'a'] = new Node();
                }
                node = node.next[ch - 'a'];
                node.val += delta;
            }
        }

        public int sum(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()){
                if (node.next[ch - 'a'] == null){
                    return 0;
                }
                node = node.next[ch - 'a'];
            }
            return node.val;
        }
    }
}
