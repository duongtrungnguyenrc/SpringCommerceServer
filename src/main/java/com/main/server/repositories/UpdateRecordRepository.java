package com.main.server.repositories;

import com.main.server.models.entities.ProductUpdateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRecordRepository extends JpaRepository<ProductUpdateRecord, Integer> {
}
