package com.sparta.assignment031.repository;

import com.sparta.assignment031.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByPostIdOrderByModifiedAtDesc(Long postId);


}
