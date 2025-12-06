package com.patinaje.v1.service;

import com.patinaje.v1.model.User;
import com.patinaje.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        
        if (!user.getActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        // Convertir el rol a GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Spring Security requiere el prefijo "ROLE_"
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.getActivo())
                .build();
    }
}
