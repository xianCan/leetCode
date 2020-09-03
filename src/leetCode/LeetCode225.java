package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/9/3 21:19
 *
 * 225. 用队列实现栈（简单）
 *
 *  使用队列实现栈的下列操作：

    push(x) -- 元素 x 入栈
    pop() -- 移除栈顶元素
    top() -- 获取栈顶元素
    empty() -- 返回栈是否为空
    注意:

    你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
    你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
    你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。

 */
public class LeetCode225 {


    class MyStack {

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        private int top;

        /** Initialize your data structure here. */
        public MyStack() {
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue1.offer(x);
            this.top = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while (queue1.size() > 1) {
                top = queue1.remove();
                queue2.add(top);
            }
            Integer pop = queue1.remove();
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return pop;
        }

        /** Get the top element. */
        public int top() {
            return this.top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }
}
