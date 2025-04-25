package com.banco.Controller;

import com.banco.Service.DepartmentServiceMongo;
import com.banco.model.DepartmentMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments/mongo")
public class DepartmentMongoController {

    @Autowired
    private DepartmentServiceMongo departmentServiceMongo;

    @GetMapping
    public ResponseEntity<List<DepartmentMongo>> getAllDepartments() {
        return ResponseEntity.ok(departmentServiceMongo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentMongo> getDepartmentById(@PathVariable String id) {
        return departmentServiceMongo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DepartmentMongo> createDepartment(@RequestBody DepartmentMongo department) {
        return ResponseEntity.ok(departmentServiceMongo.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentMongo> updateDepartment(@PathVariable String id, @RequestBody DepartmentMongo departmentDetails) {
        return departmentServiceMongo.findById(id)
                .map(department -> {
                    department.setTitle(departmentDetails.getTitle());
                    department.setDescription(departmentDetails.getDescription());
                    department.setActive(departmentDetails.isActive());
                    return ResponseEntity.ok(departmentServiceMongo.save(department));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        if (!departmentServiceMongo.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        departmentServiceMongo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
