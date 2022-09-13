package com.otamurod.yolgahamroh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.yolgahamroh.databinding.ItemDriverBinding
import com.otamurod.yolgahamroh.databinding.ItemPassengerBinding
import com.otamurod.yolgahamroh.models.User

class UserAdapter(
    var listSupplier: ArrayList<User>,
    var onItemClick: OnItemClick
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class DriverVH(var itemDriverBinding: ItemDriverBinding) :
        RecyclerView.ViewHolder(itemDriverBinding.root) {

        fun onBind(user: User) {
            itemDriverBinding.driverName.text = user.name
            itemDriverBinding.taxiFare.text = "Narxi: ${user.taxi_fare}"
            itemDriverBinding.startingTime.text = user.time
            itemDriverBinding.seats.text = "Bo'sh joy: ${user.seats} o'rin"

            itemDriverBinding.driverName.setOnClickListener {
                onItemClick.onCallClick(user.phone!!)
            }

        }
    }

    inner class PassengerVH(var itemPassengerBinding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(itemPassengerBinding.root) {

        fun onBind(user: User) {
            itemPassengerBinding.passengerName.text = user.name
            itemPassengerBinding.taxiFare.text = "Narxi: ${user.taxi_fare}"
            itemPassengerBinding.startTime.text = user.time
            itemPassengerBinding.seats.text = "Soni: ${user.seats} kishi"

            itemPassengerBinding.passengerName.setOnClickListener {
                onItemClick.onCallClick(user.phone!!)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return DriverVH(
                ItemDriverBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return PassengerVH(
                ItemPassengerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == 1) {
            val driverVH = holder as DriverVH
            driverVH.onBind(listSupplier[position])
        } else {
            val toVH = holder as PassengerVH
            toVH.onBind(listSupplier[position])
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (listSupplier[position].type == "Haydovchi") {
            return 1
        } else {
            return 2
        }
    }

    override fun getItemCount(): Int {
        return listSupplier.size
    }

    interface OnItemClick {
        fun onCallClick(phoneNumber: String)
    }

}