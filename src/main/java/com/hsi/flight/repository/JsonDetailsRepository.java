package com.hsi.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsi.flight.entity.TransactionJson;

@Repository
public interface JsonDetailsRepository extends JpaRepository<TransactionJson, String> {

}
