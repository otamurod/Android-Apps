package com.otamurod.milliytaomlar.db

import com.otamurod.milliytaomlar.models.Food

interface DatabaseService {

    fun addFood(food: Food)

    fun updateFood(food: Food):Int

    fun deleteFood(food: Food)

    fun getFoodById(id: Int): Food

    fun getAllFoods(): List<Food>

}