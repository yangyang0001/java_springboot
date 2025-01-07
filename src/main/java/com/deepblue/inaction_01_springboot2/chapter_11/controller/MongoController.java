package com.deepblue.inaction_01_springboot2.chapter_11.controller;

import com.deepblue.inaction_01_springboot2.chapter_11.entity.Book;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/insert_book")
    @ResponseBody
    public String insert() {

        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Hotspot实战");
        book1.setAuthor("陈涛");
        mongoTemplate.insert(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("深入理解JVM");
        book2.setAuthor("周志明");
        mongoTemplate.insert(book2);

        List<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);

        try {
            mongoTemplate.insertAll(books);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Book findById(Long id) {
        Book book = mongoTemplate.findById(id, Book.class);
        return book;
    }

    @RequestMapping("/queryBooks")
    @ResponseBody
    public List<Book> queryBooks() {

        Criteria criteria = Criteria.where("name").is("Hotspot实战");
        Query query = Query.query(criteria);
        List<Book> books = mongoTemplate.find(query, Book.class);

        return books;
    }

    @RequestMapping("/pagelist")
    @ResponseBody
    public List<Book> pagelist(Long pageNo, Integer limit) {

        Criteria criteria0 = Criteria.where("id").gte(2);
        Criteria criteria1 = Criteria.where("id").lte(6);
        Query query = Query.query(criteria0.andOperator(criteria1));

        // 查询总数
        long totalCount = mongoTemplate.count(query, Book.class) ;
        // 计算总数
        long totalPage = totalCount % limit == 0 ? (totalCount / limit) : (totalCount / limit + 1);

        System.out.println("totalCount is : " + totalCount + ", totalPage is : " + totalPage);

        Long skip = (pageNo - 1) * limit;
        query = query.skip(skip).limit(limit);
        List<Book> pagelist = mongoTemplate.find(query, Book.class);

        return pagelist;
    }

    @RequestMapping("/updatebook")
    @ResponseBody
    public UpdateResult updateBook() {
        Criteria criteria = Criteria.where("id").is(2);
        Query query = Query.query(criteria);

        Update update = new Update();
        update.set("author", "YangYang");
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Book.class);

        return updateResult;
    }

    @RequestMapping("/deletebook")
    @ResponseBody
    public DeleteResult deleteBook() {
        Book book = new Book();
        book.setId(6L);
        DeleteResult deleteResult = mongoTemplate.remove(book);

        return deleteResult;
    }
}
