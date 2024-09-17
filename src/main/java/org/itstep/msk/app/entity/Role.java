package org.itstep.msk.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
@Data
@Entity
@Table( name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;


   @ManyToMany(mappedBy = "roles" ,cascade =  CascadeType.ALL , fetch =  FetchType.LAZY)
   @JsonIgnore
    private Set<Customer> custom ;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Role))
            return false;
        final Role role = (Role) o;
        return Objects.equals(this.id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    public Role() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Customer> getCustom() {
        return this.custom;
    }

    public void setCustom(final Set<Customer> custom) {
        this.custom = custom;
    }
}
