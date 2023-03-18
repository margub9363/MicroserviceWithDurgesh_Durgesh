package com.lcwd.user.service.UserService.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name="Id")
    private String userId;

    @Column(name = "Name", length = 20)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "About")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
