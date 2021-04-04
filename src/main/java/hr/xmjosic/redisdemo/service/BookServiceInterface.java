package hr.xmjosic.redisdemo.service;

import hr.xmjosic.redisdemo.model.Book;

import java.util.List;

public interface BookServiceInterface {

    public List<Book> getAll();

    public Book add(Book book);

    public Book update(Book book);

    public void delete(Long id);

    public Book getBookById(Long id);

}
