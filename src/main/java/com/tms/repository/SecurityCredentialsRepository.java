package com.tms.repository;

import com.tms.domain.SecurityCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityCredentialsRepository extends JpaRepository<SecurityCredentials,Long> {
    SecurityCredentials findByUserLogin(String login);
}
