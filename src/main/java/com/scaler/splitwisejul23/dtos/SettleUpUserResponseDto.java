package com.scaler.splitwisejul23.dtos;

import com.scaler.splitwisejul23.strategies.settleupstrategy.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    String message;
    String status;
    List<Transaction> transactions;
}
