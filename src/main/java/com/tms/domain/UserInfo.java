package com.tms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
//@Schema(name = "Описание пользователя")
@Component
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
}
