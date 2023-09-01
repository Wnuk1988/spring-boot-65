package com.tms.security.domain;

import com.tms.domain.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "security_credentials")
@Data
public class SecurityCredentials {
    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "security_credentials_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "mySeqGen")
    private Long id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role userRole;
    @Column(name = "user_id")
    private Integer userId;
}
