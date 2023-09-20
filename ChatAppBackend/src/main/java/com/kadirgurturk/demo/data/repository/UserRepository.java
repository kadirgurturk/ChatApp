package com.kadirgurturk.demo.data.repository;

import com.kadirgurturk.demo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query("select u from User where u.first_name Like %:query% or u.email Like %:query%")
    List<User> searchUser(@Param("query") String query);

}
