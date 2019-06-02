package com.rajat.ecommerce.controller;

import com.rajat.ecommerce.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    /* 1. CRUD operations on items */
    @PostMapping("/items")
    public Item createItem(@Valid @RequestBody Item item) {
        System.out.println(item);
        return itemRepository.save(item);
    }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable(value = "id") Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable(value = "id") Long itemId, @Valid @RequestBody Item itemDetails) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

        item.setName(itemDetails.getName());
        item.setQuantity(itemDetails.getQuantity());
        item.setCost(itemDetails.getCost());
        item.setItemCategory(itemDetails.getItemCategory());

        Item updatedItem = itemRepository.save(item);
        return updatedItem;
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));
        itemRepository.delete(item);
        return ResponseEntity.ok().build();
    }

    /* 2. All Items Listing */
    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /* 3. Single & bulk ordering*/
    @PostMapping("/orders")
    public ResponseEntity<?> createOrders(@Valid @RequestBody Orders orders) throws IOException {

        List<String> errorMessages = new ArrayList<>();
        for (Order order: orders.getOrders()) {
            Item orderedItem = itemRepository.findById(order.getItemId()).get();
            if (order.getNumberOfItemsOrdered() > orderedItem.getQuantity()) {
                errorMessages.add("Item " + order.getItemId() + " is out of stock. There are only " + orderedItem.getQuantity() +
                        " items in the stock. But you ordered " + order.getNumberOfItemsOrdered());
            } else {
                orderedItem.setQuantity(orderedItem.getQuantity() - order.getNumberOfItemsOrdered());
                itemRepository.save(orderedItem);
                orderRepository.save(order);
            }
        }
        return ResponseEntity.ok(errorMessages);
    }

    /* 4. All orders */
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
