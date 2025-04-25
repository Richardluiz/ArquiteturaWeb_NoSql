package com.banco.Controller;

import com.banco.Service.DepartmentServiceJpa;
import com.banco.model.DepartmentJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments/jpa")
public class DepartmentJpaController {

    @Autowired
    private DepartmentServiceJpa departmentServiceJpa;

    @GetMapping
    public ResponseEntity<List<DepartmentJPA>> getAllDepartments() {
        return ResponseEntity.ok(departmentServiceJpa.findAll());
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
    public ResponseEntity<DepartmentJPA> createDepartment(@RequestBody DepartmentJPA department) {
        return ResponseEntity.ok(departmentServiceJpa.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentJPA> updateDepartment(@PathVariable Long id, @RequestBody DepartmentJPA departmentDetails) {
        return departmentServiceJpa.findById(id)
                .map(department -> {
                    department.setTitle(departmentDetails.getTitle());
                    department.setDescription(departmentDetails.getDescription());
                    department.setActive(departmentDetails.isActive());
                    return ResponseEntity.ok(departmentServiceJpa.save(department));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (!departmentServiceJpa.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        departmentServiceJpa.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
