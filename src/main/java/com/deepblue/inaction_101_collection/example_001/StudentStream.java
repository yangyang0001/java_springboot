package com.deepblue.inaction_101_collection.example_001;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class StudentStream {

    public static void main(String[] args) {

        Student student1 = new Student("zhangsan", 70);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 100);
        Student student4 = new Student("zhaoliu", 80);
        Student student5 = new Student("zhaoliu", 70);

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);

        //1.将集合转换成流,然后再转换成集合
        List<Student> studentList = students.stream().collect(Collectors.toList());

        //2.通过流的方式拿到集合的个数
        long count = studentList.stream().count();
        System.out.println("count is :" + count);

        //3.获取到集合中学生成绩最低的学生
        Student minStudent = studentList.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getScore))).get();
        System.out.println("minStudent is :" + JSON.toJSONString(minStudent));

        //4.获取到集合中学生成绩最大的学生
        Student maxStudent = studentList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore))).get();
        System.out.println("maxStudent is :" + JSON.toJSONString(maxStudent));

        //5.获取到学生的平均成绩
        Double avgScore = studentList.stream().collect(Collectors.averagingDouble(Student::getScore));
        System.out.println("avgScore is :" + avgScore);

        //6.求出所有学生的成绩总和
        Long sumScore = studentList.stream().collect(Collectors.summingLong(Student::getScore));
        System.out.println("sumScore is :" + sumScore);

        //7.将所有的student的姓名进行拼接
        String names = studentList.stream().map(item -> item.getName()).collect(Collectors.joining("-"));
        System.out.println("names is :" + names);

        //8.首先根据成绩分组,再根据名字分组
        Map<Integer, Map<String, List<Student>>> groupMap = studentList.stream().collect(Collectors.groupingBy(Student::getScore, Collectors.groupingBy(Student::getName)));
        System.out.println("groupMap is :" + JSON.toJSONString(groupMap));

        //9.分区
        Map<Boolean, List<Student>> partitionMap = studentList.stream().collect(Collectors.partitioningBy(item -> item.getScore() > 80));
        System.out.println("partitionMap is :" + JSON.toJSONString(partitionMap));

        //10.分区后进行结果求和
        Map<Boolean, Long> collectMap = studentList.stream().collect(Collectors.partitioningBy(item -> item.getScore() > 80, Collectors.summingLong(Student::getScore)));
        System.out.println("collectMap is :" + JSON.toJSONString(collectMap));

        //12.先根据studentName进行分组,然后在根据每个组中的学生成绩比较,取到成绩最低的学生
        Map<String, Optional<Student>> optionalMap = studentList.stream().collect(Collectors.groupingBy(Student::getName, Collectors.minBy(Comparator.comparingInt(Student::getScore))));
        System.out.println("optionalMap is :" + JSON.toJSONString(optionalMap));

    }
}
