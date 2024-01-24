package com.jwt.springsecurity.repository;

import com.jwt.springsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo>findByName(String username);
}
