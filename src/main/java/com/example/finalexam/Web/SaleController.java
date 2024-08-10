package com.example.finalexam.Web;

import com.example.finalexam.Entities.Sale;
import com.example.finalexam.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing sales-related operations.
 * Handles HTTP requests related to sales, such as showing the sales form, saving,
 * updating, deleting sales, and viewing the sales report.
 */
@Controller
@RequestMapping("/services")
public class SaleController {

    @Autowired
    private SaleService saleService; // Service for handling business logic related to sales

    /**
     * Displays the form for creating a new sale.
     *
     * @param model The model to hold the Sale object for the form.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/sales")
    public String showSalesForm(Model model) {
        model.addAttribute("sale", new Sale()); // Add an empty Sale object to the model
        return "salesForm"; // Return the view name for the sales form
    }

    /**
     * Handles the submission of the sales form to save a new sale.
     *
     * @param sale The Sale object submitted from the form.
     * @param model The model to hold error messages if validation fails.
     * @return A redirect to the sales report if successful, or the sales form if validation fails.
     */
    @PostMapping("/saveSale")
    public String saveSale(@ModelAttribute Sale sale, Model model) {
        // Validate the Sale object
        if (sale.getName() == null || sale.getItemType() == null || sale.getSalesAmount() <= 0 || sale.getTransactionDate() == null) {
            model.addAttribute("errorMessage", "All fields must be filled out correctly."); // Add error message to the model
            return "salesForm"; // Return to the sales form if validation fails
        }
        saleService.saveSale(sale); // Save the Sale object using the service
        return "redirect:/services/salesReport"; // Redirect to the sales report page upon successful save
    }

    /**
     * Displays the sales report showing all sales.
     *
     * @param model The model to hold the list of sales.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/salesReport")
    public String viewSalesReport(Model model) {
        List<Sale> sales = saleService.getAllSales(); // Retrieve all sales from the service
        model.addAttribute("sales", sales); // Add the list of sales to the model
        return "salesReport"; // Return the view name for the sales report
    }

    /**
     * Displays the form for editing an existing sale.
     *
     * @param id The ID of the sale to be edited.
     * @param model The model to hold the Sale object for editing.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/editSale/{id}")
    public String editSale(@PathVariable("id") Long id, Model model) {
        Sale sale = saleService.getSaleById(id); // Retrieve the sale by ID from the service
        model.addAttribute("sale", sale); // Add the Sale object to the model
        return "editSale"; // Return the view name for the edit form
    }

    /**
     * Handles the submission of the sales edit form to update an existing sale.
     *
     * @param sale The Sale object with updated information.
     * @return A redirect to the sales report page upon successful update.
     */
    @PostMapping("/updateSale")
    public String updateSale(@ModelAttribute Sale sale) {
        saleService.saveSale(sale); // Save the updated Sale object using the service
        return "redirect:/services/salesReport"; // Redirect to the sales report page upon successful update
    }

    /**
     * Handles the deletion of a sale by its ID.
     *
     * @param id The ID of the sale to be deleted.
     * @return A redirect to the sales report page upon successful deletion.
     */
    @GetMapping("/deleteSale/{id}")
    public String deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSaleById(id); // Delete the sale by ID using the service
        return "redirect:/services/salesReport"; // Redirect to the sales report page upon successful deletion
    }
}
