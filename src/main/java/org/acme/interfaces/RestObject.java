package org.acme.interfaces;

import org.acme.pojos.CustomError;

import java.util.List;

public interface RestObject {
    public boolean validateData();
    public List<CustomError> get_errors();

}
