package com.example.PPMTool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//now we actually create the route to be able to post a new project to the DB.because of this we have to do
//a post request.ResponseEntity is a type that allows us to actually have more control on our JSON response
//that we will set up with REACT.This allows us to control the response statuses and also the JSON object that
//we are gonna pass to the client.The @RequestBOdy is used for the happy path.Next the method will return the new
//Project and create a HttpsStatus that the project was created.Once this is done we can already test Post request
//with Postman.In postman we have to Create a new Collection of request with name PPMTool and send a Post request
//to the /api/project mapping.THe request body has to contain data in JSON format.So we have to add that data
//manually respecting that structure.@Valid annotation allows us to pass only valid data wich passed the constraints
//we set int the entity class.As such we avoid a text of errors in the console and get a 400 bad request instead.
//BindingResult analizes the object and determines wheter there are errors.It is a validator.BindingResult
//holds the result of a validation and binding and contains errors that may have occured.BindingResult must come
//right after the model that is validated or else it will not work.ResponseEntity represents the whole HTTP response
//including status code,headers and body.
@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValdationService(BindingResult result){
        if (result.hasErrors()) {
            //The purpose of this map is to extract the field of the project object and the corresponding error.
            //They are both added as strings to the map.The field is the key while the error is the value.
            //We can do this by using FieldError objects getters and setters.As such we extract from the fieldError list
            //what we need.
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            //if the project has errors the responseentity handels it.The result contains all field errors that where made
            // when commiting the Project object.They are added to a map.
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }


    return null;
    }

}
