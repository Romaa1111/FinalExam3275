package com.example.finalexam;

import com.example.finalexam.Entities.Sale;
import com.example.finalexam.Repositories.SaleRepository;
import com.example.finalexam.Services.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for SaleService.
 * Uses Mockito to mock SaleRepository and test the business logic in SaleService.
 */
@SpringBootTest
public class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository; // Mocked repository to isolate the service layer

    @InjectMocks
    private SaleService saleService; // Service under test, with mocked repository injected

    /**
     * Initializes mocks before each test.
     * This method is called before each test method.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the saveSale() method of SaleService.
     * Verifies that the sale record is saved and returned correctly.
     */
    @Test
    public void testSaveSale() {
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setName("John Doe");
        sale.setItemType("Washing Machine");
        sale.setSalesAmount(500.0);
        sale.setTransactionCode("12345");

        // Mock the behavior of save method to return the sale object
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        // Call the saveSale method and verify the results
        Sale savedSale = saleService.saveSale(sale);
        assertNotNull(savedSale);
        assertEquals("John Doe", savedSale.getName());
        assertEquals("Washing Machine", savedSale.getItemType());
    }

    /**
     * Tests the getAllSales() method of SaleService.
     * Verifies that the list of all sales is retrieved correctly.
     */
    @Test
    public void testGetAllSales() {
        List<Sale> sales = new ArrayList<>();
        Sale sale1 = new Sale();
        sale1.setName("John Doe");
        sale1.setItemType("Washing Machine");
        sales.add(sale1);

        // Mock the behavior of findAll method to return the list of sales
        when(saleRepository.findAll()).thenReturn(sales);

        // Call the getAllSales method and verify the results
        List<Sale> allSales = saleService.getAllSales();
        assertNotNull(allSales);
        assertEquals(1, allSales.size());
        assertEquals("John Doe", allSales.get(0).getName());
    }

    /**
     * Tests the getSaleById() method of SaleService.
     * Verifies that a sale record with a given ID is retrieved correctly.
     */
    @Test
    public void testGetSaleById() {
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setName("John Doe");
        sale.setItemType("Refrigerator");

        // Mock the behavior of findById method to return the sale with the given ID
        when(saleRepository.findById(1L)).thenReturn(Optional.of(sale));

        // Call the getSaleById method and verify the results
        Sale foundSale = saleService.getSaleById(1L);
        assertNotNull(foundSale);
        assertEquals("John Doe", foundSale.getName());
    }

    /**
     * Tests the deleteSaleById() method of SaleService.
     * Verifies that the delete operation is performed on the repository.
     */
    @Test
    public void testDeleteSaleById() {
        // Call the deleteSaleById method and verify that deleteById is called once
        saleService.deleteSaleById(1L);
        verify(saleRepository, times(1)).deleteById(1L);
    }
}
