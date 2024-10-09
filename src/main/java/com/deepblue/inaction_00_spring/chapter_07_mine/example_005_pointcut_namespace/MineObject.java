package com.deepblue.inaction_00_spring.chapter_07_mine.example_005_pointcut_namespace;

import javax.validation.constraints.Min;

/**
 *
 */
public class MineObject implements MineInterface {

    @Override
    @Mine
    public Book selectId(long id) {
        System.out.println("selectId method invoke, id = " + id);

        Book book = new Book();
        book.setId(id);
        book.setName("spring aop");

        return book;
    }

    @Override
    public int deleteId(long id) {
        System.out.println("deleteId method invoke, id = " + id);
        return 1;
    }

}
