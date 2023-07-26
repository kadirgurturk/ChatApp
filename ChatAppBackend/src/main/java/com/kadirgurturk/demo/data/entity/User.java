package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Entity
@Data
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String email;

        private String username;

        // Diğer kullanıcı bilgileri ve diğer ilişkiler buraya eklenir.

        @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
        private List<PrivateMessage> sentPrivateMessages;

        // Getter, setter ve diğer metotlar buraya eklenir.
    }

