package com.yadchenko.botspectehnika.repository;

import com.yadchenko.botspectehnika.entities.User;
import com.yadchenko.botspectehnika.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value ="select count(*) from users u where u.role = 'CLIENT'")
    public int getUsersCount();

    public List<User> findAllByRole(Role role);
}
