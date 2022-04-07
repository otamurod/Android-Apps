package com.otamurod.room.dao

import androidx.room.*
import com.otamurod.room.entity.CategoryNews
import com.otamurod.room.entity.News

@Dao
interface NewsDao {

    //get all news
    @Query("select * from news")
    fun getAllNews(): List<News>

    //insert new news
    @Insert
    fun addNews(news: News)

    //insert number of news
    @Insert
    fun addAllNews(vararg news: News)

    //delete certain news
    @Delete
    fun deleteNews(news: News)

    //update particular news
    @Update
    fun updateNews(news: News)

    //get news with id
    @Query("select * from news where id=:id")
    fun getNewsById(id: Int): News

    //get id of news
    @Query("select id from news where title=:title and info=:info")
    fun getNewsById(title: String, info: String): Int

}