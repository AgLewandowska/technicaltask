package com.example.Freezer;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class FoodController
{
    private final FreezerRepository repository;
    private final FoodModelAssembler assembler;

    public FoodController(FreezerRepository repository, FoodModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/food")
    public CollectionModel<EntityModel<Food>> getAll()
    {
        List<EntityModel<Food>> allItems = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(allItems, linkTo(methodOn(FoodController.class).getAll()).withSelfRel());
    }

    @GetMapping("/food/{id}")
    public EntityModel<Food> getByID(@PathVariable Integer id)
    {
        Food foodItem = repository.findById(id).orElseThrow();
        return assembler.toModel(foodItem);
    }

    @PostMapping("/food")
    public EntityModel<Food> addNewItem(@RequestBody Food newItem)
    {
        newItem.setDate(new Date().getTime());
        Food savedItem = repository.save(newItem);
        return assembler.toModel(savedItem);
    }

    @PutMapping("/food/{id}")
    public EntityModel<Food> updateFoodItem(@RequestBody Food updatedItem, @PathVariable Integer id)
    {
        Food item = repository.findById(id).map(i ->
        {
            i.setName(updatedItem.getName());
            i.setType(updatedItem.getType());
            i.setQuantity(updatedItem.getQuantity());
            return repository.save(i);
        }).orElseGet(() ->
        {
            updatedItem.setId(id);
            return repository.save(updatedItem);
        });
        return assembler.toModel(item);
    }

    @DeleteMapping("/food/{id}")
    public void deleteItem(@PathVariable Integer id)
    {
        repository.deleteById(id);
    }
}
