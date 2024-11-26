package org.example.projectspringojt.config;

//import org.example.projectspringojt.entity.StatusUser;
//import org.example.projectspringojt.entity.User;
//import org.example.projectspringojt.entity.Role;
//import org.example.projectspringojt.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//public class InitialAdminConfig {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CommandLineRunner createInitialAdmin() {
//        return args -> {
//            // Check if an admin user already exists
//            if (userRepository.findByUserName("admin").isEmpty()) {
//                // Create a new admin user
//                User admin = new User();
//                admin.setUserName("admin");
//                admin.setPassword(passwordEncoder.encode("1234")); // Set an initial password
//                admin.setEmail("admin@admin.com");
//                admin.setPhone("012345678");
//                admin.setRole(Role.ADMIN); // Make sure Role.ADMIN exists in your Role enum
//                admin.setStatus(StatusUser.Active); // Ensure the user is active
//
//                userRepository.save(admin);
//                System.out.println("Admin user created with username 'admin' and password 'admin123'");
//            }
//        };
//    }
//}
