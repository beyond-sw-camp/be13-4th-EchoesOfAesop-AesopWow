package com.aesopwow.echoesofaesop.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(@Value("1.1.1") String springdocVersion) {
        Info info = new Info()
                .title("AesopWow 프로젝트")
                .description("AesopWow 프로젝트는 고민을 나누고, 비슷한 경험을 가진 사람들로부터 조언과 공감을 얻음으로써 심리적 안정감을 제공.<br>"
                        + "이솝우화가 정치적, 사회적 메시지를 전달했던 것처럼, 익명 게시판은 현대 사회에서 사람들이 자유롭게 의견을 나눌 수 있는 중요한 역할을 수행.<br>"
                        + "이솝우화의 교훈적 특성을 현대적인 방식으로 재해석하여 새로운 형태의 지혜 공유를 실현.<br>"
                        + "각 API는 Swagger UI를 통해 테스트할 수 있습니다.");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes(
                        "bearer-auth",
                        new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                ))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-auth")
                )
                .info(info);
    }
}
