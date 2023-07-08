package com.scaler.splitwisejul23.dtos;

import com.scaler.splitwisejul23.models.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponseDto extends  BaseResponseDto {
    private Long id;
    private String name;
    private String description;
    private String createdByName;
}
