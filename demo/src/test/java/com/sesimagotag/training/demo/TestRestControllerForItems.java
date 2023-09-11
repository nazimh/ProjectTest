package com.sesimagotag.training.demo;

import com.sesimagotag.training.demo.service.impl.ItemsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sesimagotag.training.demo.entities.Item;
import com.sesimagotag.training.demo.repository.ItemsRepository;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


import static org.mockito.Mockito.*;


// j'ai pas effectué les test mock pour toutes les questions, faute de temps.
public class TestRestControllerForItems {

    @InjectMocks
    private RestControllerForItems itemController;

    @Mock
    private ItemsServiceImpl itemsService;

    @Mock
    private ItemsRepository itemsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItemReverse() {
        String itemId = "aaa1";
        Item item = new Item(itemId, new BigDecimal(4.43),"5234");
        Item itemRepo = new Item(itemId, new BigDecimal(4.43),"4325");
        when(itemsService.reverseNameItem(itemId)).thenReturn(item);
        when(itemsRepository.findById(itemId)).thenReturn(Optional.of(itemRepo));
        ResponseEntity<Object> response = itemController.getItemsReverse(itemId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
        verify(itemsService, times(1)).reverseNameItem(itemId);
    }
}
