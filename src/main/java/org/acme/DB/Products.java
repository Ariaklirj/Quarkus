package org.acme.DB;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Products extends PanacheEntity {
    public String name;
    public int stock;
    public float cost;
    public float price;
    public boolean has_iva;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    public Companies companies;

    @OneToMany(mappedBy = "product")
    private List<Variations> variarions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isHas_iva() {
        return has_iva;
    }

    public void setHas_iva(boolean has_iva) {
        this.has_iva = has_iva;
    }

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

    public List<Variations> getVariarions() {
        return variarions;
    }

    public void setVariarions(List<Variations> variarions) {
        this.variarions = variarions;
    }
}
