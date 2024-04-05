package com.example.demo_borntodev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RestController
public class DemoBorntodevApplication {

	private List<Item> itemList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoBorntodevApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name" , defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	// item CRUD
	@GetMapping("/items")
	public List<Item> getAllItems(){
		return itemList;
	}

	@PostMapping("/items")
	public Item addItem(@RequestBody Item item){
		itemList.add(item);
		return item;
	}

	@GetMapping("items/{id}")
	public Item getItemById(@PathVariable Long id) {
		return itemList.stream()
				.filter(item -> item.getId().equals(id))
				.findFirst()
				.orElse(null);
	}



	@PutMapping("items/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem){
		for (Item item : itemList){
			if (item.getId().equals(id)){
				item.setName(updatedItem.getName());
				return item;
			}
		}
		return null;
	}

	@DeleteMapping("/items/{id}")
	public Item deleteItem(@PathVariable Long id){
		Item itemToDelete = null;
		for (Item item : itemList){
			if (item.getId().equals(id)){
				itemToDelete = item;
				break;
			}
		}
		if (itemToDelete != null){
			itemList.remove(itemToDelete);
		}
		return itemToDelete;
	}




}
