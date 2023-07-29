package com.kadirgurturk.demo.data.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    // Mesajın ait olduğu chat ile ilişki tanımı
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    // Diğer mesaj detayları ve özellikleri

    // Kurucu metodlar, getter ve setter metodları
}
