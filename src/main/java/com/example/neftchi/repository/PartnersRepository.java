package com.example.neftchi.repository;

import com.example.neftchi.dto.response.PartnersResponse;
import com.example.neftchi.model.Partners;
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
public interface PartnersRepository extends JpaRepository<Partners, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.PartnersResponse(
            p.id,
            p.title,
            p.category,
            p.link,
            p.image,
            p.language
            )
            from Partners p
            where p.language = 'ALL'
            or p.language = :language
            order by p.id
            """)
    List<PartnersResponse> findAllPartnersResponse(@Param(value = "language") Language language);

    @Query("""
            select
            new com.example.neftchi.dto.response.PartnersResponse(
            p.id,
            p.title,
            p.category,
            p.link,
            p.image,
            p.language
            )
            from Partners p
            where p.id = :id
            """)
    PartnersResponse findByIdPartnersResponse(@Param(value = "id") Long id);
}