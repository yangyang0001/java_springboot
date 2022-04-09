package com.deepblue.inaction_05_elasticsearch.chapter_01.controller;

import com.alibaba.fastjson.JSON;
import com.deepblue.inaction_05_elasticsearch.chapter_01.dao.BookDao;
import com.deepblue.inaction_05_elasticsearch.chapter_01.dto.BookDTO;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 *
 */
@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping("/saveBook")
    public String saveBook(Long id) {
        String name = "elasticsearch_" + new Random().nextInt(1000);

        BookDTO entity = new BookDTO();
        entity.setId(id);
        entity.setName(name);
        entity.setPostDate(new Date());
        entity.setMessage("this is " + name);
        entity.setType("book");

        BookDTO save = bookDao.save(entity);

        return "success";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(Long id) {
        bookDao.deleteById(id);

        return "success";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Long id) {
        String name = "update_" + new Random().nextInt(1000);

        BookDTO entity = new BookDTO();
        entity.setId(id);
        entity.setName(name);
        entity.setPostDate(new Date());
        entity.setMessage("this is " + name);
        entity.setType("book");

        BookDTO update = bookDao.save(entity);

        return "success";
    }


    @RequestMapping("/getBookById")
    public String getBookById(Long id) {
        BookDTO book = bookDao.findById(id).get();
        return JSON.toJSONString(book);
    }

    @RequestMapping("/queryPageList")
    public String queryPageList(Integer pageNo, Integer pageSize) {
        PageRequest request = PageRequest.of(pageNo - 1, pageSize, Sort.by("id").ascending());
        QueryBuilder builder = QueryBuilders.wildcardQuery("name", "*elastic*");
        Page<BookDTO> page = bookDao.search(builder, request);

        long total = page.getTotalElements();
        long pages = page.getTotalPages();

        System.out.println("total is :" + total + " , pages :" + pages);

        List<BookDTO> resultList = page.getContent();
        return JSON.toJSONString(resultList);
    }

    @RequestMapping("/queryAllList")
    public String queryAllList() {
        Iterator<BookDTO> iterator = bookDao.findAll(Sort.by("id").ascending()).iterator();
        List<BookDTO> resultList = Lists.newArrayList();
        while(iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        return JSON.toJSONString(resultList);
    }
}
