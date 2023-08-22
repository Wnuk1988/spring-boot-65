package com.tms.domain.hibernate_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@Entity(name = "books")
@Data
@EqualsAndHashCode(exclude = {"pages","authors"})
@ToString(exclude = {"pages","authors"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
    @SequenceGenerator(name = "books_seq", sequenceName = "books_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Collection<Page> pages;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Collection<Author> authors;
}
