package com.example.neftchi.repository;

import com.example.neftchi.dto.response.CategoryResponse;
import com.example.neftchi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.CategoryResponse(
            c.id,
            c.category,
            c.color,
            c.queueNumber
            )
            from Category c
            order by c.queueNumber
            """)
    List<CategoryResponse> findAllResponse();

    @Query("select c from Category c where c.queueNumber = :queueNumber")
    Category findByQueueNumber(@Param(value = "queueNumber") int queueNumber);
}