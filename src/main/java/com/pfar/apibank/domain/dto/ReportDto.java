package com.pfar.apibank.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Date date;
    private String nameClient;
    private String numberAccount;
    private String type;
    private BigDecimal initialBalance;
    private Boolean state;
    private BigDecimal amount;
    private BigDecimal availableBalance;

    public ReportDto(Object[] objects) {
        this.date = (Date) objects[0];
        this.nameClient = (String) objects[1];
        this.numberAccount = (String) objects[2];
        this.type = (String) objects[3];
        this.initialBalance = (BigDecimal) objects[4];
        this.state = (Boolean) objects[5];
        this.amount = (BigDecimal) objects[6];
        this.availableBalance = (BigDecimal) objects[7];
    }

}
