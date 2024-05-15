package com.example.neftchi.repository;

import com.example.neftchi.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {
}