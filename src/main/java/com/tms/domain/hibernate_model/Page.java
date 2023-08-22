package com.tms.domain.hibernate_model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "pages")
@Data
@EqualsAndHashCode(exclude = "book")
@ToString(exclude = "book")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "page_seq")
    @SequenceGenerator(name = "page_seq", sequenceName = "pages_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "text")
    private String text;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
