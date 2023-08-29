package com.tms.security;

import com.tms.domain.SecurityCredentials;
import com.tms.repository.SecurityCredentialsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final SecurityCredentialsRepository securityCredentialsRepository;

    public CustomUserDetailService(SecurityCredentialsRepository securityCredentialsRepository) {
        this.securityCredentialsRepository = securityCredentialsRepository;
    }
// 401 добавить
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityCredentials securityCredentials = securityCredentialsRepository.findByUserLogin(username);
        if (securityCredentials == null){
            throw  new UsernameNotFoundException(username);
        }
        return User
                .withUsername(securityCredentials.getUserLogin())
                .password(securityCredentials.getUserPassword())
                .roles(securityCredentials.getUserRole().toString())
                .build();
    }
}
