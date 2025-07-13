package com.projectx.common.entity;

import com.projectx.common.enums.Role; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data // Lombok getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok no-argument constructor
@AllArgsConstructor // Lombok all-argument constructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // We can prefer UUID too how to choose ? 

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Creates a separate table and match with main entity
                                               // Eager fetch all time but lazy fetch when needed
    @Enumerated(EnumType.STRING) // Specify how to store enum values in database
    private Set<Role> roles; // Should we use Set or List and should we use role or roles ? 

}