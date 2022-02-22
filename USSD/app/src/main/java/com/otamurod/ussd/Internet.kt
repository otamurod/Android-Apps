package com.otamurod.ussd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
 * Use the [Internet.newInstance] factory method to
 * create an instance of this fragment.
 */
class Internet : Fragment() {
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
        root = inflater.inflate(R.layout.fragment_internet, container, false)

        if (count == 1){
            loadData()
            count++
        }

        listAdapter = ListAdapter(root.context, list)
        root.list.adapter = listAdapter

        root.list.setOnItemClickListener { parent, view, position, id ->

            val bundle = bundleOf("info" to listInfo, "position" to position)

            root.findNavController().navigate(R.id.internetInfo, bundle)
        }

        return root
    }

    private fun loadData() {
        val info = "Shu kabi paketlar Mobil Byudjet xizmatidan foydalanuvchilar uchun mavjud. (Korporativ mijozlar uchun *222# menyu orqali faollashtiriladi);\n" +
                "Internet-paketlar menyusiga kirish uchun: *111*1*9# (o'zbek tilida ma'lumot), *111*2*9# (rus tilida ma'lumot) ni terish kerak;\n" +
                "Faollashtirilgan Internet-paket (yoki tarif rejadagi) Internet trafik holatini tekshirish uchun  *100*2# yoki 9999 raqamiga 1002 SMS matnini yuborish kerak;\n" +
                "Internet-paketlar ham jismoniy, ham yuridik shaxslarga taqdim etiladi;\n" +
                "Internet paketining amal qilish muddati ulanish vaqtidan boshlab 30 kun;\n" +
                "Joriy paketni yangilash ushbu guruhdan boshqa Internet-paketni sotib olish yo'li bilan amalga oshiriladi. Umumiy megabaytning amal qilish muddati oxirgi paket xarid qilingan kundan boshlab (to'plamga kiritilgan Internet-trafik miqdori bo'yicha) 30 kunni tashkil qiladi;\n" +
                "Internet to'plamining amal qilish muddati tugagach, keyingi internet-to'plamning faollashtirilmagan bo‘lsa, qoldiq megabaytlar saqlanmaydi;\n" +
                "Agar abonentda Internet-paket mavjud bo‘lib, Internet-paketdan sessiya uchun MB yetarli bo‘lmasa (50 Mbdan kam), u holda hisob asosiy balansdan (agar unda pul mablag‘lari mavjud bo‘lsa) amalga oshiriladi;\n" +
                "Internet-trafikni sarflashning ustuvorligi:\n\n"

        list.add(Packet("1 GB", "*111*1*9*1#", "Narxi: 9 000 so‘m\nMuddati: 30 kun"))
        listInfo.add(PacketInfo("1 GB", info))

        list.add(Packet("1.5 GB", "*111*1*9*2#", "Narxi: 14 000 so‘m\nMuddati: 30 kun"))
        listInfo.add(PacketInfo("1.5 GB", info))

        list.add(Packet("3 GB", "*111*1*9*3#", "Narxi: 18 000 so‘m\nMuddati: 30 kun"))
        listInfo.add(PacketInfo("3", info))

        list.add(Packet("5 GB", "*111*1*9*4#", "Narxi: 25 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("5 GB", info))

        list.add(Packet("8 GB", "*111*1*9*5#", "Narxi: 35 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("8 GB", info))

        list.add(Packet("12 GB", "*111*1*9*6#", "Narxi: 50 000  so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("12 GB", info))

        list.add(Packet("16 GB", "*111*1*9*11#", "Narxi: 60 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("16 GB", info))

        list.add(Packet("20 GB", "*111*1*9*7#", "Narxi: 65 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("20 GB", info))

        list.add(Packet("30 GB", "*111*1*9*8#", "Narxi: 75 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("30 GB", info))

        list.add(Packet("50 GB", "*111*1*9*9#", "Narxi: 85 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("50 GB", info))

        list.add(Packet("75 GB", "*111*1*9*10#", "Narxi: 110 000 so‘m\nMuddati: 30 kun0"))
        listInfo.add(PacketInfo("75 GB", info))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Internet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Internet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}