package com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.model.DepartmentJPA;

public interface DepartmentJpaRepository extends JpaRepository<DepartmentJPA, Long> {  
}
