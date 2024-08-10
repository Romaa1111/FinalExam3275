package com.example.finalexam;
import com.example.finalexam.Entities.Sale;
import com.example.finalexam.Services.SaleService;
import com.example.finalexam.Web.SaleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the SaleController.
 * This class verifies the behavior of the SaleController by testing its various endpoints.
 */
@WebMvcTest(SaleController.class)  // Indicates that this is a Spring MVC test focusing on the SaleController.
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc is used to perform and verify HTTP requests and responses.

    @Mock
    private SaleService saleService;  // Mocking the SaleService to simulate interactions with the service layer.

    @InjectMocks
    private SaleController saleController;  // Injecting the mocked SaleService into the SaleController.

    /**
     * Set up method to initialize mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks.
        mockMvc = MockMvcBuilders.standaloneSetup(saleController).build();  // Set up MockMvc for the SaleController.
    }

    /**
     * Test case for displaying the sales form.
     * Verifies that GET requests to /services/sales return the salesForm view with a Sale model attribute.
     */
    @Test
    public void testShowSalesForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/services/sales"))  // Perform a GET request to /services/sales.
                .andExpect(status().isOk())  // Expect HTTP 200 status.
                .andExpect(MockMvcResultMatchers.view().name("salesForm"))  // Expect the salesForm view.
                .andExpect(MockMvcResultMatchers.model().attributeExists("sale"));  // Expect the model to have a "sale" attribute.
    }

    /**
     * Test case for saving a sale.
     * Verifies that POST requests to /services/saveSale correctly save the sale and redirect to the sales report.
     */
    @Test
    public void testSaveSale() throws Exception {
        Sale sale = new Sale();
        sale.setName("Jessica Lam");
        sale.setItemType("Washing Machine");
        sale.setSalesAmount(5000.0);
        sale.setTransactionCode("23");

        mockMvc.perform(MockMvcRequestBuilders.post("/services/saveSale")  // Perform a POST request to /services/saveSale.
                        .param("name", "Jessica Lam")
                        .param("itemType", "Washing Machine")
                        .param("salesAmount", "5000.0")
                        .param("transactionCode", "23"))
                .andExpect(status().is3xxRedirection())  // Expect HTTP 3xx redirection status.
                .andExpect(MockMvcResultMatchers.redirectedUrl("/services/salesReport"));  // Expect redirection to /services/salesReport.
    }

    /**
     * Test case for viewing the sales report.
     * Verifies that GET requests to /services/salesReport display the salesReport view with a list of sales.
     */
    @Test
    public void testViewSalesReport() throws Exception {
        List<Sale> sales = new ArrayList<>();
        sales.add(new Sale());  // Add a dummy sale to the list.
        when(saleService.getAllSales()).thenReturn(sales);  // Mock the service to return the list of sales.

        mockMvc.perform(MockMvcRequestBuilders.get("/services/salesReport"))  // Perform a GET request to /services/salesReport.
                .andExpect(status().isOk())  // Expect HTTP 200 status.
                .andExpect(MockMvcResultMatchers.view().name("salesReport"))  // Expect the salesReport view.
                .andExpect(MockMvcResultMatchers.model().attribute("sales", sales));  // Expect the model to have a "sales" attribute with the list of sales.
    }

    /**
     * Test case for displaying the edit sale form.
     * Verifies that GET requests to /services/editSale/{id} display the editSale view with the sale data.
     */
    @Test
    public void testEditSale() throws Exception {
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setName("Jessica Lam");

        when(saleService.getSaleById(1L)).thenReturn(sale);  // Mock the service to return the sale with ID 1L.

        mockMvc.perform(MockMvcRequestBuilders.get("/services/editSale/1"))  // Perform a GET request to /services/editSale/1.
                .andExpect(status().isOk())  // Expect HTTP 200 status.
                .andExpect(MockMvcResultMatchers.view().name("editSale"))  // Expect the editSale view.
                .andExpect(MockMvcResultMatchers.model().attribute("sale", sale));  // Expect the model to have a "sale" attribute with the sale data.
    }

    /**
     * Test case for updating a sale.
     * Verifies that POST requests to /services/updateSale update the sale and redirect to the sales report.
     */
    @Test
    public void testUpdateSale() throws Exception {
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setName("Jessica Lam");
        sale.setItemType("Washing Machine");
        sale.setSalesAmount(5000.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/services/updateSale")  // Perform a POST request to /services/updateSale.
                        .param("id", "1")
                        .param("name", "Jessica Lam")
                        .param("itemType", "Washing Machine")
                        .param("salesAmount", "5000.0"))
                .andExpect(status().is3xxRedirection())  // Expect HTTP 3xx redirection status.
                .andExpect(MockMvcResultMatchers.redirectedUrl("/services/salesReport"));  // Expect redirection to /services/salesReport.
    }

    /**
     * Test case for deleting a sale.
     * Verifies that GET requests to /services/deleteSale/{id} delete the sale and redirect to the sales report.
     */
    @Test
    public void testDeleteSale() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/services/deleteSale/1"))  // Perform a GET request to /services/deleteSale/1.
                .andExpect(status().is3xxRedirection())  // Expect HTTP 3xx redirection status.
                .andExpect(MockMvcResultMatchers.redirectedUrl("/services/salesReport"));  // Expect redirection to /services/salesReport.

        verify(saleService, times(1)).deleteSaleById(1L);  // Verify that deleteSaleById method was called once with ID 1L.
    }
}
