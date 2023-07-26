package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Diğer chatroom bilgileri ve diğer ilişkiler buraya eklenir.

    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL)
    private List<ChatroomMessage> chatroomMessages;
}
