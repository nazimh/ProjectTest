package com.sesimagotag.training.demo.repository;

import com.sesimagotag.training.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, String> {

}
