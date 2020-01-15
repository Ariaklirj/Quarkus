package org.acme.views;

import org.acme.DB.Companies;
import org.acme.DB.Products;
import org.acme.pojos.CustomError;
import org.acme.pojos.CustomErrorResponse;
import org.acme.restObjects.CompanyRestObject;
import org.acme.restObjects.ProductRestObject;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class ProductView {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create_company(ProductRestObject product) {

        if (!product.validateData()){

            List<CustomError> errors = product.get_errors();
            CustomErrorResponse errorResponse = new CustomErrorResponse();
            errorResponse.setErrorList(errors);
            errorResponse.setMessage("Los datos ingresados son incorrectos");
            return Response.status(404).entity(errorResponse).build();
        }

        Products respose =product.createProduct();

        return  Response.status(201).entity(respose).build();

    }
}
