package com.sparta.assignment031.dto;

import com.sparta.assignment031.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListDto {

        private Long id;
        private LocalDateTime starDate;
        private LocalDateTime modifiedAt;
        private String title;
        private String name;

        public PostListDto(Post post) {
                this.id = post.getId();
                this.title = post.getTitle();
                this.name = post.getName();
                this.starDate = post.getStarDate();
                this.modifiedAt = post.getModifiedAt();

        }

}
