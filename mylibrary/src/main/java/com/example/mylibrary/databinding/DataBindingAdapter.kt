package com.example.mylibrary.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R

class DataBindingAdapter(
    private val personalInfoList: List<PersonalInfo>,
    private val onClickListener: (buttonItem: PersonalInfo) -> Unit
) : RecyclerView.Adapter<DataBindingAdapter.DataBindingAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userbinding: LayoutDatabindingItemsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_databinding_items, parent,false)
        return DataBindingAdapterViewHolder(userbinding, onClickListener)
    }

    override fun onBindViewHolder(holder: DataBindingAdapterViewHolder, position: Int) {
        holder.bindView(personalInfoList[position])
    }

    override fun getItemCount(): Int = personalInfoList.size

    class DataBindingAdapterViewHolder(
        private val binding: LayoutDatabindingItemsBinding,
        private val onClickListener: (buttonItem: PersonalInfo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: PersonalInfo) {
            binding.apply {
                personalInfo = item
                castLayoutDatabinding.setOnClickListener {
                    onClickListener.invoke(item)
                }
            }
        }
    }
}