package com.sparta.assignment031.service;

import com.sparta.assignment031.domain.Reply;
import com.sparta.assignment031.dto.ReplyRequestDto;
import com.sparta.assignment031.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;


    public Long editReply(Long id, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        reply.editReply(requestDto);
        return id;
    }

}
