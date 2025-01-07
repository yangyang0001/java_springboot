package com.deepblue.inaction_100_source_algorithm_back.huawei.test_011;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner  = new Scanner(System.in);

        List<String> list = Lists.newArrayList();

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            if(StringUtils.isEmpty(line)) {
                break;
            } else {
                list.add(line);
            }

        }

        System.out.println(JSON.toJSONString(list));
    }

}
