package com.example.Freezer;

import com.example.Freezer.search.Search;
import com.example.Freezer.search.SearchController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FoodModelAssembler implements RepresentationModelAssembler<Food, EntityModel<Food>>
{
    @Override
    public EntityModel<Food> toModel(Food entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(FoodController.class).getByID(entity.getId())).withSelfRel(),
                linkTo(methodOn(FoodController.class).getAll()).withRel("food"),
                linkTo(methodOn(SearchController.class)
                        .searchBy(new Search("type", entity.getType()))).withRel("food/search")
                        .withName("search all items of this type"),
                linkTo(methodOn(SearchController.class)
                        .searchBy(new Search("name", entity.getName()))).withRel("food/search")
                        .withName("search all items with this name"));
    }
}
