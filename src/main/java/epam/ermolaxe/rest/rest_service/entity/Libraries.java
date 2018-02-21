package epam.ermolaxe.rest.rest_service.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "libraries")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Libraries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "library_title")
    private String title;

    @Column(name = "library_address")
    private String address;

    @ManyToMany
    @JoinTable(name = "library_to_authors",
            joinColumns = {@JoinColumn(name = "library_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private Set<Author> authors;

    public Libraries() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libraries libraries = (Libraries) o;
        return Objects.equals(id, libraries.id) &&
                Objects.equals(title, libraries.title) &&
                Objects.equals(address, libraries.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, address);
    }

    @Override
    public String toString() {
        return "Libraries{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", authors=" + authors +
                '}';
    }
}
