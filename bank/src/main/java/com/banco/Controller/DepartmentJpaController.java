package com.banco.Controller;

import com.banco.model.DepartmentJPA;
import com.banco.Service.DepartmentServiceJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments/jpa")
public class DepartmentJpaController {

    @Autowired
    private DepartmentServiceJpa departmentServiceJpa;

    @GetMapping
    public ResponseEntity<List<DepartmentJPA>> getAllDepartments() {
        List<DepartmentJPA> departments = departmentServiceJpa.findAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/test")
    public String test() {
        return "API funcionando!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentJPA> getDepartmentById(@PathVariable Long id) {
        return departmentServiceJpa.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DepartmentJPA> createDepartment(@Valid @RequestBody DepartmentJPA department) {
        DepartmentJPA savedDepartment = departmentServiceJpa.save(department);
        return ResponseEntity.status(201).body(savedDepartment); // Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentJPA> updateDepartment(@PathVariable Long id,
                                                          @Valid @RequestBody DepartmentJPA departmentDetails) {
        return departmentServiceJpa.findById(id)
                .map(department -> {
                    department.setTitle(departmentDetails.getTitle());
                    department.setDescription(departmentDetails.getDescription());
                    department.setActive(departmentDetails.isActive());
                    DepartmentJPA updated = departmentServiceJpa.save(department);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (departmentServiceJpa.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        departmentServiceJpa.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
