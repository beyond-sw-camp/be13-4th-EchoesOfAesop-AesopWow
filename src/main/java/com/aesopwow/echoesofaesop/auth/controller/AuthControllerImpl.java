package com.aesopwow.echoesofaesop.auth.controller;

import com.aesopwow.echoesofaesop.auth.dto.LoginRequestDto;
import com.aesopwow.echoesofaesop.auth.dto.SendOtpRequestDto;
import com.aesopwow.echoesofaesop.auth.dto.TokenResponseDto;
import com.aesopwow.echoesofaesop.auth.service.AuthService;
import com.aesopwow.echoesofaesop.auth.dto.SignUpRequestDto;
import com.aesopwow.echoesofaesop.auth.dto.VerificationOtpRequestDto;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @GetMapping("/duplicate/email/{email}")
    @Override
    public ResponseEntity<ResponseDto<Void>> emailDuplicateCheck(
            @PathVariable @NotBlank @Email String email) {
        // true if email is already in use, false otherwise
        boolean result = authService.hasUserByEmail(email);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Email Duplicate Check Success",
                        result,
                        null
                ));
    }

    @GetMapping("/duplicate/nickname/{nickname}")
    @Override
    public ResponseEntity<ResponseDto<Void>> nicknameDuplicateCheck(
            @PathVariable @NotBlank String nickname) {
        // true if nickname is already in use, false otherwise
        boolean result = authService.hasUserByNickname(nickname);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Nickname Duplicate Check Success",
                        result,
                        null
                ));
    }

    @PostMapping("/otp/send")
    @Override
    public ResponseEntity<ResponseDto<Void>> sendOtp(@RequestBody SendOtpRequestDto sendOtpRequestDto) {
        authService.sendOtp(sendOtpRequestDto.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "OTP Send Success",
                                true,
                                null
                        ));
    }

    @PostMapping("/otp/verification")
    @Override
    public ResponseEntity<ResponseDto<Void>> verificationOtp(@RequestBody VerificationOtpRequestDto verificationOtpRequestDto) {
        boolean result = authService.checkOtpValidity(verificationOtpRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                        HttpStatus.CREATED.value(),
                        "OTP Verify Success",
                        result,
                        null
                ));
    }

    @PostMapping("/signup")
    @Override
    public ResponseEntity<ResponseDto<Void>> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        authService.createUser(signUpRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "Sign Up Success",
                                true,
                                null
                        ));

    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<ResponseDto<TokenResponseDto>> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto) {
        TokenResponseDto tokenResponseDto = authService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "Login Success",
                                true,
                                tokenResponseDto
                        ));
    }

    @PostMapping("/logout")
    @Override
    public ResponseEntity<ResponseDto<Void>> logout(@RequestHeader("Authorization") String bearerToken) {
        authService.logout(bearerToken);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Logout Success",
                        true,
                        null
                ));
    }

    @PostMapping("/refresh")
    @Override
    public ResponseEntity<ResponseDto<TokenResponseDto>> refresh(@RequestHeader("Authorization") String bearerToken) {

        TokenResponseDto tokenResponseDto = authService.refresh(bearerToken);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new ResponseDto<>(
                                HttpStatus.OK.value(),
                                "Refresh Success",
                                true,
                                tokenResponseDto
                        ));
    }
}
