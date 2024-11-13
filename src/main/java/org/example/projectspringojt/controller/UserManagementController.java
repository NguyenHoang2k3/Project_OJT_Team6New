package org.example.projectspringojt.controller;


import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.UserRepository;
import org.example.projectspringojt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserManagementController {
  private final UserService userService;
  private final UserRepository userRepository;
  private static final int USERS_PER_PAGE = 10;

  // Display the User Management page
  @GetMapping("/manageUser")
  public String manageUsers(
      @RequestParam(value = "index", defaultValue = "1") Integer index,
      @RequestParam(value = "size", defaultValue = "5") Integer size,
      Model model
  ) {
    Pageable pageable = PageRequest.of(index-1,size);

    Page<User> pageUser = userRepository.findAll(pageable);
    List<User> listUsers = pageUser.getContent();

    model.addAttribute("listUsers", listUsers);
    model.addAttribute("currentPage", index);
    model.addAttribute("size", size);
    model.addAttribute("totalPages", pageUser.getTotalPages());

    return "/admin/usermanagement"; // Name of the Thymeleaf HTML template
  }
//  @PostMapping("/admin/search")
//  public String searchUsers(@RequestParam("searchQuery") String searchQuery, Model model) {
//    // Tìm kiếm người dùng qua service
//    List<User> users = userService.searchUsers(searchQuery);
//
//    // Thêm kết quả vào model để hiển thị lên trang
//    model.addAttribute("users", users);
//    model.addAttribute("searchQuery", searchQuery); // Để giữ lại query khi gửi lại form
//
//    return "admin/usermanagement2"; // Đường dẫn đến view trang kết quả
//  }

  @GetMapping("/search")
  public String search( @RequestParam("search") String search,
      Model model) {

    List<User> listUsers =  userRepository.findByAllFields("%"+search+"%");
    model.addAttribute("listUsers", listUsers);
    return "/admin/usermanagement";
  }

  @GetMapping("/test")
  public String test() {

    return "/admin/sidebar";
  }
  // Handle user edit action
  @GetMapping("/profile")
  public String editUser(@RequestParam("userID") Long userId, Model model) {
    User user = userService.getUserById(userId);
    model.addAttribute("user", user);
    return "userProfile";
  }

  @GetMapping("/manageUser/{userID}")
  public String updateForm(@PathVariable Long userID, Model model) {
    User user = userRepository.findById(userID).orElseThrow(() -> {
      throw new EntityNotFoundException("Not found entity with id: " + userID);
    });
    User createUser = new User();
    BeanUtils.copyProperties(user, createUser);
    model.addAttribute("user", createUser);
    return "/admin/userdeltail";
  }



}