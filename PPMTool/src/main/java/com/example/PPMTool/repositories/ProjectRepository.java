package com.example.PPMTool.repositories;

import com.example.PPMTool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//We create the repository and pass it the object we want to do CRUD operation on along with the type of the id field.
//the CrudRepository interface gives us all the needed methods to do operations agains the DB such as save(), findAll() etc.
//Once we extend CrudRepository in our own interface we do not need to implement the methods other than simply override
//the contract because Spring will take care of the rest.Spring will implement the methods for us
// by creating a class wich implements the methods so that we can call them using and autowiring of the
// ProjectRepository in our ProjectService class.Spring does this at runtime.We can also create methods wich are similar
//to the ones in the CrudRepository interface.These methods are resolved by JPA automatically in 2 ways:Jpa either
//searches the name of the method in an XML file and retrieves the SQL that it must be performed OR JPA strips the method
// name of its prefix(ex the "find"..By) and parsed
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();


}
