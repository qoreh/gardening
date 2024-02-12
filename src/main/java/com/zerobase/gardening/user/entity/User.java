package com.zerobase.gardening.user.entity;

import com.zerobase.gardening.type.UserRole;
import com.zerobase.gardening.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String phone;

    private UserStatus status;

    private UserRole role;

    private LocalDateTime registerDateTime;

    private LocalDateTime updateDateTime;

}
