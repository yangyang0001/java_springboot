package com.deepblue.inaction_00_spring.chapter_07.example_008_aspectj_joinpoint_info;

/**
 *
 */
public class MineSeller implements Seller{

    @Override
    public Book getBook(long bookId) {

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookName("hotspot in action");
        book.setBookPrice(10000L);

        return book;
    }

    @Mine(value = "mine_value")
    @Override
    public String getBookName(long bookId) {
        if(bookId == 10000L) {
            return "hotspot in action";
        } else {
            return "spring boot";
        }

    }
}
