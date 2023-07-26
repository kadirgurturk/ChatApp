package com.kadirgurturk.demo.data.repository;

import com.kadirgurturk.demo.data.entity.Chatroom;
import com.kadirgurturk.demo.data.entity.ChatroomMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomMessageRepository extends JpaRepository<ChatroomMessage,Long> {
}
