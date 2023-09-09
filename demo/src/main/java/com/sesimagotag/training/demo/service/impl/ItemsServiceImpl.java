package com.sesimagotag.training.demo.service.impl;

import com.sesimagotag.training.demo.entities.Item;
import com.sesimagotag.training.demo.repository.ItemsRepository;
import com.sesimagotag.training.demo.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemsRepository itemsRepository;
    @Inject
    public ItemsServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
    public List<Item> createItems(List<Item> items) {
        return itemsRepository.saveAll(items);
    }

    public Item getItem(String itemId) {
        Optional<Item> opt = itemsRepository.findById(itemId);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }
    }

    public Item reverseNameItem(String itemId) {
        Item item = getItem(itemId);
        item.setName(reverseName(item.getName()));
        return item;
    }

    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }

    public List<Item> getAllItemsWithReverseName() {
        List<Item> allItems = getAllItems();
        for (Item item : allItems) {
            item.setName(reverseName(item.getName()));
        }
        return allItems;
    }


    private String reverseName(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.reverse();

        return stringBuilder.toString();
    }


}
