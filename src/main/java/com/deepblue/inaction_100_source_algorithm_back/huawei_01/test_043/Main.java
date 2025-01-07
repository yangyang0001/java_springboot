package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_043;

import com.alibaba.fastjson.JSON;
import org.omg.CosNaming.NamingContextPackage.NotEmptyHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * n = 3 ["()()()","(()())","()(())","(())()","((()))"]
 * n = 4
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = generateParenthesis(5);
        System.out.println(JSON.toJSONString(list));

//        String source = "(()())";
//
//        String prefix = source.substring(1, source.length() / 2);
//        String suffix = source.substring(source.length() / 2, source.length() - 1);
//        System.out.println("substr = " + source);
//        System.out.println("prefix = " + prefix);
//        System.out.println("suffix = " + suffix);

    }

    public static List<String> generateParenthesis(int num) {

        List<String> result = new ArrayList<>();
        if (num == 1) {
            result.add("()");
            return result;
        } else {
            result.add("()");
        }

        for (int i = 1; i < num; i++) {
            result = execute(result);
        }

        return result;
    }

    public static List<String> execute(List<String> list) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String sub = list.get(i);
            String left = "()" + sub;
            String righ = sub + "()";
            String midd = "(" + sub + ")";

            if (!result.contains(left)) {
                result.add(left);
            }
            if (!result.contains(righ)) {
                result.add(righ);
            }
            if (!result.contains(midd)) {
                result.add(midd);
            }

            String prefix = sub.substring(1, sub.length() / 2);
            String suffix = sub.substring(sub.length() / 2, sub.length() - 1);

            if(prefix.equals(suffix)) {
                System.out.println("substr = " + sub);
                System.out.println("prefix = " + prefix);
                System.out.println("suffix = " + suffix);
                String othe = "(" + prefix + ")(" + suffix + ")";
                if (!result.contains(othe)) {
                    result.add(othe);
                }
            }
        }

        return result;
    }

}
