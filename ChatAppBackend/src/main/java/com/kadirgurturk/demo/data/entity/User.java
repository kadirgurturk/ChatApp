package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name")
        private String name;



        @Column(name = "active")
        private boolean active;

        // Kullanıcının sahip olduğu chatler ile ilişki tanımı (birçoktan çoka)
        @ManyToMany(mappedBy = "users")
        private List<Chat> chats;

        // Diğer kullanıcı detayları ve özellikleri

        // Kurucu metodlar, getter ve setter metodları
}
