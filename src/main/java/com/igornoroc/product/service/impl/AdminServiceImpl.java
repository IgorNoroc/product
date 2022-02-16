package com.igornoroc.product.service.impl;

import com.igornoroc.product.exception.AdminNotFoundException;
import com.igornoroc.product.exception.ProductForAdminNotFoundException;
import com.igornoroc.product.model.Admin;
import com.igornoroc.product.model.Product;
import com.igornoroc.product.repository.AdminRepository;
import com.igornoroc.product.repository.ProductRepository;
import com.igornoroc.product.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductForAdminNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByLogin(login);
        if (admin == null) {
            throw new AdminNotFoundException();
        }
        return new User(
                admin.getLogin(),
                admin.getPassword(),
                mapToSimpleGrantedAuthority(admin.getRole())
        );
    }

    /**
     * Convert to SimpleGrantedAuthority
     * @param role role.
     * @return List of SimpleGrantedAuthority.
     */
    private List<SimpleGrantedAuthority> mapToSimpleGrantedAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
