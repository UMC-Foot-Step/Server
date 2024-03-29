package com.footstep.domain.users.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageInfo {

    @ApiModelProperty(example = "하마")
    private String nickname;

    @ApiModelProperty(example = "5")
    private long postingCount;

    @ApiModelProperty(example = "url")
    private String profileImageUrl;
}
