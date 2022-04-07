package com.otamurod.room

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.room.adapter.CategoryAdapter
import com.otamurod.room.adapter.NewsAdapter
import com.otamurod.room.database.AppDatabase
import com.otamurod.room.databinding.ActivityMainBinding
import com.otamurod.room.databinding.DialogBinding
import com.otamurod.room.entity.Category
import com.otamurod.room.entity.News

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<News>
    lateinit var newsAdapter: NewsAdapter
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryList: ArrayList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        appDatabase = AppDatabase.getInstance(this)

        val category1 = Category("Science")
        val category2 = Category("Technology")
        appDatabase.categoryDao().insertAllCategory(category1, category2)

        list = ArrayList()
        list.addAll(appDatabase.newsDao().getAllNews())

        categoryList = ArrayList()
        categoryList.addAll(appDatabase.categoryDao().getAllCategories())

        categoryAdapter = CategoryAdapter(categoryList)
        activityMainBinding.spinner.adapter = categoryAdapter

        newsAdapter = NewsAdapter(list, object : NewsAdapter.OnItemClickListener {
            override fun onItemDelete(news: News, position: Int) {
                appDatabase.newsDao().deleteNews(news)
                list.remove(news)
                newsAdapter.notifyItemRemoved(position)
                newsAdapter.notifyItemRangeChanged(position, list.size)
            }

            override fun onItemEdit(news: News, position: Int) {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                val dialogBinding = DialogBinding.inflate(layoutInflater)
                dialogBinding.editTitle.setText(news.title)
                dialogBinding.editInfo.setText(news.info)
                alertDialog.setView(dialogBinding.root)

                alertDialog.setPositiveButton("Update", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        val title = dialogBinding.editTitle.text.toString()
                        val info = dialogBinding.editInfo.text.toString()

                        news.title = title
                        news.info = info

                        appDatabase.newsDao().updateNews(news)
                        newsAdapter.notifyItemChanged(position)
                    }
                })

                alertDialog.show()
            }
        })

        activityMainBinding.rv.adapter = newsAdapter

        activityMainBinding.addBtn.setOnClickListener {
            val title = activityMainBinding.titleEt.text.toString()
            val info = activityMainBinding.infoEt.text.toString()

            val selectedItemPosition = activityMainBinding.spinner.selectedItemPosition
            val category = categoryList[selectedItemPosition]
            val news = News(title, info)
            news.categoryId = category.id

            appDatabase.newsDao().addNews(news)
            val newsId = appDatabase.newsDao().getNewsById(title, info)
            news.id = newsId
            list.add(news)

            newsAdapter.notifyItemInserted(list.size)
        }

    }
}