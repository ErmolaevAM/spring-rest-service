package epam.ermolaxe.rest.rest_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Author.class)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<Libraries> libraries;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private Set<Book> books;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Libraries> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Libraries> libraries) {
        this.libraries = libraries;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
