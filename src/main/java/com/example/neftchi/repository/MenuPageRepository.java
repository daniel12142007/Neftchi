package com.example.neftchi.repository;

import com.example.neftchi.model.MenuPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MenuPageRepository extends JpaRepository<MenuPage, Long> {
    @Query("select m from MenuPage m order by m.id desc")
    MenuPage find();
}