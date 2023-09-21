package com.kadirgurturk.demo.data.repository;

import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.enums.ChatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    List<Chat> findByType(ChatType type);

    @Query("SELECT c FROM Chat c " +
            "WHERE c.type = 'PRIVATE' " +
            "AND :user MEMBER OF c.users " +
            "AND :reqUser MEMBER OF c.users")
    Chat findPrivateChatsByUsers(@Param("user") User user, @Param("reqUser") User reqUser);

    List<Chat> findByUsersId(Long userId);


}
