package hr.xmjosic.redisdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @SequenceGenerator(name = "books_id_seq", sequenceName = "books_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_seq")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

}
