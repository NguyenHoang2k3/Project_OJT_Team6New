package org.example.projectspringojt.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.entity.Order;
import org.example.projectspringojt.entity.Status;
import org.example.projectspringojt.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PickUpController {

    private final OrderRepository orderRepository;


    @GetMapping("/renter/confirm/{orderId}")
    public String showRenterConfirmPage(@PathVariable Integer orderId, Model model) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            model.addAttribute("order", orderOptional.get());
        } else {
            model.addAttribute("error", "Order not found");
        }
        return "renter_confirm";
    }

    // Người thuê xác nhận đã nhận xe
    @PostMapping("/renter/confirm/{orderId}")
    public String confirmReceivedCar(
            @PathVariable Integer orderId,
            Model model) {

        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            model.addAttribute("error", "Order not found");
            return "renter_confirm";
        }

        Order order = orderOptional.get();
        if (order.getStatus() == Status.DONE) {
           order.setStatus(Status.DONE);
           orderRepository.save(order);
            model.addAttribute("success", "You have successfully confirmed receiving the car.");
        } else {
            model.addAttribute("error", "The car cannot be confirmed at this stage.");
            model.addAttribute("order", order);
            return "renter_confirm" ;
        }

        model.addAttribute("order", order);
      //  return "renter_confirm";
        return "redirect:/myCars#xe-cua-toi";
    }

    @GetMapping("/order/update/{orderId}")
    public String showUpdateStatusPage(@PathVariable Integer orderId, Model model) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            model.addAttribute("order", orderOptional.get());
            model.addAttribute("statuses", Status.values());
        } else {
            model.addAttribute("error", "Order not found");
        }
        return "update_status";
    }

    @PostMapping("/order/update/{orderId}")
    public String updateOrderStatus(
            @PathVariable Integer orderId,
            @RequestParam("status") Status status,
            Model model) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            model.addAttribute("error", "Order not found");
            return "update_status";
        }

        Order order = orderOptional.get();
        order.setStatus(status);
        orderRepository.save(order);
        model.addAttribute("success", "Status updated successfully.");
        model.addAttribute("order", order);
        model.addAttribute("statuses", Status.values());
       // return "update_status";
        return "redirect:/myCars#xe-cua-toi";
    }

//    @PostMapping("/renter/cancel/{orderId}")
//    public String cancelOrder(@PathVariable Integer orderId, Model model) {
//        Optional<Order> orderOptional = orderRepository.findById(orderId);
//        if (orderOptional.isEmpty()) {
//            model.addAttribute("error", "Order not found");
//            return "renter_confirm";
//        }
//
//        Order order = orderOptional.get();
//        if (order.getStatus() == Status.PROGRESS) { // Chỉ hủy được khi trạng thái đang xử lý
//            order.setStatus(Status.CANCELLED);
//            orderRepository.save(order);
//            model.addAttribute("success", "Order has been successfully cancelled.");
//        } else {
//            model.addAttribute("error", "Order cannot be cancelled at this stage.");
//        }
//
//        model.addAttribute("order", order);
//        return "renter_confirm";
//    }
//


    @GetMapping("/renter/reasoncancelled/{orderId}")
    public String showReasonCancelledPage(@PathVariable Integer orderId, Model model) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            model.addAttribute("error", "Không tìm thấy đơn hàng.");
            return "renter_confirm";
        }

        Order order = orderOptional.get();
        if (order.getStatus() == Status.CANCELLED || order.getStatus() == Status.DONE) {
            model.addAttribute("error", "Đơn hàng này đã bị hủy. Bạn không thể thực hiện lại hành động này.");
            model.addAttribute("order", order);
            return "renter_confirm"; // Trả về trang xác nhận với lỗi
        }

        model.addAttribute("orderId", orderId);
        return "reasoncancelled"; // Hiển thị trang chọn lý do hủy
    }

    @PostMapping("/renter/reasoncancelled/{orderId}")
    public String cancelOrderWithReason(
            @PathVariable Integer orderId,
            @RequestParam("reason") String reason,
            @RequestParam(value = "customReason", required = false) String customReason,
            Model model) {

        // Tìm đơn hàng
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            model.addAttribute("error", "Order not found");
            return "reasoncancelled"; // Quay lại trang chọn lý do nếu không tìm thấy đơn hàng
        }

        Order order = orderOptional.get();

        // Chỉ hủy được nếu trạng thái đang xử lý
        if (order.getStatus() != Status.PROGRESS) {
            model.addAttribute("error", "Order cannot be cancelled at this stage.");
            return "reasoncancelled";
        }else{

            // Xử lý lý do: Nếu chọn "Lý do khác", sử dụng lý do người dùng nhập
            String finalReason = reason.equals("Orther") && customReason != null && !customReason.trim().isEmpty()
                    ? customReason
                    : reason;

            // Cập nhật đơn hàng
            order.setStatus(Status.CANCELLED);
            order.setReasons(finalReason); // Lưu lý do hủy
            orderRepository.save(order);

            // Trả về thông báo thành công
            model.addAttribute("success", "Order has been cancelled with reason: " + finalReason);
            model.addAttribute("order", order);
            //   return "renter_confirm"; // Trả về trang xác nhận

            return "redirect:/myCars#xe-cua-toi";
        }




    }




}
