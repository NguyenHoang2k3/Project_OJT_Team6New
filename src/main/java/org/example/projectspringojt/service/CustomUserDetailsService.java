package org.example.projectspringojt.service;

import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUserName());
//        builder.password(user.getPassword());
//        builder.roles(user.getRole().name()); // make sure `role` field in User entity is an enum
//        return builder.build();
//    }
//}
