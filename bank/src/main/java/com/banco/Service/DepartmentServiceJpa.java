package com.banco.Service;

import com.banco.model.DepartmentJPA;
import com.banco.repository.DepartmentJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceJpa {

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;

    public List<DepartmentJPA> findAll() {
        return departmentJpaRepository.findAll();
    }

    public Optional<DepartmentJPA> findById(Long id) {
        return departmentJpaRepository.findById(id);
    }

    public DepartmentJPA save(DepartmentJPA department) {
        return departmentJpaRepository.save(department);
    }

    public void deleteById(Long id) {
        departmentJpaRepository.deleteById(id);
    }
}
