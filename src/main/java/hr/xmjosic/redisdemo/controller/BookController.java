package hr.xmjosic.redisdemo.controller;

import hr.xmjosic.redisdemo.model.Book;
import hr.xmjosic.redisdemo.service.BookServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@AllArgsConstructor
public class BookController {

    private final BookServiceInterface bookServiceInterface;

    @GetMapping("/books")
    public ResponseEntity<Object> getAllBooks() {
        List<Book> books = this.bookServiceInterface.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") String id) {
        Long _id = Long.valueOf(id);
        Book book = this.bookServiceInterface.getBookById(_id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        Book newBook = this.bookServiceInterface.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Object> updateBook(@RequestBody Book book) {
        Book updatedBook = this.bookServiceInterface.update(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") String id) {
        Long _id = Long.valueOf(id);
        this.bookServiceInterface.delete(_id);
        return ResponseEntity.ok().build();
    }

}
