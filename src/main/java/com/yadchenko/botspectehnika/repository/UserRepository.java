package com.yadchenko.botspectehnika.repository;

import com.yadchenko.botspectehnika.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value ="select count(*) from users u where u.role = 'CLIENT'")
    public int getUsersCount();
}
