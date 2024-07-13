package com.example.neftchi.repository;

import com.example.neftchi.dto.response.AppealResponseAll;
import com.example.neftchi.dto.response.AppealResponseOne;
import com.example.neftchi.model.Appeal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AppealRepository extends JpaRepository<Appeal, Long> {
    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.id
            """)
    List<AppealResponseAll> findBySomeFieldWithPageable(Pageable pageable);

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.id
            """)
    List<AppealResponseAll> findAllAppealResponseAll();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.fullName
            """)
    List<AppealResponseAll> findAllSortNameAsc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.fullName desc
            """)
    List<AppealResponseAll> findAllSortNameDesc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.email
            """)
    List<AppealResponseAll> findAllSortEmailAsc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.email desc
            """)
    List<AppealResponseAll> findAllSortEmailDesc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.phoneNumber
            """)
    List<AppealResponseAll> findAllSortNumberAsc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.phoneNumber desc
            """)
    List<AppealResponseAll> findAllSortNumberDesc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.status
            """)
    List<AppealResponseAll> findAllSortStatusAsc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseAll(
            a.id,
            a.fullName,
            a.email,
            a.phoneNumber,
            a.status
            )
            from Appeal a
            order by a.status desc
            """)
    List<AppealResponseAll> findAllSortStatusDesc();

    @Query("""
            select
            new com.example.neftchi.dto.response.AppealResponseOne(
            a.id,
            a.fullName,
            a.briefComplaint,
            a.description,
            a.phoneNumber,
            a.email,
            a.status,
            a.fileName,
            coalesce(case when a.status='ANSWERED' then a.appealAnswer else 'null' end, 'null'),
             coalesce(case when a.status='ANSWERED' then true else false end, false)
            )
            from Appeal a
            where a.id = :id
            """)
    AppealResponseOne findByIdAppealResponseOne(@Param(value = "id") Long id);
}