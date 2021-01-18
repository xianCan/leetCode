package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2021/1/18 20:40
 *
 * 721. 账户合并（中等）
 *
 *  给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。

    现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。

    合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。

     

    示例 1：

    输入：
    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    输出：
    [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    解释：
    第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
    第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
    可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
    ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
     

    提示：

    accounts的长度将在[1，1000]的范围内。
    accounts[i]的长度将在[1，10]的范围内。
    accounts[i][j]的长度将在[1，30]的范围内。

 */
public class LeetCode721 {

    /**
     * 并查集
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();

        Map<String, Integer> mailIdx = new HashMap<>();
        Map<String, String> mailName = new HashMap<>();
        int idx = 0;
        for (List<String> account : accounts){
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++){
                String acc = account.get(i);
                if (!mailIdx.containsKey(acc)){
                    mailIdx.put(acc, idx++);
                    mailName.put(acc, name);
                }
            }
        }

        UnionFind unionFind = new UnionFind(idx);

        for (List<String> account : accounts){
            Integer fa = mailIdx.get(account.get(1));
            for (int i = 2; i < account.size(); i++){
                unionFind.union(mailIdx.get(account.get(i)), fa);
            }
        }

        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : mailIdx.keySet()){
            int index = unionFind.find(mailIdx.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<>());
            account.add(email);
            indexToEmails.put(index, account);
        }

        for (List<String> emails : indexToEmails.values()){
            Collections.sort(emails);
            String name = mailName.get(emails.get(0));
            emails.add(0, name);
            res.add(emails);
        }

        return res;
    }

    private class UnionFind{
        private int[] parent;

        public UnionFind(int x){
            this.parent = new int[x];
            for (int i = 0; i < x; i++){
                parent[i] = i;
            }
        }

        public int find(int x){
            if (x != parent[x]){
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY){
                return;
            }

            parent[rootX] = rootY;
        }
    }
}
