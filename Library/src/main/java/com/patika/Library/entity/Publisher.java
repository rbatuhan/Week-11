package com.patika.Library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "establishment_year", nullable = false)
    private int establishmentYear;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> bookList;

}
