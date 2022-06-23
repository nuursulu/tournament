package com.example.mycli.web;

import com.example.mycli.exceptions.RoleNotFound;
import com.example.mycli.exceptions.exception.TournamentException;
import com.example.mycli.model.RoleEntity;
import com.example.mycli.model.UserEntity;
import com.example.mycli.model.entity.Participant;
import com.example.mycli.repository.RoleEntityRepository;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityRepository.save(userEntity);
    }
//    public void updateUser(String username, String role) {
////        if (!role.equals("USER_MODERATOR") && !role.equals("ROLE_USER")) {
////            throw new RoleNotFound(role);
////        }
//        UserEntity userEntity = userEntityRepository.findByLogin(username);
////        if (userEntity != null) {
//            userEntityRepository.updateUserRole(userEntity.getLogin(), role);
////        }
//    }
    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = userEntityRepository.findByLogin(login);
        System.out.println(userEntity + " - user entity");
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}

