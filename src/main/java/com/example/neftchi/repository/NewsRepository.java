package com.example.neftchi.repository;

import com.example.neftchi.dto.response.NewsAllResponse;
import com.example.neftchi.dto.response.NewsOneResponse;
import com.example.neftchi.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("""
            select new com.example.neftchi.dto.response.NewsAllResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            coalesce(case when n.pdf is null then false else true end,false),
            coalesce(case when n.video is null then false else true end,false),
            coalesce(case when n.youtubeLink is null then false else true end,false),
            n.category.category
            )
            from News n
            order by n.id
            """)
    List<NewsAllResponse> findAllNewsAllResponse();

    @Query("""
            select new com.example.neftchi.dto.response.NewsAllResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            coalesce(case when n.pdf is null then false else true end,false),
            coalesce(case when n.video is null then false else true end,false),
            coalesce(case when n.youtubeLink is null then false else true end,false),
            n.category.category
            )
            from News n
            order by n.dataCreated
            """)
    List<NewsAllResponse> findAllResponseSortDataAsc();

    @Query("""
            select new com.example.neftchi.dto.response.NewsAllResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            coalesce(case when n.pdf is null then false else true end,false),
            coalesce(case when n.video is null then false else true end,false),
            coalesce(case when n.youtubeLink is null then false else true end,false),
            n.category.category
            )
            from News n
            order by n.dataCreated desc
            """)
    List<NewsAllResponse> findAllResponseSortDataDesc();

    @Query("""
            select new com.example.neftchi.dto.response.NewsAllResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            coalesce(case when n.pdf is null then false else true end,false),
            coalesce(case when n.video is null then false else true end,false),
            coalesce(case when n.youtubeLink is null then false else true end,false),
            n.category.category
            )
            from News n
            order by n.category.queueNumber
            """)
    List<NewsAllResponse> findAllResponseSortCategoryAsc();

    @Query("""
            select new com.example.neftchi.dto.response.NewsAllResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            coalesce(case when n.pdf is null then false else true end,false),
            coalesce(case when n.video is null then false else true end,false),
            coalesce(case when n.youtubeLink is null then false else true end,false),
            n.category.category
            )
            from News n
            order by n.category.queueNumber desc
            """)
    List<NewsAllResponse> findAllResponseSortCategoryDesc();

    @Query("""
            select new com.example.neftchi.dto.response.NewsAllResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            coalesce(case when n.pdf is null then false else true end,false),
            coalesce(case when n.video is null then false else true end,false),
            coalesce(case when n.youtubeLink is null then false else true end,false),
            n.category.category
            )
            from News n
            where n.category.category = :category
            order by n.id
            """)
    List<NewsAllResponse> findAllResponseByCategory(@Param(value = "category") String category);

    @Query("""
            select new com.example.neftchi.dto.response.NewsOneResponse(
            n.id,
            n.headerImage,
            n.title,
            n.description,
            n.dataCreated,
            n.pdf,
            n.video,
            n.youtubeLink,
            n.category.category
            )
            from News n
            where n.id = :newsId
            """)
    NewsOneResponse findByIdResponse(@Param(value = "newsId") Long newsId);

}