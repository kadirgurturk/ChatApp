package com.kadirgurturk.demo.data.repository;

import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.enums.ChatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    List<Chat> findByType(ChatType type);

}
