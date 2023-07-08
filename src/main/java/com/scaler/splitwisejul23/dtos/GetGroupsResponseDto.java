package com.scaler.splitwisejul23.dtos;

import com.scaler.splitwisejul23.controllers.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetGroupsResponseDto extends BaseResponseDto {
    List<UserGroup> userGroups;
    @Getter
    @Setter
    public static class UserGroup {
        private Long groupId;
        private String name;
        private String description;
    }

    @Override
    public void printResponse() {
        super.printResponse();
        if (getStatus() != null && getStatus().equals(ResponseStatus.SUCCESS)) {
            System.out.println("This user is member of below groups: ");
            for (UserGroup group: userGroups) {
                System.out.println("ID: " + group.getGroupId()+", Name: " + group.name);
            }
        }
    }
}
