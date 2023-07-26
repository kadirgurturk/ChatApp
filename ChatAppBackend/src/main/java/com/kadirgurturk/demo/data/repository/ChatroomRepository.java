package com.kadirgurturk.demo.data.repository;

import com.kadirgurturk.demo.data.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom,Long> {
}
