package com.sesimagotag.training.demo.repository;

import com.sesimagotag.training.demo.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, String> {

    @Query("SELECT e FROM Item e ORDER BY e.price, e.name")
    List<Item> findAllOrderByPropriete1AndPropriete2();
    Page<Item> findAll(Pageable pageable);

}
