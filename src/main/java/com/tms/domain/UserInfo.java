package com.tms.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
//@Schema(name = "Описание пользователя")
@EqualsAndHashCode(exclude = "updatedAt")
@Data
@Entity(name = "user_info")
public class UserInfo {
    //@Schema(name = "Уникальный индификатор пользователя")

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "user_info_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "mySeqGen")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "created")
    private LocalDateTime createdAt;
    @Column(name = "updated")
    private LocalDateTime updatedAt;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
