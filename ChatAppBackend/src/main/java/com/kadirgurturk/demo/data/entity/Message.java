package com.kadirgurturk.demo.data.entity;

import com.kadirgurturk.demo.data.enums.MessageStatus;
import com.kadirgurturk.demo.data.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MessageStatus status;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MessageType type;

    // Mesajın ait olduğu chat ile ilişki tanımı
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    // Diğer mesaj detayları ve özellikleri

    // Kurucu metodlar, getter ve setter metodları
}
