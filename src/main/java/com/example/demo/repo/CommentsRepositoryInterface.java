package com.example.demo.repo;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepositoryInterface extends JpaRepository<Comment, Long> {
}