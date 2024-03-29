package com.footstep.domain.posting.controller;

import com.footstep.domain.base.BaseException;
import com.footstep.domain.base.BaseResponse;
import com.footstep.domain.posting.service.LikeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"좋아요 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/footstep/{posting-id}")
@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "accessToken", required = true, example = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImZvb3RzdGVwQG5hdmVyLmNvbSIsImlhdCI6MTY3NjAwOTY1OSwiZXhwIjoxNjc2MzEyMDU5fQ.VBt8rfM3W7JdH5jMQ7A19-tuZ3OGLBqzmRC8GF2DzGQ")
})
public class LikeController {

    private final LikeService likeService;

    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 2005, message = "로그인이 필요합니다.")
    })
    @PostMapping("/like")
    @ApiResponse(code = 3031, message = "게시글이 존재하지 않습니다")
    @ApiOperation(value = "좋아요 누르기", notes = "해당 게시물에 좋아요를 누르면 1(좋아요)을 반환, 눌려있는 상태에서 좋아요를 누르면 0(좋아요 취소)을 반환")
    public BaseResponse<Long> like(
            @ApiParam(value = "게시물 ID", required = true, example = "3") @PathVariable("posting-id")Long postingId,
            @RequestHeader("Authorization")String accessToken) {
        try {
            Long result = likeService.like(postingId);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/like")
    @ApiResponse(code = 3031, message = "게시글이 존재하지 않습니다.")
    @ApiOperation(value = "좋아요 개수", notes = "해당 게시물에 좋아요 개수 세기")
    public BaseResponse<String> countLike(
            @ApiParam(value = "게시물 ID", required = true, example = "3") @PathVariable("posting-id")Long postingId,
            @RequestHeader("Authorization")String accessToken) {
        try {
            String result = likeService.count(postingId);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}