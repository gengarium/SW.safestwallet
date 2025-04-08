package com.safestwallet.sw.ui.whitelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.safestwallet.sw.R

import com.safestwallet.sw.model.WhitelistEntry

class WhitelistAdapter(
    private var entries: List<WhitelistEntry>,
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<WhitelistAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val addressTextView: TextView = view.findViewById(R.id.text_address)
        val deleteButton: Button = view.findViewById(R.id.button_remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_whitelist_address, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.addressTextView.text = entry.address
        holder.deleteButton.setOnClickListener {
            onDeleteClick(entry.address)
        }
    }

    override fun getItemCount() = entries.size
    
    fun updateData(newEntries: List<WhitelistEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}
