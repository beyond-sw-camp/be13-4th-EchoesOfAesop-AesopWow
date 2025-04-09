package com.aesopwow.echoesofaesop.auth.service;

import com.aesopwow.echoesofaesop.auth.dto.SignUpRequestDto;
import com.aesopwow.echoesofaesop.auth.dto.TokenResponseDto;
import com.aesopwow.echoesofaesop.auth.dto.VerificationOtpRequestDto;
import com.aesopwow.echoesofaesop.auth.exception.UnauthorizeException;
import com.aesopwow.echoesofaesop.auth.jwt.JwtTokenProvider;
import com.aesopwow.echoesofaesop.auth.repository.RoleTypeRepository;
import com.aesopwow.echoesofaesop.common.enums.RoleType;
import com.aesopwow.echoesofaesop.common.cache.CacheService;
import com.aesopwow.echoesofaesop.data.entity.user.RoleTypeEntity;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleTypeRepository roleTypeRepository;
    private final CacheService cacheService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean hasUserByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public void createUser(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        // String getVerified = cacheService.get(this.verifiedUserKey(username));
        //
        // if (getVerified == null) {
        //     throw new RuntimeException("인증되지 않은 사용자입니다.");
        // }

        userRepository.findByUsername(username).ifPresent(user -> {
            throw new RuntimeException("이미 가입된 아이디입니다.");
        });

        // cacheService.delete(this.verifiedUserKey(username));

        RoleTypeEntity roleType = roleTypeRepository.findByRoleType(RoleType.ROLE_USER).orElseThrow(() -> {
            throw new RuntimeException("Invalid RoleType");
        });
        
        User user = User.builder()
                .username(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .roleType(roleType)
                .build();
        userRepository.save(user);
    }

    private String verifiedUserKey(String username) {
        return "verified:" + username;
    }

    @Override
    public TokenResponseDto login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizeException("아이디 또는 비밀번호가 올바르지 않습니다."));

        if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
//        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizeException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(String.valueOf(user.getId()),
                        user.getUsername(),
                        String.valueOf(user.getRoleType().getRoleType())
                ),
                jwtTokenProvider.createRefreshToken(String.valueOf(user.getId()), user.getUsername())
        );
    }

    @Override
    public void logout(String bearerToken) {
        String accessToken = jwtTokenProvider.resolveToken(bearerToken);

        if (accessToken == null || !jwtTokenProvider.validateToken(accessToken)) {
            throw new UnauthorizeException("토큰이 유효하지 않습니다.");
        }

        jwtTokenProvider.addBlackList(accessToken);
        jwtTokenProvider.deleteRefreshToken(accessToken);

    }

    @Override
    public TokenResponseDto refresh(String bearerToken) {
        String refreshToken = jwtTokenProvider.resolveToken(bearerToken);

        if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
            throw new UnauthorizeException("토큰이 유효하지 않습니다.");
        }

        if (!jwtTokenProvider.isValidRefreshToken(refreshToken)) {
            throw new UnauthorizeException("토큰이 유효하지 않습니다.");
        }

        User user;
        user = userRepository.findByUsername(jwtTokenProvider.getUserUsername(refreshToken))
                .orElseThrow(() -> new UnauthorizeException("아이디 또는 비밀번호가 올바르지 않습니다."));

        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(String.valueOf(user.getId()),
                        user.getUsername(),
                        String.valueOf(user.getRoleType().getRoleType())
                ),
                refreshToken
        );
    }
}
