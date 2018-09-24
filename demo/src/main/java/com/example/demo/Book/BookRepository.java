package com.example.demo.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByNameLike (String value);
    //Book findAllByPriceOrderByPriceAsc();
    List<Book> findAllByPriceLessThanOrderByPriceAsc(int price);

}
