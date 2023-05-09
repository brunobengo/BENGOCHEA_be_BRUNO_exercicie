package com.ecore.roles.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(name = "display_name", nullable = false, length = 20)
    private String displayName;

    @Column(name = "avatar_url", nullable = true, length = 255)
    private String avatarUrl;

    @Column(name = "location", nullable = false, length = 100)
    private String location;


    public User(UUID id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }
}
