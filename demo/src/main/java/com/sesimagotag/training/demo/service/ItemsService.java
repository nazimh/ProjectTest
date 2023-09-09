package com.sesimagotag.training.demo.service;

import com.sesimagotag.training.demo.entities.Item;

import java.util.List;

public interface ItemsService {
    List<Item> createItems(List<Item> items);

    Item getItem(String itemId);

    Item reverseNameItem(String itemId);

    List<Item> getAllItems();

    List<Item> getAllItemsWithReverseName();
}
