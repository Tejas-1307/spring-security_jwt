package com.jwt.springsecurity.controller;

import com.jwt.springsecurity.entity.AuthRequest;
import com.jwt.springsecurity.entity.UserInfo;
import com.jwt.springsecurity.service.JwtService;
import com.jwt.springsecurity.service.UserInfoDetails;
import com.jwt.springsecurity.service.UserInfoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to spring security";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invaild User request");
        }
    }

    @GetMapping("/getAllUsers")
    public List<UserInfo> getAllusers(){
        return userInfoService.getAllUser();
    }

    @GetMapping("getUserById/{id}")
    public UserInfo getUserById(@PathVariable Integer id){
        return userInfoService.getUser(id);
    }
}
