package com.ezzy.cashapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezzy.cashapp.R
import com.ezzy.cashapp.data.model.CashEntry
import com.ezzy.cashapp.databinding.EntryItemBinding

class CashEntryAdapter(private val context: Context) :
    ListAdapter<CashEntry, CashEntryAdapter.CashEntryViewHolder>(CashEntryCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashEntryViewHolder {
        return CashEntryViewHolder(
            EntryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CashEntryViewHolder, position: Int) {
        holder.apply {
            bindItem(getItem(position))
        }
    }

    private object CashEntryCallBack : DiffUtil.ItemCallback<CashEntry>() {
        override fun areItemsTheSame(
            oldItem: CashEntry,
            newItem: CashEntry
        ): Boolean {
            return oldItem == oldItem
        }

        override fun areContentsTheSame(
            oldItem: CashEntry,
            newItem: CashEntry
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class CashEntryViewHolder(
        private val binding: EntryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: CashEntry) {
            with(binding) {
                amount.text = context.getString(
                    R.string.amt,
                    item.amount
                )
            }
        }

    }
}