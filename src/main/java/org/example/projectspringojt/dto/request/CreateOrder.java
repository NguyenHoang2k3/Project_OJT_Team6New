package org.example.projectspringojt.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.example.projectspringojt.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class CreateOrder {

    private Integer orderId;

    private LocalDate OrderStartDate;

    private LocalDate OrderEndDate;

    private BigDecimal orderPrice;

    private String sh_address;

    private Status Status;

}
