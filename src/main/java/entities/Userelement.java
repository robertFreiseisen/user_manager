package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Userelement {
    private Integer id;
    private String name;
    private String hashedpassword;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "hashedpassword")
    public String getHashedpassword() {
        return hashedpassword;
    }

    public void setHashedpassword(String hashedpassword) {
        this.hashedpassword = hashedpassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userelement that = (Userelement) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(hashedpassword, that.hashedpassword))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hashedpassword != null ? hashedpassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Userelement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hashedpassword='" + hashedpassword + '\'' +
                '}';
    }
}
