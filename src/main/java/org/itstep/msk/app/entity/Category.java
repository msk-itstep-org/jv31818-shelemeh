package org.itstep.msk.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@NamedEntityGraph(name = "category-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("name")
        }
)
public class Category implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!id.equals(category.id)) return false;
        return name.equals(category.name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(name = "category_name", unique = true, nullable = false)
    private String name;

    @Column(name = "image")
    private transient String image;

    @OneToMany(mappedBy = "category",fetch =FetchType.LAZY,
            cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
   // @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Set<Product> prodCategories = new HashSet<>();

}
