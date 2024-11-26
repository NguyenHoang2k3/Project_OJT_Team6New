package org.example.projectspringojt.controller;


import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Role;
import org.example.projectspringojt.entity.Status;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.CarRepository;
import org.example.projectspringojt.repository.OrderRepository;
import org.example.projectspringojt.repository.UserRepository;
import org.example.projectspringojt.service.CarService;
import org.example.projectspringojt.service.UserService;
import org.example.projectspringojt.service.impl.CarServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
  private final CarRepository carRepository;
  private final CarServiceImpl carService;
  private static final int USERS_PER_PAGE = 10;
  private final OrderRepository orderRepository;

  // Display the User Management page
  @GetMapping("/manageUser")
  public String manageUsers(
      @RequestParam(value = "index", defaultValue = "1") Integer index,
      @RequestParam(value = "size", defaultValue = "8") Integer size,
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
  @GetMapping("/acpCar")
  public String acpCars(
      @RequestParam(value = "index", defaultValue = "1") Integer index,
      @RequestParam(value = "size", defaultValue = "8") Integer size,
      Model model
  ) {
    Pageable pageable = PageRequest.of(index-1,size);

    Page<Car> pageUser = carRepository.findAll(pageable);
    List<Car> listCars = pageUser.getContent();

    model.addAttribute("listCars", listCars);
    model.addAttribute("currentPage", index);
    model.addAttribute("size", size);
    model.addAttribute("totalPages", pageUser.getTotalPages());

    return "/admin/acpcar"; // Name of the Thymeleaf HTML template
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

  @GetMapping("/search2")
  public String search2( @RequestParam("search") String search,
      Model model) {

    List<Car> listCars =  carRepository.findByAllFields("%"+search+"%");
    model.addAttribute("listCars", listCars);
    return "/admin/acpcar";
  }

  @GetMapping("/test")
  public String test() {

    return "/admin/sidebar";
  }

  @GetMapping("/test2")
  public String test2() {

    return "/admin/userdeltail";
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
  @GetMapping("/acpCar/{carId}")
  public String updateForm2(@PathVariable Integer carId, Model model) {
    Car car = carRepository.findById(carId).orElseThrow(() -> {
      throw new EntityNotFoundException("Not found entity with id: " + carId);
    });
    Car createUser = new Car();
    BeanUtils.copyProperties(car, createUser);
    model.addAttribute("car", createUser);
    return "/admin/cardetail";
  }

  @PostMapping("/ban")
  public String banUser(@RequestParam Long ID,
      @RequestParam String reasonBan,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate timeBan) {
    userService.banUser(ID, reasonBan, timeBan);
    return "redirect:/admin/manageUser"; // Redirect to the appropriate page
  }
  @PostMapping("/ban2")
  public String banCar(@RequestParam Integer ID,
      @RequestParam String reasonBan,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate timeBan) {
    carService.rejectCar(ID, reasonBan, timeBan);
    return "redirect:/admin/acpCar";
  }

  @PostMapping("/ChangeStatusCarAcp")
  public String changeCarStatus(
      @RequestParam Integer carId,
      @RequestParam Boolean status) {

    carService.changeCarStatus(carId, status);

    // Redirect back to the appropriate page after status change
    return "redirect:/admin/acpCar";
  }

   @GetMapping("/dashboard")
   public String HomeAdmin(Model model){
     long countCar = carRepository.count();
     long countOrder = orderRepository.countByStatus(Status.DONE);
     long countCancelOrder = orderRepository.countByStatus(Status.CANCELLED);
     long countCustomer = userRepository.countByRole(Role.CUSTOMER);
     long countCarOwner = userRepository.countByRole(Role.CAR_OWNER);
     List<Car> lisCar = carRepository.findTop9ByAcpCarStatusTrueOrderByCarIdDesc();
     List<Long> monthlyRevenue = orderRepository.getMonthlyRevenueByYear();
     List<String> yearRevenue = orderRepository.getYearRevenue();
     model.addAttribute("countCar", countCar);
     model.addAttribute("countOrder", countOrder);
     model.addAttribute("countCancelOrder", countCancelOrder);
     model.addAttribute("countCarOwner", countCarOwner);
     model.addAttribute("countCustomer", countCustomer);
     model.addAttribute("lisCar", lisCar);
     model.addAttribute("monthlyRevenue", monthlyRevenue);
     model.addAttribute("yearRevenue", yearRevenue);
     return "/admin/dashboard";
   }


}