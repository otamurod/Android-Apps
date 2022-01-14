package com.otamurod.draganddrop.utils

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition:Int, toPosition:Int){}

    fun onItemDismiss(position:Int){}
}