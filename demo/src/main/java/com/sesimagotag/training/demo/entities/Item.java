package com.sesimagotag.training.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item implements Serializable {
    @Id
    private String id;
    private BigDecimal price;
    private String name;

    public Item(Item item) {
        this.id = item.id;
        this.price = new BigDecimal(item.price.toString());
        this.name = item.name;
    }
}
