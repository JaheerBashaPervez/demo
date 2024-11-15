package com.example.demo.repos;

import com.example.demo.beans.ProcessRequest;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<ProcessRequest,Integer> {

    @Procedure(procedureName = "GetRequestStatus")
    String getRequestStatus(int id);
}
