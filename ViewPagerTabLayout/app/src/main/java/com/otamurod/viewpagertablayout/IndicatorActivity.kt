package com.otamurod.viewpagertablayout

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.viewpagertablayout.adapters.IndicatorAdapter
import kotlinx.android.synthetic.main.activity_indicator.*
import kotlinx.android.synthetic.main.activity_main.*

class IndicatorActivity : AppCompatActivity() {

    lateinit var list: ArrayList<String>
    lateinit var indicatorAdapter: IndicatorAdapter
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator)
        list = ArrayList()

        loadImages()

        indicatorAdapter = IndicatorAdapter(list, supportFragmentManager)
        v_pager.adapter = indicatorAdapter
        tablayout.setupWithViewPager(v_pager)

        handler = Handler()
        handler.postDelayed(runnable, 6000)

    }

    var runnable = object : Runnable {
        override fun run() {
            v_pager.currentItem += 1
            handler.postDelayed(this, 6000)
        }

    }

    private fun loadImages() {
        //new
        list.add("https://images.unsplash.com/photo-1465189684280-6a8fa9b19a7a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTh8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1508669232496-137b159c1cdb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NjJ8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://i.pinimg.com/originals/a2/5d/53/a25d5395731c6fa114491a9bb1fca7e3.jpg")
        list.add("https://images.unsplash.com/photo-1527489377706-5bf97e608852?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NjN8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://www.teahub.io/photos/full/0-2653_wonderful-new-wallpaper-nature-image-for-facebook-dp.jpg")
        list.add("https://i.pinimg.com/originals/8e/fa/a7/8efaa79ef5037e950f4bcd6dd2783e36.jpg")
        list.add("https://images.unsplash.com/photo-1601786776487-5530c3a6287a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8d29uZGVyZnVsfGVufDB8fDB8fA%3D%3D&w=1000&q=80")
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWqAUThq6LKb0cl6950ONDNTW8Ac64xLVqtgVKwHWsnI6FVn71cKO_BXFDypIWCD8k7nc&usqp=CAU")
        list.add("https://media.istockphoto.com/photos/pura-ulun-danu-bratan-temple-in-bali-picture-id675172642?b=1&k=20&m=675172642&s=170667a&w=0&h=IgKlfRYs9bs6bq3fx0WOCqdgWY3WW2-_2PKBMCcREj0=")
        list.add("https://static.vecteezy.com/system/resources/previews/004/272/784/non_2x/glowing-magic-christmas-tree-blue-twinkling-wonderful-lights-merry-christmas-and-happy-new-year-2022-illustration-free-vector.jpg")
        list.add("https://wallpapercave.com/wp/NuVW27r.jpg")
        list.add("https://images.unsplash.com/photo-1593291619431-271d4391ded1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8d29uZGVyZnVsfGVufDB8fDB8fA%3D%3D&w=1000&q=80")
        list.add("https://www.freewalldownload.com/winter-wallpaper/natural-create-a-stachu-wallpaper-hp-picture-download-for-free.jpg")
        list.add("https://us.123rf.com/450wm/leonidtit/leonidtit1903/leonidtit190300168/118771642-the-magic-rhododendron-blossoms-in-springtime-location-carpathian-national-park-ukraine-europe-great.jpg?ver=6")
        list.add("https://st2.depositphotos.com/3651191/7410/i/600/depositphotos_74106203-stock-photo-blossom-carpet-of-pink-rhododendron.jpg")
        //animals
        list.add("https://media.istockphoto.com/photos/two-rothschilds-giraffes-in-northern-uganda-picture-id1307291479?b=1&k=20&m=1307291479&s=170667a&w=0&h=SWKMKkWhu1CS7l55vWgMPZYanqjdwAia6puS8YSCpEo=")
        list.add("https://media.istockphoto.com/photos/point-reyes-elk-herd-leader-picture-id1334720521?b=1&k=20&m=1334720521&s=170667a&w=0&h=GXICUb1jQz504kmhE4NXn_JF3Idmr1HK5vub6-Ww5Ak=")
        list.add("https://media.istockphoto.com/photos/tiger-cub-with-mother-picture-id1339454339?b=1&k=20&m=1339454339&s=170667a&w=0&h=18T4cgJAMQvbd8HlbFOqvpkZNo8dCRyNBdEhio5DfV4=")
        list.add("https://images.unsplash.com/photo-1592670130429-fa412d400f50?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8d2lsZCUyMGFuaW1hbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1598755257130-c2aaca1f061c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8d2lsZCUyMGFuaW1hbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1611173533528-79899600af0c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8d2lsZCUyMGFuaW1hbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1534759846116-5799c33ce22a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8d2lsZCUyMGFuaW1hbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://media.istockphoto.com/photos/red-fox-in-a-winter-setting-picture-id871800682?b=1&k=20&m=871800682&s=170667a&w=0&h=_YSaW2AE-fJt0bq5N2JwpTbUFRaT29rGXZdCWl7OclE=")
        list.add("https://images.unsplash.com/photo-1578956919791-af7615c94b90?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1470619549108-b85c56fe5be8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjl8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1567633090480-f19f2f67c088?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjR8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1623692838101-34077a51e7a1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzJ8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1488217820102-3ef7cfe8bb4e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzh8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1626354132377-fa994612a006?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzl8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1600158362217-37e9db30b17a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fHdpbGQlMjBhbmltYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        //technology
        list.add("https://images.unsplash.com/photo-1531297484001-80022131f5a1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1120&q=80")
        list.add("https://images.unsplash.com/photo-1530893609608-32a9af3aa95c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDF8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1535303311164-664fc9ec6532?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjd8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1504384764586-bb4cdc1707b0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzN8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1611162617213-7d7a39e9b1d7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzl8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1504610926078-a1611febcad3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDF8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1483000805330-4eaf0a0d82da?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDl8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1592659762303-90081d34b277?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTV8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1473968512647-3e447244af8f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NzF8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1593642634315-48f5414c3ad9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NzR8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1451187863213-d1bcbaae3fa3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OTF8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1529310399831-ed472b81d589?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTAwfHx0ZWNobm9sb2d5fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1487875961445-47a00398c267?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTAyfHx0ZWNobm9sb2d5fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1506399558188-acca6f8cbf41?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTEzfHx0ZWNobm9sb2d5fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        //nature
        list.add("https://images.unsplash.com/photo-1433086966358-54859d0ed716?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8bmF0dXJlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1501854140801-50d01698950b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8bmF0dXJlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1441974231531-c6227db76b6e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8bmF0dXJlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1540206395-68808572332f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bmF0dXJlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1472214103451-9374bd1c798e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1446329813274-7c9036bd9a1f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzN8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://media.istockphoto.com/photos/hot-air-balloons-flying-over-the-botan-canyon-in-turkey-picture-id1297349747?b=1&k=20&m=1297349747&s=170667a&w=0&h=oH31fJty_4xWl_JQ4OIQWZKP8C6ji9Mz7L4XmEnbqRU=")
        list.add("https://images.unsplash.com/photo-1505765050516-f72dcac9c60e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjl8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1431794062232-2a99a5431c6c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzJ8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1552083375-1447ce886485?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzh8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1470770903676-69b98201ea1c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzV8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1473773508845-188df298d2d1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzd8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1547036967-23d11aacaee0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDB8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1518173946687-a4c8892bbd9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDZ8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        list.add("https://images.unsplash.com/photo-1572099606223-6e29045d7de3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTF8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.category -> {
                val intent = Intent("category")
                startActivity(intent)
                finish()
                true
            }
            R.id.indicator -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}