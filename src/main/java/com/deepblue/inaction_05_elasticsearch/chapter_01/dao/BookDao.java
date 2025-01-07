package com.deepblue.inaction_05_elasticsearch.chapter_01.dao;

import com.deepblue.inaction_05_elasticsearch.chapter_01.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends ElasticsearchRepository<BookDTO, Long> {

    public List<BookDTO> getById(Long Id);

}
