package com.otamurod.playmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otamurod.playmarket.adapters.PlayMarketAdapter
import com.otamurod.playmarket.models.GeneralData
import com.otamurod.playmarket.models.Program
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var generalList: ArrayList<GeneralData>
    lateinit var playMarketAdapter: PlayMarketAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        playMarketAdapter = PlayMarketAdapter(generalList)
        rv.adapter = playMarketAdapter

    }

    private fun loadData() {
        generalList = ArrayList()
        val program1 = Program("https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Telegram_2019_Logo.svg/640px-Telegram_2019_Logo.svg.png", "Telegram")
        val program2 = Program("https://www.facebook.com/images/fb_icon_325x325.png", "Facebook")
        val program3 = Program("https://play-lh.googleusercontent.com/x3XxTcEYG6hYRZwnWAUfMavRfNNBl8OZweUgZDf2jUJ3qjg2p91Y8MudeXumaQLily0", "Twitter")
        val program4 = Program("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/1200px-Instagram_logo_2016.svg.png", "Instagram")

        generalList.add(GeneralData("Famous", listOf(program1, program2, program3, program4) ))

        val program5 = Program("https://static.wikia.nocookie.net/8ballpool/images/b/b6/8_Ball_Pool_mobile_logo.png/revision/latest?cb=20150627154714", "8 Ball Pool")
        val program6 = Program("https://media.pocketgamer.biz/2017/9/81396/football-strike-2-r225x.jpg", "Football Strike")
        val program7 = Program("https://ludoking.com/images/left.png", "Ludo King")
        val program8 = Program("https://justtravelto.com/wp-content/uploads/2021/05/3D-Chess-2-Player-MOD-Unlimited-Money.png", "Chess 3d Offline")

        generalList.add(GeneralData("Games", listOf(program5, program6, program7, program8) ))

        val program9 = Program("https://archive.org/download/mx-player-logo-450x450/mx-player-logo-450x450.png", "MX Player")
        val program10 = Program("https://image.winudf.com/v2/image1/Y29tLmdhbGxlcnkyMF9pY29uXzE1NTk5MTE3MTBfMDEw/icon.png?w=&fakeurl=1", "AI Gallery")
        val program11 = Program("https://play-lh.googleusercontent.com/jn0j7Sq77Vygl-DCaT8HVCIQW7o3WwMYI8ZAikNxbTOSGyI9uKvC5VRQI-4FEawWzg", "AI Camera")
        val program12 = Program("https://play-lh.googleusercontent.com/y-jJBrUkvzwB2FsovKYjPl1TTIQ8eNl7__w3Waony71rjKqWmy1WxGZK47KuafZygQ", "Xender")

        generalList.add(GeneralData("Recommended", listOf(program9, program10, program11, program12) ))

        val program13 = Program("https://www.bigw.com.au/medias/sys_master/images/images/hce/h7f/14655046516766.jpg", "Atomic Habits")
        val program14 = Program("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1347453416l/1684692.jpg", "The Happiness Hypothesis")
        val program15 = Program("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1311282539i/9897792._UY475_SS475_.jpg", "The Dyslexic Advantage")
        val program16 = Program("https://kbimages1-a.akamaihd.net/800dd933-f1a6-44e3-93d6-adec5ef411d9/1200/1200/False/thinking-fast-and-slow-3.jpg", "Thinking, Fast and Slow")

        generalList.add(GeneralData("Books", listOf(program13, program14, program15, program16) ))
    }
}