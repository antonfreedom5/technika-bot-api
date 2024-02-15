package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.entities.User;
import com.yadchenko.botspectehnika.enums.Role;
import com.yadchenko.botspectehnika.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByIdOrCreate(User currentUser, Role role) {
        User user = userRepository.findById(currentUser.getId()).orElse(new User());
        if (user.getId() == null) {
            user.setId(currentUser.getId());
            user.setUsername(currentUser.getUsername());
            user.setFirstName(currentUser.getFirstName());
            user.setLastName(currentUser.getLastName());
            user.setRegistrationDate(new Date());
            user.setRole(role);
            return userRepository.save(user);
        }
        user.setRole(role);
        return user;
    }

    public User getByIdOrCreate(org.telegram.telegrambots.meta.api.objects.User currentUser, Role role) {
        User user = userRepository.findById(currentUser.getId()).orElse(new User());
        if (user.getId() == null) {
            user.setId(currentUser.getId());
            user.setUsername(currentUser.getUserName());
            user.setFirstName(currentUser.getFirstName());
            user.setLastName(currentUser.getLastName());
            user.setRegistrationDate(new Date());
            user.setRole(role);
            return userRepository.save(user);
        }
        user.setRole(role);
        return user;
    }

    public int getUsersCount() {
        return userRepository.getUsersCount();
    }
}
