package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_038;

import java.util.*;

/**
 * 7
 * IN 1 1
 * IN 2 3
 * IN 2 1
 * IN 1 3
 * OUT 1
 * OUT 1
 * OUT 1
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {

            int count = Integer.valueOf(scanner.nextLine());
            List<String> events = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine();
                events.add(line);
            }

            execute(events);

        }

    }

    public static void execute(List<String> events) {

        Map<String, List<Integer>> input = new HashMap<String, List<Integer>>();
        List<String> output = new ArrayList<>();
        List<String> files = new ArrayList<>();

        int count = 1;
        for (int i = 0; i < events.size(); i++) {
            String event = events.get(i);
            String[] split = event.split(" ");
            if (split.length == 2) {
                // 输出
                String key = split[1];
                output.add(key);
            } else {
                // 输入
                String key = split[1];
                String val = split[2];

                if(input.get(key) == null || input.get(key).isEmpty()) {
                    List<Integer> vals = new ArrayList<>();
                    vals.add(Integer.valueOf(val));
                    input.put(key, vals);
                } else {
                    List<Integer> vals = input.get(key);
                    vals.add(Integer.valueOf(val));
                    Collections.sort(vals);
                    input.put(key, vals);
                }

                files.add(key + "-" + val + "-" + count);
                count++;
            }
        }

//        System.out.println("files = " + JSON.toJSONString(files));

        output.stream().forEach(item -> {
//            System.out.println("key = " + item + ", vals = " + JSON.toJSONString(input.get(item)));
            List<Integer> vals = input.get(item);
            if(vals != null && !vals.isEmpty()) {
                int val = vals.get(vals.size() - 1);
                for (int i = 0; i < files.size(); i++) {
                    String[] file = files.get(i).split("-");
                    String soruce = file[0] + "-" + file[1];
                    String target = item + "-" + val;
                    String result = file[2];
                    if(soruce.equals(target)){
//                        System.out.println("source = " + soruce);
//                        System.out.println("target = " + target);
//                        System.out.println("result = " + result);
                        System.out.println(result);
                        System.out.println("before file size = " + files.size());
                        files.remove(i);
                        System.out.println("after  file size = " + files.size());
                        break;
                    }
                }
                vals.remove(vals.size() - 1);
                input.put(item, vals);
            } else {
                System.out.println("NULL");
            }
        });


    }
}
