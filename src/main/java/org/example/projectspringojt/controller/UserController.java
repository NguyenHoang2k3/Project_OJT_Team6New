package org.example.projectspringojt.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.UserRepository;
import org.example.projectspringojt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserRepository userRepository;

  @GetMapping("/users")
  public String getAllUsers(Model model) {
    List<User> users = userService.getAllUsers(); // Lấy danh sách người dùng
    model.addAttribute("users", users); // Thêm danh sách người dùng vào model
    return "user"; // Trả về tên template (user.html)
  }
}
