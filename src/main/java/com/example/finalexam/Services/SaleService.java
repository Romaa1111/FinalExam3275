package com.example.finalexam.Services;

import com.example.finalexam.Entities.Sale;
import com.example.finalexam.Repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing sales data.
 * Handles business logic and interacts with the SaleRepository.
 */
@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository; // Repository for accessing sale data

    /**
     * Saves a sale record to the database.
     * If the sale record already exists (based on its ID), it will be updated.
     * Otherwise, a new record will be created.
     *
     * @param sale the Sale entity to be saved
     * @return the saved Sale entity
     */
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    /**
     * Retrieves all sale records from the database.
     *
     * @return a list of all Sale entities
     */
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    /**
     * Retrieves a sale record by its ID.
     * If no sale with the given ID is found, returns null.
     *
     * @param id the ID of the Sale entity to retrieve
     * @return the Sale entity with the given ID, or null if not found
     */
    public Sale getSaleById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.orElse(null); // Return the sale if present, otherwise return null
    }

    /**
     * Deletes a sale record by its ID.
     *
     * @param id the ID of the Sale entity to delete
     */
    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }
}
