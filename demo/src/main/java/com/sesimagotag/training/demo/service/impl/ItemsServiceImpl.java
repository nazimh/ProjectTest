package com.sesimagotag.training.demo.service.impl;

import com.sesimagotag.training.demo.entities.Item;
import com.sesimagotag.training.demo.repository.ItemsRepository;
import com.sesimagotag.training.demo.service.ItemsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        allItems.forEach(item -> item.setName(reverseName(item.getName())));
        return allItems;
    }

    public List<Item> itemsSort() {
        return itemsRepository.findAllOrderByPropriete1AndPropriete2();
    }

    public List<Item> getItemsIterate(int page, int pageSize, boolean sort, boolean reverseName) {
        Pageable pageable = sort ? PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "price").and(Sort.by(Sort.Direction.ASC, "name")))
                : PageRequest.of(page - 1, pageSize);
        Page<Item> pageResult = itemsRepository.findAll(pageable);
        List<Item> result = pageResult.getContent();
        return !reverseName ? result :
            result.stream()
            .map(this::reverseName2)
            .collect(Collectors.toList());
    }

    private Item reverseName2(Item item) {
        item.setName(reverseName(item.getName()));
        return item;
    }
    private String reverseName(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }


}
