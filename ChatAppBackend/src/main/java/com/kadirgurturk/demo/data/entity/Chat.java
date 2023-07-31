package com.kadirgurturk.demo.data.entity;

import com.kadirgurturk.demo.data.enums.ChatType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats")
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ChatType type;

    // Chat'in sahip olduğu kullanıcılar ile ilişki tanımı (çoktan çoka)
    @ManyToMany
    @JoinTable(
            name = "users_chats",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    // Chat'in içerdiği mesajlar ile ilişki tanımı (birçoktan bire)
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messages;

    // Diğer chat detayları ve özellikleri

    // Kurucu metodlar, getter ve setter metodları
}