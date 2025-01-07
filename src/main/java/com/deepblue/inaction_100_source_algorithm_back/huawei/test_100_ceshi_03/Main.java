package com.deepblue.inaction_100_source_algorithm_back.huawei.test_100_ceshi_03;

import java.util.*;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());

        List<String> steps = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String step = scanner.nextLine();
            if(step.isEmpty()) {
                break;
            }
            steps.add(step);
        }

        String result = execute(count, steps);
        System.out.println(result);

    }

    public static String execute(int count, List<String> steps) {

        // array[i] 记录能否到达第i个格子, 0:不能, 1:能
        int[] array = new int[count];

        // 存在依赖在不能开启
        if(!isLegal(steps)) {
            return "no";
        }

        // 设置自动开启的格子
        Set<String> indexSet = new HashSet<String>();
        for(String step : steps) {
            String[] split = step.split(" ");
            indexSet.add(split[0]);
            indexSet.add(split[1]);
        }
        for(int i = 0; i < count; i++) {
            if(!indexSet.contains(String.valueOf(i))) {
                array[i] = 1;
            }
        }

        // 设置触发开启的格子
        for(String index : indexSet) {
            array[Integer.parseInt(index)] = 1;
        }

        return Arrays.asList(array).contains(0) ? "no" : "yes";
    }

    /**
     * 相互依赖为不合法
     * @param steps
     * @return
     */
    public static boolean isLegal(List<String> steps) {

        for (String step : steps) {
            String reverse = new StringBuilder(step).reverse().toString();
            if(steps.contains(reverse)) {
                return false;
            }
        }

        return true;
    }
}
