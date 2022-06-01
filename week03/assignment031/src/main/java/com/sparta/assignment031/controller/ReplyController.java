package com.sparta.assignment031.controller;

import com.sparta.assignment031.domain.Post;
import com.sparta.assignment031.domain.Reply;
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
    @GetMapping("/replies/{postId}")
    public List<Reply> showReply(@PathVariable Long postId) {
        return replyRepository.findAllByPostIdOrderByModifiedAtDesc(postId);
    }

    //댓글 작성
    @PostMapping("/replies/{postId}")
    public Reply writeReply(@PathVariable Long postId, @RequestBody ReplyRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("아이디가 없습니다.")
        );
        //post자체를 넣어야함
        Reply reply = new Reply();
        reply.setPost(post);
        reply.setContents(requestDto.getContents());
        return replyRepository.save(reply);
    }

    //댓글 수정
    @PutMapping("/replies/{replyId}")
    public Long editReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto requestDto) {
        return replyService.editReply(replyId, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/replies/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
