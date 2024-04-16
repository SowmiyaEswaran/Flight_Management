package com.hsi.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsi.flight.entity.TransactionDetails;

public interface FlightRepository extends JpaRepository<TransactionDetails, String> {

}
