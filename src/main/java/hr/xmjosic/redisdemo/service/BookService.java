package hr.xmjosic.redisdemo.service;

import hr.xmjosic.redisdemo.model.Book;
import hr.xmjosic.redisdemo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "bookCache")
@AllArgsConstructor
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;

    @Cacheable(cacheNames = "books")
    @Override
    public List<Book> getAll() {
        waitSomeTime();
        return this.bookRepository.findAll();
    }

    private void waitSomeTime() {
        System.out.println("Long wait begin...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("Long wait end!");
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    public Book add(Book book) {
        return this.bookRepository.save(book);
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    public Book update(Book book) {
        Optional<Book> optionalBook = this.bookRepository.findById(book.getId());
        if (!optionalBook.isPresent())
            return null;
        Book existBook = optionalBook.get();
        existBook.setBookName(book.getBookName());
        existBook.setAuthor(book.getAuthor());
        return this.bookRepository.save(existBook);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "book", key = "#id"),
            @CacheEvict(cacheNames = "books", allEntries = true)})
    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "book", key = "#id", unless = "#result == null")
    @Override
    public Book getBookById(Long id) {
        waitSomeTime();
        return this.bookRepository.findById(id).orElse(null);
    }
}
