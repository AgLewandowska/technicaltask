package com.example.Freezer.search;

import com.example.Freezer.Food;
import com.example.Freezer.FoodModelAssembler;
import com.example.Freezer.FreezerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class SearchController
{
    private final FreezerRepository repository;
    private final FoodModelAssembler assembler;

    SearchController(FreezerRepository repository, FoodModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("food/search")
    public CollectionModel<EntityModel<Food>> searchBy(@RequestBody Search search)
    {
        return CollectionModel.of(search.getMatchingItems(repository.findAll())
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()));
    }
}
