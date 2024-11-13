package org.example.projectspringojt.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
//    public List<User> getUsers(int page, int size) {
//      PageRequest pageRequest = PageRequest.of(page - 1, size);
//      return userRepository.findUsersWithPagination(pageRequest);
//    }

    public List<User> searchUsers(String keyword, int page, int size) {
      PageRequest pageRequest = PageRequest.of(page - 1, size);
      return userRepository.searchUsersWithPagination(keyword, pageRequest);
    }

//    public int countUsers() {
//      return userRepository.countAllUsers();
//    }
//
//    public int countUsersBySearch(String keyword) {
//      return userRepository.countUsersByKeyword(keyword);
//    }

    public User getUserById(Long userId) {
      return userRepository.findById(userId).orElse(null);
    }
//  public List<User> searchUsers(String searchQuery) {
//    if (searchQuery == null || searchQuery.isEmpty()) {
//      return new ArrayList<>(); // Nếu không có từ khóa, trả về danh sách rỗng
//    }
//    return userRepository.findByNameContainingOrEmailContaining(searchQuery, searchQuery);
//  }

}
