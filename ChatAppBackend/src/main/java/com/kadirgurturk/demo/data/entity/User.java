package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "password")
        private String password;

        @Column(name = "email")
        private String email;

        @Column(name = "avatarId")
        private Integer avatarId;

        @Column(name = "active")
        private boolean active;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_date")
        private LocalDateTime createdDate;

        @ManyToMany(mappedBy = "users")
        private List<Chat> chats;

}
