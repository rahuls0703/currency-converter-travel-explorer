package com.travelconverter.repository;

import com.travelconverter.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByCurrencyCode(String currencyCode);
}
