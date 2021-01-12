package com.onlinegroceryshopping.demo.service;

import com.onlinegroceryshopping.demo.model.User;
import com.onlinegroceryshopping.demo.model.enums.Role;
import com.onlinegroceryshopping.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (Objects.nonNull(userFromDb)) {
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s!\n" +
                    "Thank you for signing up! In order to complete your account activation, click the link below or copy it into a browser window.\n\n" +
                    "http://localhost:8080/activate/%s", user.getUsername(), user.getActivationCode());

            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (Objects.isNull(user)) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);

        userRepo.save(user);

        return true;
    }
}
