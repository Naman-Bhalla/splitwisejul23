package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupMemberRequestDto {
    private String userId;
    private String groupId;
    private String newMemberId;
}
