package com.sesimagotag.training.demo;

import java.util.*;


import com.sesimagotag.training.demo.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sesimagotag.training.demo.entities.Item;

import javax.inject.Inject;

@org.springframework.web.bind.annotation.RestController
public class RestControllerForItems {

    private final Map<String, Item> items = Collections.synchronizedMap(new HashMap<>());

    private final ItemsService itemsService;

    @Inject
    public RestControllerForItems(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    /**
     * Create all items given in parameters and overwrite existing one.
     * 
     * @return count of new items added
     */
    @PostMapping(value = "api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createItems(@RequestBody final List<Item> newItems) {
        List<Item> itemsCreated = itemsService.createItems(newItems);
        return new ResponseEntity<>(itemsCreated, HttpStatus.OK);
    }

    /**
     * @return return item with corresponding itemId
     */
    @GetMapping(value = "api/v1/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItem(@PathVariable final String itemId) {
        Item returnedItem = itemsService.getItem(itemId);
        return new ResponseEntity<>(returnedItem, HttpStatus.OK);
    }

    /**
     * @return return all items with reverse name
     */
    @GetMapping(value = "api/v1/items/reverse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllItemsWithReverseName() {
        List<Item> returnedItems = itemsService.getAllItemsWithReverseName();
        return new ResponseEntity<>(returnedItems, HttpStatus.OK);
    }
    /**
     * Do not modify existing item list on the reverse operation.
     * 
     * @return return item with corresponding itemId and reverse its name in the
     *         result.
     */
    public ResponseEntity<Object> getItemReverse(@RequestParam final int itemId) {
        return null;
    }

    /**
     * Do not modify existing item list on the reverse operation.
     * 
     * @return all items with reverse name
     */
    @GetMapping(value = "api/v1/items/{itemId}/reverse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItemsReverse(@PathVariable final String itemId) {
        Item item = itemsService.reverseNameItem(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /**
     * @return all items sorted by prices asc and names asc
     */
    @GetMapping(value = "api/v1/items/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItemsSort() {
        return null;
    }

    /**
     * @return all items
     */
    @GetMapping(value = "api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Object> getItems() {
        List<Item> returnedItems = itemsService.getAllItems();
        return new ResponseEntity<>(returnedItems, HttpStatus.OK);
    }

    /**
     * <p>
     * page=1 and pageSize=5, return 0->4 elements
     * </p>
     * <p>
     * page=2 and pageSize=5, return 5->9 elements
     * </p>
     * <p>
     * page=2 and pageSize=10, return 10->19 elements
     * </p>
     * 
     * @param page
     *                    first page, start at 1
     * @param pageSize
     *                    elements returned in a page
     * @param sort
     *                    sort by prices asc and names asc
     * @param reverseName
     *                    reverse names (after sorting)
     * @return items
     */
    @GetMapping(value = "api/v1/items/iterate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItemsIterate(@RequestParam final int page, @RequestParam final int pageSize,
            @RequestParam final boolean sort, @RequestParam final boolean reverseName) {
        int currentIndex = sort?  page * pageSize*page*pageSize: (int) (reverseName ? 48 : Math.pow(5, 2) * Math.PI);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
