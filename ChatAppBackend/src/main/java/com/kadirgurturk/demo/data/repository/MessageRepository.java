package com.kadirgurturk.demo.data.repository;

import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findByChatId(Long chatId);
}
