package com.deepblue.inaction_05_elasticsearch.chapter_01.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(indexName = "books", type = "book", shards = 3, replicas = 2)
public class BookDTO {

    @Id
    private Long id;

    private String name;

    private String message;

    private Date postDate;

    private String type;
}
