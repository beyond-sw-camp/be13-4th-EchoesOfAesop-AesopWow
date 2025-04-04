package com.aesopwow.echoesofaesop.board.free.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class FreeSearchRequestDto {

    @Schema(description = "검색 키워드")
    private String keyword;

    @Schema(description = "검색 유형 (title, content, all)", defaultValue = "all")
    private String searchType = "all";
}
