package com.example.neftchi.repository;

import com.example.neftchi.dto.response.MiniPartnersResponse;
import com.example.neftchi.model.MiniPartners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MiniPartnersRepository extends JpaRepository<MiniPartners, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.MiniPartnersResponse(
            m.id,
            m.title,
            m.pdf
            )
            from MiniPartners m
            where m.id = :id
            """)
    Optional<MiniPartnersResponse> findByIdMiniPartnersResponse(@Param(value = "id") Long id);
    @Query("""
            select
            new com.example.neftchi.dto.response.MiniPartnersResponse(
            m.id,
            m.title,
            m.pdf
            )
            from MiniPartners m
            order by m.id
            """)
    List<MiniPartnersResponse>findAllMiniPartnersResponse();
}