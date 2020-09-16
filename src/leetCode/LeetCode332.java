package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/8/27 20:34
 *
 * 332. 重新安排行程（中等）
 *
 *  给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。

    说明:

    如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
    所有的机场都用三个大写字母表示（机场代码）。
    假定所有机票至少存在一种合理的行程。
    示例 1:

    输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
    示例 2:

    输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。

 */
public class LeetCode332 {

    private Map<String, PriorityQueue<String>> map = new HashMap<>();
    private List<String> list = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets){
            String key = ticket.get(0);
            String value = ticket.get(1);
            if (!map.containsKey(key)){
                map.put(key, new PriorityQueue<>());
            }
            map.get(key).offer(value);
        }

        dfs("JFK");
        Collections.reverse(list);
        return list;
    }

    private void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        list.add(curr);
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList(new String[]{"JFK","KUL"}));
        tickets.add(Arrays.asList(new String[]{"JFK","NRT"}));
        tickets.add(Arrays.asList(new String[]{"NRT","JFK"}));
        List<String> itinerary = new LeetCode332().findItinerary(tickets);
        for (String str : itinerary){
            System.out.println(str);
        }
    }
}
