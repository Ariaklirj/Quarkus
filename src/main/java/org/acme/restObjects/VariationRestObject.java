package org.acme.restObjects;

import org.acme.DB.Products;
import org.acme.DB.Variations;
import org.acme.interfaces.RestObject;
import org.acme.pojos.CustomError;

import java.util.ArrayList;
import java.util.List;

public class VariationRestObject implements RestObject {

    public String name;
    public String brand;
    public String sku;
    public int stock;
    public Long id;
    public Products product;
    private List<String> errors= new ArrayList<String>();
    private List<String> field= new ArrayList<String>();

    public void createVariation(){
        Variations variations = new Variations();
        variations.name=name;
        variations.brand= brand;
        variations.sku=sku;
        variations.stock=stock;
        variations.product=this.product;
        variations.persist();
        this.id=variations.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public long getSkuCount(){
        long count = Variations.count("sku",sku);
        return count;
    }

    @Override
    public boolean validateData() {
        boolean is_succese= true;
        if (getSkuCount()>0){
            this.errors.add("El skuproporcionado ya esta asociado a otra variación");
            this.field.add("sku");
            is_succese=false;      }

        return is_succese;
    }

    @Override
    public List<CustomError> get_errors() {

        List <CustomError> customErrorList = new ArrayList<>();
        for (int i=0;i<this.errors.size();i++){
            CustomError customError = new CustomError();
            customError.setMessage(this.errors.get(i));
            customError.setFieldName(this.field.get(i));
            customErrorList.add(customError);
        }
        return customErrorList;
    }
}
