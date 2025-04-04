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
    public OpenAPI openAPI(@Value("${springdoc.swagger-ui.version}") String springdocVersion) {
        Info info = new Info()
                .title("허겁직업 프로젝트")
                .version(springdocVersion)
                .description("허겁직업 프로젝트의 API는 사용자 프로필 관리 및 멘토링 기능을 제공합니다.<br>"
                        + "주요 기능으로는 사용자 등록, 프로필 조회 및 수정, 멘토와 멘티 매칭 등이 포함됩니다.<br>"
                        + "인증된 사용자만 접근할 수 있으며, API 요청에 대한 응답으로 JSON 형식의 데이터를 반환합니다.<br>"
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