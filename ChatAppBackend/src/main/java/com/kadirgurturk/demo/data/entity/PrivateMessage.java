package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

}
