package com.example.neftchi.repository;

import com.example.neftchi.dto.response.FunctionResponse;
import com.example.neftchi.model.Function;
import com.example.neftchi.model.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FunctionRepository extends JpaRepository<Function, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.FunctionResponse(
            f.id,
            f.title,
            f.link,
            f.image,
            f.language
            )
            from Function f
            where f.id = :id
            """)
    Optional<FunctionResponse> findByIdFunctionResponse(@Param(value = "id") Long id);

    @Query("""
            select
            new com.example.neftchi.dto.response.FunctionResponse(
            f.id,
            f.title,
            f.link,
            f.image,
            f.language
            )
            from Function f
            where f.language = 'ALL'
            or f.language = :language
            order by f.id
            """)
    List<FunctionResponse> findAllByLanguage(@Param(value = "language") Language language);
}