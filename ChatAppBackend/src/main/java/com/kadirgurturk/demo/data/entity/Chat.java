package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Chat'in sahip olduğu kullanıcılar ile ilişki tanımı (çoktan çoka)
    @ManyToMany
    @JoinTable(
            name = "users_chats",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    // Chat'in içerdiği mesajlar ile ilişki tanımı (birçoktan bire)
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private Set<Message> messages;

    // Diğer chat detayları ve özellikleri

    // Kurucu metodlar, getter ve setter metodları
}