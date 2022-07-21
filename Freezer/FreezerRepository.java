package com.example.Freezer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FreezerRepository extends JpaRepository<Food, Integer>
{

}
