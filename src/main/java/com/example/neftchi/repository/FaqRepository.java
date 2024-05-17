package com.example.neftchi.repository;

import com.example.neftchi.dto.response.FaqResponse;
import com.example.neftchi.model.FAQ;
import com.example.neftchi.model.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FaqRepository extends JpaRepository<FAQ, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.FaqResponse(
            f.id,
            f.question,
            f.answer,
            f.fileName,
            f.language
            )
            from FAQ f
            where f.id = :id
            """)
    FaqResponse findByIdFaqResponse(@Param(value = "id") Long id);

    @Query("""
            select
            new com.example.neftchi.dto.response.FaqResponse(
            f.id,
            f.question,
            f.answer,
            f.fileName,
            f.language
            )
            from FAQ f
            where f.language = 'ALL'
            or f.language = :language
            order by f.id
            """)
    List<FaqResponse> findAllFaqResponse(@Param(value = "language") Language language);
}