package com.aesopwow.echoesofaesop.auth.service;

import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        log.info("Email : {}", user.getEmail());

        return user;
    }
}
