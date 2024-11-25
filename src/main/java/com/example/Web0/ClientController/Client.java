package com.example.Web0.ClientController;

import com.example.Web0.controller.CartController;
import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.*;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.service.OrderHistoryService;
import com.example.Web0.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class Client {
    private final ProductService productService;
    private final UserRepository userRepository;
    private final CartController controller;
    private final OrderHistoryService orderHistoryService;

    @GetMapping(value = "login")
    public ModelAndView loginPage() {
        return new ModelAndView("login/login");
    }

    @GetMapping(value = "/cart")
    public ModelAndView cartPage() {
        ModelAndView modelAndView = new ModelAndView("cart/Cart");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);
        CartEntity cart = user.getCart();
        List<CartItemEntity> cartItems = cart.getCartItems();
        modelAndView.addObject("listItems", cartItems);
        return modelAndView;
    }

    @GetMapping(value = {"/", ""})
    public ModelAndView homePage(@ModelAttribute ProductSearchRequest productSearchRequest) {
        List<ProductEntity> productEntities = productService.searchProduct(productSearchRequest);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("listProduct", productEntities);
        return modelAndView;
    }

    @GetMapping(value = "/menu")
    public ModelAndView menuPage(@ModelAttribute ProductSearchRequest productSearchRequest) {
        ModelAndView modelAndView = new ModelAndView("menu");
        List<ProductEntity> productEntities = productService.searchProduct(productSearchRequest);
        modelAndView.addObject("listProduct", productEntities);
        return modelAndView;
    }

    @GetMapping(value = "/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register/register");
    }

    @GetMapping(value = "/pay")
    public ModelAndView pay() {
        ModelAndView modelAndView = new ModelAndView("pay/Pay");
        List<CartItemEntity> cartItemEntities = controller.cartItem();
        modelAndView.addObject("listCartItem", cartItemEntities);
        return modelAndView;
    }

    @GetMapping(value = "/history-order")
    public ModelAndView historyOrder() {
        ModelAndView modelAndView = new ModelAndView("History/HistoryOrder");
        List<OrderHistoryEntity> orderHistoryEntities = orderHistoryService.getOrderHistory();
        modelAndView.addObject("orderHistoryEntities", orderHistoryEntities);
        return modelAndView;
    }

    @GetMapping(value = "/detail-{id}")
    public ModelAndView detailPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        ProductEntity product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        List<ProductEntity> list = productService.getProductAll();
        modelAndView.addObject("listProduct", list);
        return modelAndView;
    }
}
