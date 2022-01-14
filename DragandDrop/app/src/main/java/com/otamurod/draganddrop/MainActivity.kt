package com.otamurod.draganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.draganddrop.adapters.UserAdapter
import com.otamurod.draganddrop.adapters.UserAdapter2
import com.otamurod.draganddrop.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var userList:ArrayList<User>
    lateinit var userAdapter: UserAdapter
    lateinit var userAdapter2: UserAdapter2

    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()
        userAdapter = UserAdapter()
        userAdapter.submitList(userList)


        loadData()
        userAdapter2 = UserAdapter2(this, userList)


//        rv.adapter = userAdapter
        rv.adapter = userAdapter2

        /*add_btn.setOnClickListener {
            val s1 = username.text.toString()
            val s2 = password.text.toString()

            var user = User(count++, s1, s2)
            userList.add(user)

            userAdapter.submitList(userList)
            rv.adapter = userAdapter
        }*/

        val itemTouch = object :ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userAdapter2.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter2.onItemDismiss(viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouch)
        itemTouchHelper.attachToRecyclerView(rv)

    }

    private fun loadData() {
        for (i in 1..30){
            userList.add(User(i, "User $i", "Password $i"))
        }
    }
}