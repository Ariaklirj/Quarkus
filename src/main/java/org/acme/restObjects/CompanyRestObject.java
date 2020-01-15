package org.acme.restObjects;

import org.acme.DB.Companies;
import org.acme.interfaces.RestObject;
import org.acme.pojos.CustomError;

import java.util.ArrayList;
import java.util.List;

public class CompanyRestObject implements RestObject {
    private Companies company;
    private List<String> errors= new ArrayList<String>();
    private List<String> field= new ArrayList<String>();

    public CompanyRestObject(Companies company) {
        this.company=company;
    }

    @Override
    public boolean validateData() {
        boolean is_succese = true;

        if (this.company.name.equals("")){

            this.errors.add("Este campo no puede ir vacio");
            this.field.add("name");
            is_succese=false;
        }
        return  is_succese;
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
