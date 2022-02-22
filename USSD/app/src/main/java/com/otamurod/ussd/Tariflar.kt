package com.otamurod.ussd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.otamurod.ussd.adapters.ListAdapter
import com.otamurod.ussd.models.Packet
import com.otamurod.ussd.models.PacketInfo
import kotlinx.android.synthetic.main.fragment_tariflar.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Tariflar.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tariflar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root:View
    var list = ArrayList<Packet>()
    var listInfo = ArrayList<PacketInfo>()

    lateinit var listAdapter: ListAdapter
    var count = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_tariflar, container, false)

        if (count == 1){
            loadData()
            count++
        }

        listAdapter = ListAdapter(root.context, list)
        root.list.adapter = listAdapter

        root.list.setOnItemClickListener { parent, view, position, id ->

            val bundle = bundleOf("info" to listInfo, "position" to position)

            root.findNavController().navigate(R.id.tariflarInfo, bundle)
        }

        return root
    }

    private fun loadData() {
        list.add(Packet("Milliy 10", "*111*1*11*1#", "Oylik: 10 000 so'm\nDaqiqa: 10 MIN\nInternet: 10 MB"))
        listInfo.add(
            PacketInfo("Milliy 10", "O‘zbekiston bo‘yicha daqiqalar/SMS limiti.\n" +
                "TR doirasida kuniga bittadan 200 tagacha SMS narxi – 10 so‘m, kuniga 200 tadan ortiq SMS narxi - 84 so‘m.\n" +
                "Ushbu TRga ulanish va boshka TRlariga o‘tish UZTELECOM sotuv va xizmat ko‘rsatish ofislarida hamda dilerlik xizmat ko‘rsatish joylarida amalga oshirilishi mumkin.\n" +
                "Abonent to‘lovi (keyingi o‘rinlarda - AT) hisoblanishi abonent “faol” holatda bo‘lganida amalga oshiriladi.  Agar abonent hisob raqamida pul mablag‘i yetarli bo‘lmaganligi sababli bloklangan bo‘lsa, AT to‘lov kiritilmagunga qadar hisoblanmaydi.\n" +
                "Ushbu TR doirasida AT hisoblanishining boshlanishi yagona jadval bo‘yicha emas, balki har bir abonent uchun alohida hisob-kitob davri bo‘yicha aniq soniyaga qadar amalga oshiriladi. TR doirasida ATni yechib olish vaqti aniq belgilanmagan bo‘lib, xizmatlardan foydalanish imkoniyati taqdim etilgan vaqtga bog‘liq bo‘ladi.\n" +
                "ATning hisob-kitob davri kalendar oyining 1-sanasiga bog‘lanmagan holda 1 (bir) oyni tashkil etadi va abonentga xizmatdan foydalanish imkoniyati taqdim etilgan sanadan boshlab hisoblanadi. Hisob-kitob sanasi abonentning hisob-kitob davri uchun ATni hisoblashdagi boshlang‘ich nuqtasi hisoblanadi. Misol uchun, abonent TRga\n" +
                "10-sanada ulangan bo‘lsa, u holda navbatdagi oylik AT keyingi oyning 10-sanasida abonentning shaxsiy hisob raqamida TR bo‘yicha AT uchun yetarli mablag‘ bo‘lgandagina yechib olinadi. Agarda abonent 31 avgust kuni ulangan bo‘lsa, u holda navbatdagi ATni yechib olishga urinish 30 sentyabr kuni amalga oshiriladi.\n" +
                "Limitlar TR bo‘yicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob davri tugagunga qadar amal qiladi, TR bo‘yicha foydalanilmay qoldirilgan limitlar keyingi hisob-kitob davriga o‘tkazilmaydi.\n" +
                "Xizmatlar limiti va AT masshtablashtirilmaydi.\n" +
                "Misol uchun: Abonent “O‘zMobayl” tarmog‘iga 30 yanvar kuni ulanganda, AT yechib olinishi 28 yoki 29 fevral kuni (kalendar yiliga ko‘ra) amalga oshiriladi va hisob-kitob davri yakunida Abonent hisobida AT uchun yetarli mablag‘ bo‘lmasa, abonent raqami bloklanadi. Abonent tomonidan balans 5 yoki 10 kundan keyin to‘ldirilsa, abonent hisobidan AT to‘liq hajmda yechib olinadi, TR doirasidagi xizmatning barcha limitlari keyingi oyda AT yechib olingunga qadar bir oy muddatga to‘liq hajmda taqdim etiladi, bunda har oylik ATni yechib olish sanasi keyingi muvaffaqiyatli AT yechib olinadigan sanaga suriladi.\n" +
                "Amaldagi TR “Milliy” TRlariga o‘zgartirilganda, agarda “raqamni ijaraga berish” xizmati yoqilgan bo‘lsa, xizmat uchun hisob-kitob davri yangi TR bo‘yicha keyingi davrdan boshlab abonent to‘lovini hisoblash davri bilan tenglashtiriladi.\n" +
                "Barcha qo‘ng‘iroqlar  so‘zlashuvning birinchi soniyasidan boshlab narxlanadi va to‘liq daqiqa sifatida yaxlitlanib, hisobga olinadi.\n" +
                "Ushbu tarif rejasida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Qabul qilinmagan qo‘ng‘iroq/Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Sevimli raqamlar”, “Restart”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalarlar, MB va SMS paketlari.\n" +
                "To‘plamdagi barcha tarif rejalarida quyidagi xizmatlar mavjud: “Qabul qilinmagan qo‘ng‘iroq / Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Restart”.\n" +
                "“Milliy 15”, “Milliy 10” tarif rejalarida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Sevimli raqamlar”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalar, MB va SMS paketlari.\n" +
                "“Milliy 40”, “Milliy 50”, “Milliy 70”, “Milliy 100” tarif rejalarida quyidagi xizmatlar mavjud: “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "“Milliy” to‘plamidagi TRga quyidagi tarif rejalaridan o‘tish mumkin: “Ishbilarmon”, “Flash”, “OnLime Street Upgrade”, “Royal”, “Flash Upgrade”, “Aloqachi”, “Yoshlar”, “Oddiy 10”, “Street”, “Traffic”, “Ezoz”, “Constructor”, “Step”, “C 1”, “C 2”, “C 3”, “C 4”, “Salom”, “Salom plus”, “Komfort”, “UzMobile 1200”, “UzMobile 4000”, “UzMobile VIP”, “UNITS”, ”Bolajon”, “TA‘LIM B2C”,  “MAKTAB” va “Taʼlim”.\n" +
                "“Milliy” TRdan quyidagi tarif rejalariga o‘tish mumkin: “UNITS”, “Aloqachi”, “C1”, “C2”, “C3”, “C4”, “TA‘LIM B2C” va  “MAKTAB”.\n" +
                "TR to‘plami ichida boshqa tarif rejalariga  o‘tish 4 200 so‘mga  amalga oshiriladi.\n" +
                "“Delovoy”, “Flash”, “OnLime”, «Street Upgrade», “Royal”, «Flash Upgrade», “Aloqachi”, “Yoshlar”, «Prosto 10», “Street”, «C 1», «C 2», «C 3», «C 4»,  “UNITS”, “Bolajon”, «TA‘LIM B2C», “MAKTAB” tarif rejalaridan  “Milliy” TRga o‘tish 4 200 so‘mga amalga oshiriladi.\n" +
                "“Traffic”, “Ezoz”, “Constructor”, “Step”, “Salom”, «Salom plus», “Komfort”, «UzMobile 1200», «UzMobile 4000», «UzMobile VIP», “Taʼlim” tarif rejalaridan  “Milliy” TRga o‘tish bepul amalga oshiriladi.\n\n")
        )

        list.add(Packet("Milliy 15", "*111*1*11*2#", "Oylik: 15 000 so'm\nDaqiqa: 100 MIN\nInternet: 500 MB"))
        listInfo.add(
            PacketInfo("Milliy 15", "O‘zbekiston bo‘yicha daqiqalar/SMS limiti.\n" +
                "TR doirasida kuniga bittadan 200 tagacha SMS narxi – 5 so‘m, kuniga 200 tadan ortiq SMS narxi - 84 so‘m.\n" +
                "Ushbu TRga ulanish va boshka TRlariga o‘tish UZTELECOM sotuv va xizmat ko‘rsatish ofislarida hamda dilerlik xizmat ko‘rsatish joylarida amalga oshirilishi mumkin.\n" +
                "Abonent to‘lovi (keyingi o‘rinlarda - AT) hisoblanishi abonent “faol” holatda bo‘lganida amalga oshiriladi.  Agar abonent hisob raqamida pul mablag‘i yetarli bo‘lmaganligi sababli bloklangan bo‘lsa, AT to‘lov kiritilmagunga qadar hisoblanmaydi.\n" +
                "Ushbu TR doirasida AT hisoblanishining boshlanishi yagona jadval bo‘yicha emas, balki har bir abonent uchun alohida hisob-kitob davri bo‘yicha aniq soniyaga qadar amalga oshiriladi. TR doirasida ATni yechib olish vaqti aniq belgilanmagan bo‘lib, xizmatlardan foydalanish imkoniyati taqdim etilgan vaqtga bog‘liq bo‘ladi.\n" +
                "ATning hisob-kitob davri kalendar oyining 1-sanasiga bog‘lanmagan holda 1 (bir) oyni tashkil etadi va abonentga xizmatdan foydalanish imkoniyati taqdim etilgan sanadan boshlab hisoblanadi. Hisob-kitob sanasi abonentning hisob-kitob davri uchun ATni hisoblashdagi boshlang‘ich nuqtasi hisoblanadi. Misol uchun, abonent TRga\n" +
                "10-sanada ulangan bo‘lsa, u holda navbatdagi oylik AT keyingi oyning 10-sanasida abonentning shaxsiy hisob raqamida TR bo‘yicha AT uchun yetarli mablag‘ bo‘lgandagina yechib olinadi. Agarda abonent 31 avgust kuni ulangan bo‘lsa, u holda navbatdagi ATni yechib olishga urinish 30 sentyabr kuni amalga oshiriladi.\n" +
                "Limitlar TR bo‘yicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob davri tugagunga qadar amal qiladi, TR bo‘yicha foydalanilmay qoldirilgan limitlar keyingi hisob-kitob davriga o‘tkazilmaydi.\n" +
                "Xizmatlar limiti va AT masshtablashtirilmaydi.\n" +
                "Misol uchun: Abonent “O‘zMobayl” tarmog‘iga 30 yanvar kuni ulanganda, AT yechib olinishi 28 yoki 29 fevral kuni (kalendar yiliga ko‘ra) amalga oshiriladi va hisob-kitob davri yakunida Abonent hisobida AT uchun yetarli mablag‘ bo‘lmasa, abonent raqami bloklanadi. Abonent tomonidan balans 5 yoki 10 kundan keyin to‘ldirilsa, abonent hisobidan AT to‘liq hajmda yechib olinadi, TR doirasidagi xizmatning barcha limitlari keyingi oyda AT yechib olingunga qadar bir oy muddatga to‘liq hajmda taqdim etiladi, bunda har oylik ATni yechib olish sanasi keyingi muvaffaqiyatli AT yechib olinadigan sanaga suriladi.\n" +
                "Amaldagi TR “Milliy” TRlariga o‘zgartirilganda, agarda “raqamni ijaraga berish” xizmati yoqilgan bo‘lsa, xizmat uchun hisob-kitob davri yangi TR bo‘yicha keyingi davrdan boshlab abonent to‘lovini hisoblash davri bilan tenglashtiriladi.\n" +
                "Barcha qo‘ng‘iroqlar  so‘zlashuvning birinchi soniyasidan boshlab narxlanadi va to‘liq daqiqa sifatida yaxlitlanib, hisobga olinadi.\n" +
                "Ushbu tarif rejasida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Qabul qilinmagan qo‘ng‘iroq/Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Sevimli raqamlar”, “Restart”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalarlar, MB va SMS paketlari.\n" +
                "To‘plamdagi barcha tarif rejalarida quyidagi xizmatlar mavjud: “Qabul qilinmagan qo‘ng‘iroq / Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Restart”.\n" +
                "“Milliy 15”, “Milliy 10” tarif rejalarida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Sevimli raqamlar”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalar, MB va SMS paketlari.\n" +
                "“Milliy 40”, “Milliy 50”, “Milliy 70”, “Milliy 100” tarif rejalarida quyidagi xizmatlar mavjud: “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "“Milliy” to‘plamidagi TRga quyidagi tarif rejalaridan o‘tish mumkin: “Ishbilarmon”, “Flash”, “OnLime Street Upgrade”, “Royal”, “Flash Upgrade”, “Aloqachi”, “Yoshlar”, “Oddiy 10”, “Street”, “Traffic”, “Ezoz”, “Constructor”, “Step”, “C 1”, “C 2”, “C 3”, “C 4”, “Salom”, “Salom plus”, “Komfort”, “UzMobile 1200”, “UzMobile 4000”, “UzMobile VIP”, “UNITS”, ”Bolajon”, “TA‘LIM B2C”,  “MAKTAB” va “Taʼlim”.\n" +
                "“Milliy” TRdan quyidagi tarif rejalariga o‘tish mumkin: “UNITS”, “Aloqachi”, “C1”, “C2”, “C3”, “C4”, “TA‘LIM B2C” va  “MAKTAB”.\n" +
                "TR to‘plami ichida boshqa tarif rejlariga  o‘tish 4 200 so‘mga  amalga oshiriladi.\n" +
                "“Delovoy”, “Flash”, “OnLime”, «Street Upgrade», “Royal”, «Flash Upgrade», “Aloqachi”, “Yoshlar”, «Prosto 10», “Street”, «C 1», «C 2», «C 3», «C 4»,  “UNITS”, “Bolajon”, «TA‘LIM B2C», “MAKTAB” tarif rejalaridan  “Milliy” TRga o‘tish 4 200 so‘mga amalga oshiriladi.\n" +
                "“Traffic”, “Ezoz”, “Constructor”, “Step”, “Salom”, «Salom plus», “Komfort”, «UzMobile 1200», «UzMobile 4000», «UzMobile VIP», “Taʼlim” tarif rejalaridan  “Milliy” TRga o‘tish bepul amalga oshiriladi.\n\n")
        )

        list.add(Packet("Milliy 40", "*111*1*11*3#", "Oylik: 40 000 so'm\nDaqiqa: Cheksiz\nInternet: 8 GB"))
        listInfo.add(
            PacketInfo("Milliy 40", "O‘zbekiston bo‘yicha daqiqalar/SMS limiti.\n" +
                "Oyiga 45 000 daqiqalik texnologik cheklov o‘rnatilgan. 45 000 daqiqadan so‘ng barcha chiquvchi qo‘ng‘iroqlar bloklanadi.\n" +
                "Ushbu TRga ulanish va boshka TRlariga o‘tish UZTELECOM sotuv va xizmat ko‘rsatish ofislarida hamda dilerlik xizmat ko‘rsatish joylarida amalga oshirilishi mumkin.\n" +
                "Abonent to‘lovi (keyingi o‘rinlarda - AT) hisoblanishi abonent “faol” holatda bo‘lganida amalga oshiriladi.  Agar abonent hisob raqamida pul mablag‘i yetarli bo‘lmaganligi sababli bloklangan bo‘lsa, AT to‘lov kiritilmagunga qadar hisoblanmaydi.\n" +
                "Ushbu TR doirasida AT hisoblanishining boshlanishi yagona jadval bo‘yicha emas, balki har bir abonent uchun alohida hisob-kitob davri bo‘yicha aniq soniyaga qadar amalga oshiriladi. TR doirasida ATni yechib olish vaqti aniq belgilanmagan bo‘lib, xizmatlardan foydalanish imkoniyati taqdim etilgan vaqtga bog‘liq bo‘ladi.\n" +
                "ATning hisob-kitob davri kalendar oyining 1-sanasiga bog‘lanmagan holda 1 (bir) oyni tashkil etadi va abonentga xizmatdan foydalanish imkoniyati taqdim etilgan sanadan boshlab hisoblanadi. Hisob-kitob sanasi abonentning hisob-kitob davri uchun ATni hisoblashdagi boshlang‘ich nuqtasi hisoblanadi. Misol uchun, abonent TRga\n" +
                "10-sanada ulangan bo‘lsa, u holda navbatdagi oylik AT keyingi oyning 10-sanasida abonentning shaxsiy hisob raqamida TR bo‘yicha AT uchun yetarli mablag‘ bo‘lgandagina yechib olinadi. Agarda abonent 31 avgust kuni ulangan bo‘lsa, u holda navbatdagi ATni yechib olishga urinish 30 sentyabr kuni amalga oshiriladi.\n" +
                "Limitlar TR bo‘yicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob davri tugagunga qadar amal qiladi, TR bo‘yicha foydalanilmay qoldirilgan limitlar keyingi hisob-kitob davriga o‘tkazilmaydi.\n" +
                "Xizmatlar limiti va AT masshtablashtirilmaydi.\n" +
                "Misol uchun: Abonent “O‘zMobayl” tarmog‘iga 30 yanvar kuni ulanganda, AT yechib olinishi 28 yoki 29 fevral kuni (kalendar yiliga ko‘ra) amalga oshiriladi va hisob-kitob davri yakunida Abonent hisobida AT uchun yetarli mablag‘ bo‘lmasa, abonent raqami bloklanadi. Abonent tomonidan balans 5 yoki 10 kundan keyin to‘ldirilsa, abonent hisobidan AT to‘liq hajmda yechib olinadi, TR doirasidagi xizmatning barcha limitlari keyingi oyda AT yechib olingunga qadar bir oy muddatga to‘liq hajmda taqdim etiladi, bunda har oylik ATni yechib olish sanasi keyingi muvaffaqiyatli AT yechib olinadigan sanaga suriladi.\n" +
                "Amaldagi TR “Milliy” TRlariga o‘zgartirilganda, agarda “raqamni ijaraga berish” xizmati yoqilgan bo‘lsa, xizmat uchun hisob-kitob davri yangi TR bo‘yicha keyingi davrdan boshlab abonent to‘lovini hisoblash davri bilan tenglashtiriladi.\n" +
                "Barcha qo‘ng‘iroqlar  so‘zlashuvning birinchi soniyasidan boshlab narxlanadi va to‘liq daqiqa sifatida yaxlitlanib, hisobga olinadi.\n" +
                "Ushbu tarif rejasida quyidagi xizmatlar mavjud: “Mediabay”dan onlayn-kinoteatr, “Qabul qilinmagan qo‘ng‘iroq/Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”,  “Restart”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "To‘plamdagi barcha tarif rejalarida quyidagi xizmatlar mavjud: “Qabul qilinmagan qo‘ng‘iroq / Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Restart”.\n" +
                "“Milliy 15”, “Milliy 10” tarif rejalarida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Sevimli raqamlar”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalar, MB va SMS paketlari.\n" +
                "“Milliy 40”, “Milliy 50”, “Milliy 70”, “Milliy 100” tarif rejalarida quyidagi xizmatlar mavjud: “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "“Milliy” to‘plamidagi TRga quyidagi tarif rejalaridan o‘tish mumkin: “Ishbilarmon”, “Flash”, “OnLime Street Upgrade”, “Royal”, “Flash Upgrade”, “Aloqachi”, “Yoshlar”, “Oddiy 10”, “Street”, “Traffic”, “Ezoz”, “Constructor”, “Step”, “C 1”, “C 2”, “C 3”, “C 4”, “Salom”, “Salom plus”, “Komfort”, “UzMobile 1200”, “UzMobile 4000”, “UzMobile VIP”, “UNITS”, ”Bolajon”, “TA‘LIM B2C”,  “MAKTAB” va “Taʼlim”.\n" +
                "“Milliy” TRdan quyidagi tarif rejalariga o‘tish mumkin: “UNITS”, “Aloqachi”, “C1”, “C2”, “C3”, “C4”, “TA‘LIM B2C” va  “MAKTAB”.\n" +
                "TR to‘plami ichida boshqa tarif rejlariga  o‘tish 4 200 so‘mga  amalga oshiriladi.\n" +
                "“Delovoy”, “Flash”, “OnLime”, «Street Upgrade», “Royal”, «Flash Upgrade», “Aloqachi”, “Yoshlar”, «Prosto 10», “Street”, «C 1», «C 2», «C 3», «C 4»,  “UNITS”, “Bolajon”, «TA‘LIM B2C», “MAKTAB” tarif rejalaridan  “Milliy” TRga o‘tish 4 200 so‘mga amalga oshiriladi.\n" +
                "“Traffic”, “Ezoz”, “Constructor”, “Step”, “Salom”, «Salom plus», “Komfort”, «UzMobile 1200», «UzMobile 4000», «UzMobile VIP», “Taʼlim” tarif rejalaridan  “Milliy” TRga o‘tish bepul amalga oshiriladi.\n\n")
        )

        list.add(Packet("Milliy 50", "*111*1*11*4#", "Oylik: 50 000 so'm\nDaqiqa: Cheksiz\nInternet: 12 GB"))
        listInfo.add(
            PacketInfo("Milliy 50", "O‘zbekiston bo‘yicha daqiqalar/SMS limiti.\n" +
                "Oyiga 45 000 daqiqalik texnologik cheklov o‘rnatilgan. 45 000 daqiqadan so‘ng barcha chiquvchi qo‘ng‘iroqlar bloklanadi.\n" +
                "Ushbu TRga ulanish va boshka TRlariga o‘tish UZTELECOM sotuv va xizmat ko‘rsatish ofislarida hamda dilerlik xizmat ko‘rsatish joylarida amalga oshirilishi mumkin.\n" +
                "Abonent to‘lovi (keyingi o‘rinlarda - AT) hisoblanishi abonent “faol” holatda bo‘lganida amalga oshiriladi.  Agar abonent hisob raqamida pul mablag‘i yetarli bo‘lmaganligi sababli bloklangan bo‘lsa, AT to‘lov kiritilmagunga qadar hisoblanmaydi.\n" +
                "Ushbu TR doirasida AT hisoblanishining boshlanishi yagona jadval bo‘yicha emas, balki har bir abonent uchun alohida hisob-kitob davri bo‘yicha aniq soniyaga qadar amalga oshiriladi. TR doirasida ATni yechib olish vaqti aniq belgilanmagan bo‘lib, xizmatlardan foydalanish imkoniyati taqdim etilgan vaqtga bog‘liq bo‘ladi.\n" +
                "ATning hisob-kitob davri kalendar oyining 1-sanasiga bog‘lanmagan holda 1 (bir) oyni tashkil etadi va abonentga xizmatdan foydalanish imkoniyati taqdim etilgan sanadan boshlab hisoblanadi. Hisob-kitob sanasi abonentning hisob-kitob davri uchun ATni hisoblashdagi boshlang‘ich nuqtasi hisoblanadi. Misol uchun, abonent TRga\n" +
                "10-sanada ulangan bo‘lsa, u holda navbatdagi oylik AT keyingi oyning 10-sanasida abonentning shaxsiy hisob raqamida TR bo‘yicha AT uchun yetarli mablag‘ bo‘lgandagina yechib olinadi. Agarda abonent 31 avgust kuni ulangan bo‘lsa, u holda navbatdagi ATni yechib olishga urinish 30 sentyabr kuni amalga oshiriladi.\n" +
                "Limitlar TR bo‘yicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob davri tugagunga qadar amal qiladi, TR bo‘yicha foydalanilmay qoldirilgan limitlar keyingi hisob-kitob davriga o‘tkazilmaydi.\n" +
                "Xizmatlar limiti va AT masshtablashtirilmaydi.\n" +
                "Misol uchun: Abonent “O‘zMobayl” tarmog‘iga 30 yanvar kuni ulanganda, AT yechib olinishi 28 yoki 29 fevral kuni (kalendar yiliga ko‘ra) amalga oshiriladi va hisob-kitob davri yakunida Abonent hisobida AT uchun yetarli mablag‘ bo‘lmasa, abonent raqami bloklanadi. Abonent tomonidan balans 5 yoki 10 kundan keyin to‘ldirilsa, abonent hisobidan AT to‘liq hajmda yechib olinadi, TR doirasidagi xizmatning barcha limitlari keyingi oyda AT yechib olingunga qadar bir oy muddatga to‘liq hajmda taqdim etiladi, bunda har oylik ATni yechib olish sanasi keyingi muvaffaqiyatli AT yechib olinadigan sanaga suriladi.\n" +
                "Amaldagi TR “Milliy” TRlariga o‘zgartirilganda, agarda “raqamni ijaraga berish” xizmati yoqilgan bo‘lsa, xizmat uchun hisob-kitob davri yangi TR bo‘yicha keyingi davrdan boshlab abonent to‘lovini hisoblash davri bilan tenglashtiriladi.\n" +
                "Barcha qo‘ng‘iroqlar  so‘zlashuvning birinchi soniyasidan boshlab narxlanadi va to‘liq daqiqa sifatida yaxlitlanib, hisobga olinadi.\n" +
                "Ushbu tarif rejasida quyidagi xizmatlar mavjud:  “Mediabay”dan onlayn-kinoteatr, “Qabul qilinmagan qo‘ng‘iroq/Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”,  “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”,  “Restart”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "To‘plamdagi barcha tarif rejalarida quyidagi xizmatlar mavjud: “Qabul qilinmagan qo‘ng‘iroq / Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Restart”.\n" +
                "“Milliy 15”, “Milliy 10” tarif rejalarida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Sevimli raqamlar”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalar, MB va SMS paketlari.\n" +
                "“Milliy 40”, “Milliy 50”, “Milliy 70”, “Milliy 100” tarif rejalarida quyidagi xizmatlar mavjud: “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "“Milliy” to‘plamidagi TRga quyidagi tarif rejalaridan o‘tish mumkin: “Ishbilarmon”, “Flash”, “OnLime Street Upgrade”, “Royal”, “Flash Upgrade”, “Aloqachi”, “Yoshlar”, “Oddiy 10”, “Street”, “Traffic”, “Ezoz”, “Constructor”, “Step”, “C 1”, “C 2”, “C 3”, “C 4”, “Salom”, “Salom plus”, “Komfort”, “UzMobile 1200”, “UzMobile 4000”, “UzMobile VIP”, “UNITS”, ”Bolajon”, “TA‘LIM B2C”,  “MAKTAB” va “Taʼlim”.\n" +
                "“Milliy” TRdan quyidagi tarif rejalariga o‘tish mumkin: “UNITS”, “Aloqachi”, “C1”, “C2”, “C3”, “C4”, “TA‘LIM B2C” va  “MAKTAB”.\n" +
                "TR to‘plami ichida boshqa tarif rejlariga  o‘tish 4 200 so‘mga  amalga oshiriladi.\n" +
                "“Delovoy”, “Flash”, “OnLime”, «Street Upgrade», “Royal”, «Flash Upgrade», “Aloqachi”, “Yoshlar”, «Prosto 10», “Street”, «C 1», «C 2», «C 3», «C 4»,  “UNITS”, “Bolajon”, «TA‘LIM B2C», “MAKTAB” tarif rejalaridan  “Milliy” TRga o‘tish 4 200 so‘mga amalga oshiriladi.\n" +
                "“Traffic”, “Ezoz”, “Constructor”, “Step”, “Salom”, «Salom plus», “Komfort”, «UzMobile 1200», «UzMobile 4000», «UzMobile VIP», “Taʼlim” tarif rejalaridan  “Milliy” TRga o‘tish bepul amalga oshiriladi.\n\n")
        )

        list.add(Packet("Milliy 70", "*111*1*11*5#", "Oylik: 70 000 so'm\nDaqiqa: Cheksiz\nInternet: 20 GB"))
        listInfo.add(
            PacketInfo("Milliy 70", "O‘zbekiston bo‘yicha daqiqalar/SMS limiti.\n" +
                "Oyiga 45 000 daqiqalik texnologik cheklov o‘rnatilgan. 45 000 daqiqadan so‘ng barcha chiquvchi qo‘ng‘iroqlar bloklanadi.\n" +
                "Ushbu TRga ulanish va boshka TRlariga o‘tish UZTELECOM sotuv va xizmat ko‘rsatish ofislarida hamda dilerlik xizmat ko‘rsatish joylarida amalga oshirilishi mumkin.\n" +
                "Abonent to‘lovi (keyingi o‘rinlarda - AT) hisoblanishi abonent “faol” holatda bo‘lganida amalga oshiriladi.  Agar abonent hisob raqamida pul mablag‘i yetarli bo‘lmaganligi sababli bloklangan bo‘lsa, AT to‘lov kiritilmagunga qadar hisoblanmaydi.\n" +
                "Ushbu TR doirasida AT hisoblanishining boshlanishi yagona jadval bo‘yicha emas, balki har bir abonent uchun alohida hisob-kitob davri bo‘yicha aniq soniyaga qadar amalga oshiriladi. TR doirasida ATni yechib olish vaqti aniq belgilanmagan bo‘lib, xizmatlardan foydalanish imkoniyati taqdim etilgan vaqtga bog‘liq bo‘ladi.\n" +
                "ATning hisob-kitob davri kalendar oyining 1-sanasiga bog‘lanmagan holda 1 (bir) oyni tashkil etadi va abonentga xizmatdan foydalanish imkoniyati taqdim etilgan sanadan boshlab hisoblanadi. Hisob-kitob sanasi abonentning hisob-kitob davri uchun ATni hisoblashdagi boshlang‘ich nuqtasi hisoblanadi. Misol uchun, abonent TRga\n" +
                "10-sanada ulangan bo‘lsa, u holda navbatdagi oylik AT keyingi oyning 10-sanasida abonentning shaxsiy hisob raqamida TR bo‘yicha AT uchun yetarli mablag‘ bo‘lgandagina yechib olinadi. Agarda abonent 31 avgust kuni ulangan bo‘lsa, u holda navbatdagi ATni yechib olishga urinish 30 sentyabr kuni amalga oshiriladi.\n" +
                "Limitlar TR bo‘yicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob davri tugagunga qadar amal qiladi, TR bo‘yicha foydalanilmay qoldirilgan limitlar keyingi hisob-kitob davriga o‘tkazilmaydi.\n" +
                "Xizmatlar limiti va AT masshtablashtirilmaydi.\n" +
                "Misol uchun: Abonent “O‘zMobayl” tarmog‘iga 30 yanvar kuni ulanganda, AT yechib olinishi 28 yoki 29 fevral kuni (kalendar yiliga ko‘ra) amalga oshiriladi va hisob-kitob davri yakunida Abonent hisobida AT uchun yetarli mablag‘ bo‘lmasa, abonent raqami bloklanadi. Abonent tomonidan balans 5 yoki 10 kundan keyin to‘ldirilsa, abonent hisobidan AT to‘liq hajmda yechib olinadi, TR doirasidagi xizmatning barcha limitlari keyingi oyda AT yechib olingunga qadar bir oy muddatga to‘liq hajmda taqdim etiladi, bunda har oylik ATni yechib olish sanasi keyingi muvaffaqiyatli AT yechib olinadigan sanaga suriladi.\n" +
                "Amaldagi TR “Milliy” TRlariga o‘zgartirilganda, agarda “raqamni ijaraga berish” xizmati yoqilgan bo‘lsa, xizmat uchun hisob-kitob davri yangi TR bo‘yicha keyingi davrdan boshlab abonent to‘lovini hisoblash davri bilan tenglashtiriladi.\n" +
                "Barcha qo‘ng‘iroqlar  so‘zlashuvning birinchi soniyasidan boshlab narxlanadi va to‘liq daqiqa sifatida yaxlitlanib, hisobga olinadi.\n" +
                "Ushbu tarif rejasida quyidagi xizmatlar mavjud: “Mediabay”dan onlayn-kinoteatr, “Qabul qilinmagan qo‘ng‘iroq/Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”,  “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Restart”,  “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "To‘plamdagi barcha tarif rejalarida quyidagi xizmatlar mavjud: “Qabul qilinmagan qo‘ng‘iroq / Tarmoqda”, “Shaxsiy raqam uzatilishini taqiqlash”, “Tezkor o‘tkazmalar”, “Raqam ijarasi”, “Yashirin qo‘ng‘iroq”, “Restart”.\n" +
                "“Milliy 15”, “Milliy 10” tarif rejalarida quyidagi xizmatlar mavjud: “Limitsiz ovoz”, “Tungi qo‘ng‘iroqlar”, “Oila uchun”, “Sevimli raqamlar”, “Foydali almashinuv”, “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha daqiqalar, MB va SMS paketlari.\n" +
                "“Milliy 40”, “Milliy 50”, “Milliy 70”, “Milliy 100” tarif rejalarida quyidagi xizmatlar mavjud: “Qo‘llab yubor”, “Tungi Internet” xizmatlari, shuningdek, ulanish imkoniyati berilgan qo‘shimcha MB va SMS paketlari.\n" +
                "“Milliy” to‘plamidagi TRga quyidagi tarif rejalaridan o‘tish mumkin: “Ishbilarmon”, “Flash”, “OnLime Street Upgrade”, “Royal”, “Flash Upgrade”, “Aloqachi”, “Yoshlar”, “Oddiy 10”, “Street”, “Traffic”, “Ezoz”, “Constructor”, “Step”, “C 1”, “C 2”, “C 3”, “C 4”, “Salom”, “Salom plus”, “Komfort”, “UzMobile 1200”, “UzMobile 4000”, “UzMobile VIP”, “UNITS”, ”Bolajon”, “TA‘LIM B2C”,  “MAKTAB” va “Taʼlim”.\n" +
                "“Milliy” TRdan quyidagi tarif rejalariga o‘tish mumkin: “UNITS”, “Aloqachi”, “C1”, “C2”, “C3”, “C4”, “TA‘LIM B2C” va  “MAKTAB”.\n" +
                "TR to‘plami ichida boshqa tarif rejlariga  o‘tish 4 200 so‘mga  amalga oshiriladi.\n" +
                "“Delovoy”, “Flash”, “OnLime”, «Street Upgrade», “Royal”, «Flash Upgrade», “Aloqachi”, “Yoshlar”, «Prosto 10», “Street”, «C 1», «C 2», «C 3», «C 4»,  “UNITS”, “Bolajon”, «TA‘LIM B2C», “MAKTAB” tarif rejalaridan  “Milliy” TRga o‘tish 4 200 so‘mga amalga oshiriladi.\n" +
                "“Traffic”, “Ezoz”, “Constructor”, “Step”, “Salom”, «Salom plus», “Komfort”, «UzMobile 1200», «UzMobile 4000», «UzMobile VIP», “Taʼlim” tarif rejalaridan  “Milliy” TRga o‘tish bepul amalga oshiriladi.\n\n")
        )

        list.add(Packet("Units 700", "*777*1#", "Abonent to'lovi 7000 so'm 7 kunga"))
        listInfo.add(
            PacketInfo("Units 700", "700 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 1500", "*777*2#", "Abonent to'lovi 15 000 so'm 15 kunga"))
        listInfo.add(
            PacketInfo("Units 1500", "1500 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 4000", "*777*3#", "Abonent to'lovi 20 000 so'm 30 kunga"))
        listInfo.add(
            PacketInfo("Units 4000", "4000 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 8000", "*777*4#", "Abonent to'lovi 35 000 so'm 30 kunga"))
        listInfo.add(
            PacketInfo("Units 8000", "8000 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 11000", "*777*5#", "Abonent to'lovi 45 000 so'm 30 kunga"))
        listInfo.add(
            PacketInfo("Units 11000", "11000 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 14000", "*777*6#", "Abonent to'lovi 55 000 so'm 30 kunga"))
        listInfo.add(
            PacketInfo("Units 14000", "14000 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 18000", "*777*7#", "Abonent to'lovi 65 000 so'm 30 kunga"))
        listInfo.add(
            PacketInfo("Units 18000", "18000 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n")
        )

        list.add(Packet("Units 22000", "*777*8#", "Abonent to'lovi 75 000 so'm 30 kunga"))
        listInfo.add(PacketInfo("Units 22000", "22000 Units\n" +
                "O'zbekiston bo'ylab chiqish qo'ng'iroqlar limiti\n" +
                "1 Unit = 1 daqiqa\n" +
                "1 Unit = 1 MB\n" +
                "1 Unit = 1 SMS\n" +
                "Yechib olish:\n" +
                "\t\t*  Internet xizmatidan foydalanilganda, Internet-trafik kvant tarifikatsiyasiga muvofiq 16 KV da yechib olinadi – “Units” mutanosib ravishda yechib olinadi (1 “Units” = 1 MB ga hisobida).\n" +
                "\t\t*  SMSdan foydalanilganda, abonent resurslaridan har bir SMS uchun 1 “Units” birlik yechib olinadi (1 “Units” = 1 SMS).\n" +
                "\t\t*  Ovozli qoʻngʻiroqni amalga oshirilganda, abonent resurslaridan 1 Units birlik yechib olinadi, har bir qoʻngʻiroq bir daqiqagacha yaxlitlanadi (1 “Units” = 1 daqiqa).\n" +
                "Tarif rejaning (TR) hisob-kitob davri – tanlangan opsiyaga qarab –- 7, 15 yoki 30 kun.\n" +
                "Barcha Units resurslarini sarf etib boʻlinganda, chiquvchi qoʻngʻiroqlar, SMS va Internetga kirish bloklanadi. Imkoniyat quyidagi hollarda tiklanadi:\n" +
                "\t\t*  hisob-kitob davri kelganda abonent toʻlovi (AT) toʻlangandan soʻng;\n" +
                "\t\t*  “Restart” xizmati faollashtirilganda (abonent toʻlovi yechib olinadi, AT yechib olingandan soʻng, tanlangan TRga muvofiq resurslar taqdim etiladi);\n" +
                "\t\t*  ushbu toʻplamdagi boshqa tarif rejaga oʻtilganda va tanlangan tarifning AT toʻlanganda.\n" +
                "Tarif toʻplamlari ichida oʻtishlar – bepul.\n" +
                "Ushbu tarif rejalaridan boshqa tarif rejalariga oʻtish amalga oshirilayotgan tarif rejasi shartlariga muvofiq.\n" +
                "“Units” tarif rejalariga boshqa tarif rejalaridan oʻtish bepul.\n" +
                "“Units” tarif toʻplamlari ichida oʻzaro oʻtilganda resurslar (“Units”) saqlab qolinadi (ularning amal qilish muddatiga muvofiq).\n" +
                "Ushbu tarif rejalarga har qanday tarif rejalardan (quyidagi tariflar bundan mustasno: Uzmobile M2M, Uzmobile M2M yil, M2M basic, UZCARD GSM, HUMO GSM) har qanday qulay usul bilan (USSD-buyruq, SMS, shaxsiy kabinet, “MyUZTELECOM” ilovasi, sotuv ofislari, call-centre) oʻtish mumkin.\n" +
                "“Units” TR toʻplamidan boshqa tarif rejalarga oʻtishda taqdim etilgan resurslar (“Units”) saqlanib qolinmaydi.\n" +
                "Boshqa TRlardan “Units” tarif toʻplamiga oʻtishda, abonentda qolib ketgan barcha resurslar (tarif reja tomonidan taqdim qilingan, shuningdek sotib olingan daq/MB/SMS lar shular jumlasidan) kuyib ketadi.\n" +
                "Ushbu TRlarga ulanish va boshka TRlarga oʻtish UZTELECOMning sotuv va xizmat koʻrsatish ofislarida hamda dilerlik xizmat koʻrsatish joylarida ham mavjud.\n" +
                "Ushbu TRlar doirasida abonent toʻlovini hisoblashni boshlash har bir Abonent uchun yagona jadval boʻyicha emas, alohida tartibda AT yechib olingan vaqtdan boshlab hisob-kitob davri uchun soniya aniqligi bilan amalga oshiriladi.\n" +
                "Resurslar TR boʻyicha AT muvaffaqiyatli yechib olinganda taqdim etiladi va joriy hisob-kitob oyining soʻnggiga qadar amal qiladi, TR boʻyicha sarf etilmagan limitlar keyingi hisob-kitob davriga oʻtmaydi.\n" +
                "“Foydali almashinuv”, “Qoʻllab yubor”, “Cheksiz ovoz”, “Tungi qoʻngʻiroqlar”, “Tungi internet”, “Oila uchun” va “Sevimli raqamlar” xizmatlari ushbu toʻplam TRlari uchun ulanish mavjud emas.\n" +
                "Ushbu tariflar toʻplamida quyidagi xizmatlar mavjud: “Restart”, “ZiyoNET” va “Tezkor oʻtkazma”. “Units” toʻplamidagi TRga oʻtishda yuqorida sanab oʻtilgan xizmatlarni koʻrsatish shartlari va muddatlari oʻzgarishsiz qoladi.\n" +
                "Internet toʻplamlar, Kunlik non-stop, Kunlik internet toʻplamlar, Internet non-stop, TAS-IX uchun Internet-toʻplamlar, SMS toʻplamlar, daqiqalar toʻplami, har kun uchun SMS, xalqaro SMS toʻplamlar — quyidagi toʻplamlarga ulanish mavjud emas.\n\n"))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tariflar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tariflar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}