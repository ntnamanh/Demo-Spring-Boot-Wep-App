package com.example.demo.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findByName(String name);
    Book findDistinctByPrice(int price);
    Book findByNameStartsWith(String word);
    List<Book> findAllByNameLike (String value);
    List<Book> findAllByCategoryEquals(String price);
    List<Book> findAllByOrderByNameAsc();
    List<Book> findAllByBookStatusEquals(String bookStatus);
    List<Book> findAllByPriceLessThan(int price);
    List<Book> findAllByPriceGreaterThan(int price);

}
