package com.sparta.assignment031.controller;

import com.sparta.assignment031.domain.Post;
import com.sparta.assignment031.domain.Reply;
import com.sparta.assignment031.dto.PostRequestDto;
import com.sparta.assignment031.dto.ReplyRequestDto;
import com.sparta.assignment031.repository.PostRepository;
import com.sparta.assignment031.repository.ReplyRepository;
import com.sparta.assignment031.repository.UserRepository;
import com.sparta.assignment031.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //댓글 목록조회
    @GetMapping("/api/posts/{id}/replies")
    public List<Reply> showReply(@PathVariable Long id) {
        return replyRepository.findAllByPostIdOrderByModifiedAtDesc(id);
    }

    //댓글 작성
    @PostMapping("/api/posts/{id}/replies")
    public Reply writeReply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 없습니다.")
        );
        //post자체를 넣어야함
        Reply reply = new Reply();
        reply.setPost(post);
        reply.setContents(requestDto.getContents());
        return replyRepository.save(reply);
    }

    //댓글 수정
    @PutMapping("/api/posts/replies/{id}")
    public Long editReply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto) {
        return replyService.editReply(id, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/posts/replies/{id}")
    public void deleteReply(@PathVariable Long id) {
        replyRepository.deleteById(id);
    }
}
