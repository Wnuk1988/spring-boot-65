package com.tms.security.service;

import com.tms.domain.Role;
import com.tms.domain.UserInfo;
import com.tms.repository.UserRepository;
import com.tms.security.domain.RegistrationDTO;
import com.tms.security.domain.SecurityCredentials;
import com.tms.security.repository.SecurityCredentialsRepository;
import com.tms.security.JwtUtils;
import com.tms.security.domain.AuthRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class SecurityService {
    private final SecurityCredentialsRepository securityCredentialsRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    private final UserInfo userInfo;
    private final SecurityCredentials securityCredentials;
    private final UserRepository userRepository;

    public SecurityService(SecurityCredentialsRepository securityCredentialsRepository, JwtUtils jwtUtils,
                           PasswordEncoder passwordEncoder, UserInfo userInfo, SecurityCredentials securityCredentials,
                           UserRepository userRepository) {
        this.securityCredentialsRepository = securityCredentialsRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userInfo = userInfo;
        this.securityCredentials = securityCredentials;
        this.userRepository = userRepository;
    }

    public String generateToken(AuthRequest authRequest){
        //1. достать юзера по логину
        //2. проверить логику
        //3. сгенерировать токен
        //4. если все плохо то возврощает ""
        Optional<SecurityCredentials> credentials = securityCredentialsRepository.findByUserLogin(authRequest.getLogin());
        if (credentials.isPresent() && passwordEncoder.matches(authRequest.getPassword(),credentials.get().getUserPassword())){
            return jwtUtils.generateJwtToken(authRequest.getLogin());
        }
           return "";
    }

    @Transactional(rollbackFor = Exception.class)
    public void registration(RegistrationDTO registrationDTO){
        //TODO: 0. Есть ли такой логин в базе, иначе Exception

        // 1. собрать юзера
        userInfo.setFirstName(registrationDTO.getFirstName());
        userInfo.setLastName(registrationDTO.getLastName());
        userInfo.setCreatedAt(LocalDateTime.now());
        userInfo.setUpdatedAt(LocalDateTime.now());
        UserInfo userInfoResult = userRepository.save(userInfo);

        // 2. собрать секьюрити
        securityCredentials.setUserLogin(registrationDTO.getUserLogin());
        securityCredentials.setUserPassword(passwordEncoder.encode(registrationDTO.getUserPassword()));
        securityCredentials.setUserRole(Role.USER);
        securityCredentials.setUserId(userInfoResult.getId());

        // 3. описать транзакцию и обработку
        securityCredentialsRepository.save(securityCredentials);
    }
}
