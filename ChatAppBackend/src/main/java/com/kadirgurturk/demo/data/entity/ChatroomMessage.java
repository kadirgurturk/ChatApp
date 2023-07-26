package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChatroomMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;
}
