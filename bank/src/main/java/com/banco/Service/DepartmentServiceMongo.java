package com.banco.Service;

import com.banco.model.DepartmentMongo;
import com.banco.repository.DepartmentMongoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceMongo {

    @Autowired
    private DepartmentMongoRepository departmentMongoRepository;

    public List<DepartmentMongo> findAll() {
        return departmentMongoRepository.findAll();
    }

    public Optional<DepartmentMongo> findById(String id) {
        return departmentMongoRepository.findById(id);
    }

    public DepartmentMongo save(DepartmentMongo department) {
        return departmentMongoRepository.save(department);
    }

    public void deleteById(String id) {
        departmentMongoRepository.deleteById(id);
    }
}
