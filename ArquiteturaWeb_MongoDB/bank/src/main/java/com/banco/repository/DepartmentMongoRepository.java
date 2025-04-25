package com.banco.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banco.model.DepartmentMongo;

@Repository
public interface DepartmentMongoRepository extends MongoRepository<DepartmentMongo, String> { 

}