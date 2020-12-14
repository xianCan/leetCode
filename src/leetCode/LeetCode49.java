package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/12/14 21:53
 *
 * 49. 字母异位词分组（中等）
 *
 *  给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

    示例:

    输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
    输出:
    [
    ["ate","eat","tea"],
    ["nat","tan"],
    ["bat"]
    ]
    说明：

    所有输入均为小写字母。
    不考虑答案输出的顺序。

 */
public class LeetCode49 {

    /**
     * 1、排序
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 2、计数。3、自定义hashcode和equals方法
     */
}
