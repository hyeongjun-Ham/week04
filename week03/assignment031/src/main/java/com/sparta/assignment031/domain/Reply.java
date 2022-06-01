package com.sparta.assignment031.domain;

import com.sparta.assignment031.dto.ReplyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Entity
@Setter
@NoArgsConstructor
public class Reply extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn
    private Post post;

    public Reply(Long id,ReplyRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public void editReply(ReplyRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
