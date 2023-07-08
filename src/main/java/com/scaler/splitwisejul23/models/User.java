package com.scaler.splitwisejul23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {

    private String name;
    private String phone;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "members")
    private List<Group> groups;
}
