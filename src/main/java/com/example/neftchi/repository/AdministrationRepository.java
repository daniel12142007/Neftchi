package com.example.neftchi.repository;

import com.example.neftchi.dto.response.AdministrationResponse;
import com.example.neftchi.model.Administration;
import com.example.neftchi.model.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AdministrationRepository extends JpaRepository<Administration, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.AdministrationResponse(
            a.id,
            a.fullName,
            a.description,
            a.image,
            a.language
            )
            from Administration a
            where a.language = :language
            or a.language = 'ALL'
            order by a.id
            """)
    List<AdministrationResponse> findAllAdministration(@Param(value = "language") Language language);

    @Query("""
            select
            new com.example.neftchi.dto.response.AdministrationResponse(
            a.id,
            a.fullName,
            a.description,
            a.image,
            a.language
            )
            from Administration a
            where a.id = :id
            """)
    AdministrationResponse findByIdResponse(@Param(value = "id") Long id);
}