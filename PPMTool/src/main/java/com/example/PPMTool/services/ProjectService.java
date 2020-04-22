package com.example.PPMTool.services;

import com.example.PPMTool.domain.Project;
import com.example.PPMTool.exceptions.ProjectIdException;
import com.example.PPMTool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//We should always autowire(inject) the repository into the according service class.We do this in order to
//have access to all the functionalities of the ProjectRepository object and wich allow us to interface with the
//backend.We can call every method from the ProjectRepository interface and the ones from the CrudRepository(save,delete etc)
// in the ProjectService Class.They are already implemented at runtime by spring automatically.Next, all these methods
// wich we create here in this Service Class contain methods implemented by Spring automatically, so we make the
// loose coupling and can use them in a Controller.
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "'already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {

        //this allows for a project to be found not mather if the id is lowercase of uppercase
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "'does not exist");
        }
        return project;
    }

    //Whenever we have something that returns a list, the best way to extract that list is to traverse through the list
    //This returns a JSON Iterable object.
    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    //deleting a project
    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Cannot delete Project with ID '" + projectId + "' . This project does not exist");
        }

        projectRepository.delete(project);
    }
}
