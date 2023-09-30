package com.graphqlapp.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
@Data
public class User extends BaseEntity {


    private String userName;
    private String mail;

    @Enumerated(EnumType.STRING)
    private Role role;

}
