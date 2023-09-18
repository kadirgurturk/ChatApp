package com.kadirgurturk.demo.data.entity;

import com.kadirgurturk.demo.data.enums.ChatType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
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

    @JoinColumn(name = "created_user")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User createdUser;


    private Set<User> users = new HashSet<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();


}