package org.acme.DB;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.acme.interfaces.RestObject;
import org.acme.pojos.CustomError;

import javax.persistence.*;
import java.util.List;


@Entity
public class Companies extends PanacheEntity  {
    public String name = "";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "company_generator")
    @SequenceGenerator(name="company_generator", sequenceName = "comp_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @OneToMany(mappedBy = "companies")
    private List<Products> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name){
        this.name = name.toLowerCase();
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
