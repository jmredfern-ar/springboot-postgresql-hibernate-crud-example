package com.redfern.springbootpostgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redfern.springbootpostgresql.model.AddressTest;

@Repository
public interface AddressTestRepository extends JpaRepository<AddressTest, Long>{

}
