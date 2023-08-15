package composite;

import java.util.Stack;

public class Composite {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1st");
        stack.push("2nd");
        stack.push("3rd");

        stack.add(0, "4th");

        System.out.println(stack.pop());
    }
}
