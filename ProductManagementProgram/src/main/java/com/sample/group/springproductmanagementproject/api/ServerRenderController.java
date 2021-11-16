package com.sample.group.springproductmanagementproject.api;

import com.sample.group.springproductmanagementproject.dto.ProductDto;
import com.sample.group.springproductmanagementproject.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "api/main")
public class ServerRenderController {

    private final ProductServiceInterface productServiceInterface;

    @Autowired
    public ServerRenderController(ProductServiceInterface productServiceInterface) {
        this.productServiceInterface = productServiceInterface;
    }

    @GetMapping("/search/display")
    public String displayProductsServer(Model model) {
        model.addAttribute("products", productServiceInterface.displayProducts());
        return "display";
    }

    @GetMapping("/search/id/{productID}")
    public String displayObjectByIDServer(@PathVariable Long productID, Model model) {
        model.addAttribute("productID", productServiceInterface.searchByID(productID));
        return "productID";
    }

    @GetMapping("/search/name/{productName}")
    public String displayObjectByNameServer(@PathVariable String productName, Model model) {
        model.addAttribute("productName", productServiceInterface.searchByName(productName));
        return "productName";
    }

    @GetMapping("/addProduct")
    public String createProductServerLandingPage(Model model) {
        ProductDto dto = new ProductDto();
        model.addAttribute("product", dto);
        return "addPageForm";
    }

    @PostMapping("/saveProduct")
    public String createProductServer(@ModelAttribute("product") ProductDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "addPageForm";
        }
        productServiceInterface.createProduct(dto);
        return "redirect:/api/main/search/display";
    }


    @GetMapping("search/update/{productID}")
    public String updateProductServerLandingPage(@PathVariable Long productID, Model model) {
        final ProductDto dto = productServiceInterface.searchByIDSingleEntity(productID);
        model.addAttribute("productDto", dto);
        model.addAttribute("productID", productID);

        return "update";
    }

    @PostMapping(path = "{productID}")
    public String updateProductServer(@PathVariable("productID") Long productID, @Valid ProductDto dto) {
        productServiceInterface.updateProduct(productID, dto);
        return "redirect:/api/main/search/display";
    }

    @GetMapping(path = "/search/delete/{productID}")
    public String deleteProductServer(@PathVariable("productID") Long productID) {
        productServiceInterface.deleteProduct(productID);
        return "redirect:/api/main/search/display";
    }

}
