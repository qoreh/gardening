package com.zerobase.gardening.user.repository;

import com.zerobase.gardening.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
