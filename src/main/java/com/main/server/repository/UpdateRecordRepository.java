package com.main.server.repository;

import com.main.server.model.entities.ProductUpdateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRecordRepository extends JpaRepository<ProductUpdateRecord, Integer> {
}
