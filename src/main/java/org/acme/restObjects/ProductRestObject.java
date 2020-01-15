package org.acme.restObjects;

import org.acme.DB.Companies;
import org.acme.DB.Products;

import org.acme.interfaces.RestObject;
import org.acme.pojos.CustomError;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;

public class ProductRestObject implements RestObject {

    public String name = "";
    public int stock = -1;
    public float cost = -1;
    public float price = -1;
    public boolean variationNotValid=false;
    public boolean has_iva = false;
    public long company_id = -1;
    public Long id;
    public List <VariationRestObject>  variations;
    private List<String> errors= new ArrayList<String>();
    private List<String> field= new ArrayList<String>();

    @Override
    public boolean validateData() {

        boolean is_succese = true;

        if (this.name.equals("")){

            this.errors.add("Este campo no puede ir vacio");
            this.field.add("name");
            is_succese=false;
        }

        if (this.stock < 0){

            this.errors.add("Este campo no puede ir vacio ni ser menor a 0");
            this.field.add("name");
            is_succese=false;
        }

        if (this.cost < 0){

            this.errors.add("Este campo no puede ir vacio ni ser menor a 0");
            this.field.add("name");
            is_succese=false;
        }

        if (this.price < 0 ){

            this.errors.add("Este campo no puede ir vacio ni ser menor a 0");
            this.field.add("name");
            is_succese=false;
        }

        if (this.company_id < 0){

            this.errors.add("Este campo no puede ir vacio ni ser menor a 0");
            this.field.add("name");
            is_succese=false;
        }

        if (this.cost>this.price){
            this.errors.add("El costo no puede ser menor al precio del producto");
            this.field.add("price|cost");
            is_succese=false;
        }

        if (companyCount()<0){
            this.errors.add("El id de compañia proporsionado no existe");
            this.field.add("company_id");
            is_succese=false;
        }

        if (!isAllVariationsValid()){
            is_succese=false;
            variationNotValid=true;
        }

        return  is_succese;
    }



    public Products createProduct(){
        Products product = new Products();
        product.name=name;
        product.cost=cost;
        product.price=price;
        product.stock=stock;
        product.has_iva=has_iva;
        product.companies=getCompaniesObject();
        product.persist();
        this.id=product.id;
        createVariations(product);
        return product;
    }

    public void createVariations(Products product){
        System.out.println(variations.size());
        for(int i = 0;i<variations.size();i++){
            variations.get(i).setProduct(product);
            variations.get(i).createVariation();
        }
    }

    public boolean isAllVariationsValid(){
        List<String>variationsSku = new ArrayList<>();
        boolean is_valid = true;
        for(int i = 0;i<variations.size();i++){
            if (!variations.get(i).validateData()){
                is_valid= false;
            }
            if (!variationsSku.contains(variations.get(i).sku)){
                variationsSku.add(variations.get(i).sku);
            }
            else {
                is_valid=false;
            }
        }
        return is_valid;
    }

    public Companies getCompaniesObject(){
        Companies company= Companies.findById(company_id);
        return  company;
    }

    public  long companyCount(){
        long company_count = Companies.count("id",company_id);
        return  company_count;
    }

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

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public List<VariationRestObject> getVariations() {
        return variations;
    }

    public void setVariations(List<VariationRestObject> variations) {
        this.variations = variations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (variationNotValid) {
            for(int i = 0;i<variations.size();i++){
                List <CustomError> customErrorListVariations = variations.get(i).get_errors();
                if (customErrorListVariations.size()>0) customErrorList.addAll(customErrorListVariations);

            }
        }
        return customErrorList;
    }
}
