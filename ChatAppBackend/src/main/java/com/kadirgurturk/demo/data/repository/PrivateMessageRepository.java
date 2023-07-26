package com.kadirgurturk.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessageRepository,Long> {
}
