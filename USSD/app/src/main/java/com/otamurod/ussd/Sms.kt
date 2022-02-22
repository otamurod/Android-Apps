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
 * Use the [Minutes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Sms : Fragment() {
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
        root = inflater.inflate(R.layout.fragment_sms, container, false)

        if (count == 1){
            loadData()
            count++
        }

        listAdapter = ListAdapter(root.context, list)
        root.list.adapter = listAdapter

        root.list.setOnItemClickListener { parent, view, position, id ->

            val bundle = bundleOf("info" to listInfo, "position" to position)

            root.findNavController().navigate(R.id.smsInfo, bundle)
        }

        return root
    }

    private fun loadData() {
        val info = "Xizmat faqat jismoniy shaxs, GSM abonentlari uchun amal qiladi.\n" +
                "SMS-paketlari O‘zbekiston bo‘ylab (mobil aloqa abonentlari raqamiga) chiquvchi SMS-xabar jo‘natishga mo‘ljallangan.\n" +
                "Paketlarning amal qilish muddati – faollashtirilgan vaqtdan boshlab 30 kecha-kunduz.\n" +
                "SMS uchun navbatdagi bir turdagi paketni sotib olishda trafik jamlanadi, amal qilish muddati esa oxirgi sotib olingan paket amal qilish muddatiga muvofiq belgilanadi.\n" +
                "Tarif reja yoki telefon raqamini almashtirishda, sotib olingan paket bo‘yicha trafik qoldig‘i saqlanib qoladi.\n" +
                "Paketlar abonent Uzmobile GSM tarmog'i amal qilish zonasida bo‘lganida haqiqiy hisoblanadi.\n\n"

        list.add(Packet("MS 10 SMS", "*111*1*1*1#", "Narxi: 420 so‘m\nSMS Soni: 10"))
        listInfo.add(PacketInfo("MS 10 SMS", info))

        list.add(Packet("MS 50 SMS", "*111*1*1*2#", "Narxi: 1 680 so‘m\nSMS Soni: 50"))
        listInfo.add(PacketInfo("MS 50 SMS", info))

        list.add(Packet("MS 200 SMS", "*111*1*1*3#", "Narxi: 5 200 so‘m\nSMS Soni: 200"))
        listInfo.add(PacketInfo("MS 200 SMS", info))

        list.add(Packet("MMS 500 SMS", "*111*1*1*4#", "Narxi: 9 500 so‘m\nSMS Soni: 500"))
        listInfo.add(PacketInfo("MS 500 SMS", info))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Minutes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Minutes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}