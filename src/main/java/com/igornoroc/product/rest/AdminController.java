package com.igornoroc.product.rest;

import com.igornoroc.product.model.Admin;
import com.igornoroc.product.model.Product;
import com.igornoroc.product.service.AdminService;
import com.igornoroc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final AuthenticationManager authenticationManager;
    private final AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        UserDetails userDetailsService = adminService.loadUserByUsername(admin.getLogin());
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetailsService,
                admin.getPassword(),
                userDetailsService.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        return "success";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_product")
    public Product saveOrUpdate(@RequestBody @Valid Product product) {
        return productService.saveOrUpdate(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find_product/{id}")
    public Product findById(@PathVariable @NotNull Long id) {
        return adminService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_product/{id}")
    public void deleteProduct(@PathVariable @NotNull Long id) {
        productService.delete(id);
    }
}
