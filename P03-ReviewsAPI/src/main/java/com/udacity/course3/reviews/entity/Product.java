package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodid")
    private Integer prodid;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prodid", referencedColumnName = "prodid")
    private List<Reviews> reviews;

    public List<Reviews> getReviews() { return reviews; }

    public void setReviews(List<Reviews> reviews) { this.reviews = reviews; }

    public Product () {
    }

    public Integer getProdid() { return prodid; }

    public void setProdid(Integer prodid) { this.prodid = prodid; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Product{" +
                "prodid=" + prodid +
                ", name='" + name + '\'' +
                '}';
    }
}
