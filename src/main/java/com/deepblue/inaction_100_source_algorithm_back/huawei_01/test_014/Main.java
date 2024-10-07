    package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_014;

    import java.util.LinkedHashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Scanner;
    import java.util.stream.Collectors;
    import java.util.stream.Stream;

    /**
     *
     */
    public class Main {

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int count = Integer.valueOf(scanner.nextLine());

            Map<Integer, Integer> map = new LinkedHashMap<>();

            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");

                if(map.get(Integer.valueOf(split[0].trim())) == null) {
                    map.put(Integer.valueOf(split[0].trim()), Integer.valueOf(split[1]));
                } else {
                    map.put(Integer.valueOf(split[0].trim()), map.get(Integer.valueOf(Integer.valueOf(split[0].trim()))) + Integer.valueOf(split[1]));
                }
            }

            List<Integer> collect = map.keySet().stream().sorted().collect(Collectors.toList());
            for(Integer key : collect) {
                System.out.println(key + " " + map.get(key));
            }
        }

    }
