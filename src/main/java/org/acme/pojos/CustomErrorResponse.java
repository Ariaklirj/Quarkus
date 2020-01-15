package org.acme.pojos;

import java.util.List;

public class CustomErrorResponse {
    public List<CustomError> errorList;
    public String message;



    public List<CustomError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<CustomError> errorList) {
        this.errorList = errorList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
