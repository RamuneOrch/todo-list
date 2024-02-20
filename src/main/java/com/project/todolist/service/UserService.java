package com.project.todolist.service;

import com.project.todolist.Exception.UserExistenceException;
import com.project.todolist.dto.user.UserRequestDto;
import com.project.todolist.dto.user.UserResponseDto;
import com.project.todolist.entity.User;
import com.project.todolist.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequestDto req) {
        String username = req.getUsername();
        String password = passwordEncoder.encode(req.getPassword());

        validUser(username);

        // 사용자 등록
        User user = new User(username, password);

        userRepository.save(user);
        return new UserResponseDto(user);
    }

    private void validUser(String username) {
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new UserExistenceException("중복된 사용자가 존재합니다.");
        }
    }
}
