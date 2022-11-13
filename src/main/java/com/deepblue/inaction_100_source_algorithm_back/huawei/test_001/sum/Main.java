package com.deepblue.inaction_100_source_algorithm_back.huawei.test_001.sum;

import com.sun.deploy.security.SandboxSecurity;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a + b);
        }

    }
}
