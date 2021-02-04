package com.hw.apodmaterialdesign.view.fragment.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.view.fragment.notes.data.Note
import com.hw.apodmaterialdesign.view.fragment.notes.item.ItemTouchHelperCallback
import com.hw.apodmaterialdesign.view.fragment.notes.item.OnStartDragListener
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(){

    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arrayListOf(Note("Title", "Text"))

        val adapter = RvAdapter(data,
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            }
        )
        recyclerView.adapter = adapter

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
}