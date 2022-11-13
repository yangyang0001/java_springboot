package com.deepblue.inaction_100_source_algorithm_back.huawei.test_004.maxdepth;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int depth = maxDepth(line);

            System.out.println(depth);
        }

    }

    /**
     * (1+(20*3)+((89)/42))+100
     * @param line
     * @return
     */
    public static int maxDepth0(String line) {

        List<String> stringList = new ArrayList<>();
        List<String> numberList = new ArrayList<>();

        String number = "";
        for(int i = 0; i < line.length(); i++) {
            String substring = line.substring(i, i+1);
            if(substring.compareTo("0") >=0 && substring.compareTo("9") <= 0) {
                number += substring;
            } else {
//                if(!"".equals(number)) {
//                    numberList.add(number);
//                    number = "";
//                }
                stringList.add(substring);
            }
        }

//        if(!"".equals(number)) {
//            numberList.add(number);
//        }

        int maxDepth = 0;

        for(int i = 0; i < stringList.size(); i++) {
            if("(".equals(stringList.get(i))) {
                maxDepth += 1;
            }
        }


        return 0;
    }

    public static int maxDepth(String line) {

        String[] split = line.split("");

        //定义嵌套深度和最大嵌套深度
        int depth = 0, maxDepth = 0;

        //遍历
        for(String string : split){
            //左括号，嵌套深度加一并和最大值比较，大于最大值则替换最大值
            if ("(".equals(string)) {
                depth++;
                if(depth > maxDepth){
                    maxDepth = depth;
                }
            }

            //右括号，嵌套深度减一
            if(")".equals(string)){
                depth--;
            }
        }

        return maxDepth;
    }

}
