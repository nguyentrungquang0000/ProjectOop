package com.example.Web0.AdminController;

import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.ProductEntity;
import com.example.Web0.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class Admin {
    private final ProductService productService;
    @GetMapping(value = "/")
    public ModelAndView home(@ModelAttribute ProductSearchRequest productSearchRequest) {
        List<ProductEntity> list = productService.searchProduct(productSearchRequest);
        ModelAndView mav = new ModelAndView("admin/dashboard");
        mav.addObject("listProducts", list);
        return mav;
    }

    @GetMapping(value = "/add-product")
    public ModelAndView addProduct() {
        ModelAndView mav = new ModelAndView("admin/addProduct");
        return mav;
    }

    @GetMapping(value = "/edit-product/{id}")
    public ModelAndView editProduct(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/editProduct");
        ProductEntity product = productService.getProduct(id);
        mav.addObject("product", product);
        return mav;
    }
}
