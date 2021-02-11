package com.hw.apodmaterialdesign.view.fragment.notes.item

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}