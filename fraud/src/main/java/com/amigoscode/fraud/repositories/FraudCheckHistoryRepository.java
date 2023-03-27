package com.amigoscode.fraud.repositories;

import com.amigoscode.fraud.models.FraudCheckHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistoryModel, Integer> {
}
