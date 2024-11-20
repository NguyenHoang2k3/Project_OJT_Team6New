package org.example.projectspringojt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketsDiscountDTO {


    private Integer ticketsId;

    @NotBlank(message = "Tickets Name is required")
    private String ticketsName;

    @NotNull(message = "Discount is required")
    private Double discount;

    @NotNull(message = "User ID is required")
    private Long userId;
}
