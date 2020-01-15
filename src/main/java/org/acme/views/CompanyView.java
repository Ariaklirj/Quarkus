package org.acme.views;

import org.acme.DB.Companies;
import org.acme.pojos.CustomError;
import org.acme.pojos.CustomErrorResponse;
import org.acme.restObjects.CompanyRestObject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;


@Path("/company")
@Transactional
@Produces(MediaType.APPLICATION_JSON)

public class CompanyView {

    @GET
    public List<Companies> get_all_campanies() {
         List<Companies> companies = Companies.listAll();
         return  companies;
    }

    @GET
    @Path("{id}")
    public Companies get_company_by_id(@PathParam("id") Long id) {
        Companies companies = Companies.findById(id);
        return  companies;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create_company(Companies companies) {
        CompanyRestObject companyRestObject= new CompanyRestObject(companies);

        if (!companyRestObject.validateData()){

            List<CustomError> errors = companyRestObject.get_errors();
            CustomErrorResponse errorResponse = new CustomErrorResponse();
            errorResponse.setErrorList(errors);
            errorResponse.setMessage("Los datos ingresados son incorrectos");
            return Response.status(404).entity(errorResponse).build();
        }

        companies.persist();
        return  Response.status(201).entity("ok").build();
    }
}