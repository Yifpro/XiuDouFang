package com.example.administrator.xiudoufang.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/8/22
 */

public class ExpressionUtils {

    private HashMap<String, Integer> map;
    {
        map = new HashMap<>();
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("(", 0);
        map.put(")", 0);
    }
    private Stack<String> s1 = new Stack<>(); //定义运算符栈
    private Stack<String> s2 = new Stack<>(); //定义操作数栈
    private static volatile ExpressionUtils instance;

    public static ExpressionUtils getInstance() {
        if (instance == null) {
            synchronized (ExpressionUtils.class) {
                if (instance == null) {
                    instance = new ExpressionUtils();
                }
            }
        }
        return instance;
    }

    public String caculate(String expression) {
        return suffixToAnswer(infixToSuffix(expression));
    }

    private String suffixToAnswer(Stack<String> suffix) {
        s1.clear();
        s2.clear();
        for (int i = 0; i < suffix.size(); i++) {
            String s = suffix.get(i);
            if (isNumber(s)) {
                if (s2.size() > 1 && s1.size() > 0) {
                    caculate(s, s2);
                } else {
                    s2.push(s);
                }
            } else {
                //******** 压入的是符号，则先判断 s4 是否已存在两个数字可用于计算 ********
                //******** 是->计算结果并将结果压入 s4 ********
                //******** 否->压入 s3 ********
                if (s2.size() > 1) {
                    caculate(s, s2);
                } else {
                    s1.push(s);
                }
            }
        }
        String result = s2.pop();
        int index = result.indexOf(".");
        if (index != -1) {
            result = result.substring(0, index);
        }
        return result;
    }

    private void caculate(String operator, Stack<String> s2) {
        BigDecimal previous = new BigDecimal(s2.pop().toString());
        BigDecimal latter = new BigDecimal(s2.pop().toString());
        double result = 0;
        switch (operator) {
            case "+":
                result = latter.add(previous).doubleValue();
                break;
            case "-":
                result = latter.subtract(previous).doubleValue();
                break;
            case "*":
                result = latter.multiply(previous).doubleValue();
                break;
            case "/":
                try {
                    result = latter.divide(previous, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        s2.push(String.valueOf(result));
    }

    private Stack<String> infixToSuffix(String str) {
        s1.clear();
        s2.clear();
        //******** 分解计算表达式 ********
        List<String> list = splitExpression(str);
        for (String s : list) {
            if (isNumber(s)) {
                //******** 将数字压入 s2 ********
                s2.push(s);
            } else if (s.equals(")")) {
                //******** 将括号中的符号压入 s2 ********
                int last = s1.lastIndexOf("(");
                for (int i = s1.size() - 1; i >= last; i--) {
                    String pop = s1.pop();
                    if (!"(".equals(pop) && !")".equals(pop)) {
                        s2.push(pop);
                    }
                }
            } else if (s.equals("(")) {
                s1.push(s);
            } else {
                //******** 如果 s1 已存在符号且优先级高于或等于即将压入的符号，则先弹出并压入到 s2 ********
                if (s1.size() > 0) {
                    String peek = s1.peek();
                    if (map.get(peek) >= map.get(s)) {
                        s2.push(s1.pop());
                    }
                }
                s1.push(s);
            }
        }
        //******** 将 s1 压入 s2 ********
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        Stack<String> s3 = (Stack<String>) s2.clone();
        return s3;
    }

    private List<String> splitExpression(String s) {
        char[] array = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (isComponentOfNumber(array[i])) {
                sb.append(array[i]);
                if (i == array.length - 1) {
                    list.add(sb.toString());
                } else if (i + 1 < array.length && !isComponentOfNumber(array[i + 1])) {
                    list.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                list.add(String.valueOf(array[i]));
            }
        }
        return list;
    }

    private boolean isOperator(String operator) {
        boolean isOperator = false;
        switch (operator) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "(":
            case ")":
                isOperator = true;
                break;
        }
        return isOperator;
    }

    private boolean isComponentOfNumber(char c) {
        return c >= '0' && c <= '9' || c == '.';
    }

    private boolean isNumber(String num) {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
        return pattern.matcher(num).matches();
    }
}
