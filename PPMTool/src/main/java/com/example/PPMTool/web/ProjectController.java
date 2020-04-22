package com.example.PPMTool.web;


import com.example.PPMTool.domain.Project;
import com.example.PPMTool.services.MapValidationErrorService;
import com.example.PPMTool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//We can create a new project object by autowiring the ProjectService object with all its Crud methods inside this class.
//We are using the ProjectService because it has implemented methods.
@RestController
// This path supports both GET and POST methods since we gave it a "" argument
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

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
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValdationService(result);
        //If errormap contains errors we return it.
        if (errorMap != null) return errorMap;

        // This save the project to the database
        Project project1 = projectService.saveOrUpdateProject(project);
        //this returns an HttpStatus
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    // The projectId from the mapping needs to match with the projectId from the parameter.Basically the method
    //will take the variable from the GetMapping and fill it as an argument for itself.It is done by Spring automatically.
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

        Project project = projectService.findProjectByIdentifier(projectId);
        //This also returns a project and also returns the Http 200 OK status.
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }


    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted", HttpStatus.OK);
    }

}