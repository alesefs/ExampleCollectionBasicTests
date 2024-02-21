package com.example.mylibrary.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.commons.Utils
import com.facebook.shimmer.ShimmerFrameLayout

class DetailsAdapter(
    private val items: List<DetailsItemsModel>
) : RecyclerView.Adapter<DetailsAdapter.DetailsAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_detail_data_item, parent, false)
        return DetailsAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsAdapterViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DetailsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutCast: ConstraintLayout = itemView.findViewById(R.id.cast_layout_detail)
        private val titleitem: TextView = itemView.findViewById(R.id.title_detail)
        private val valueItem: TextView = itemView.findViewById(R.id.data_detail)
        private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_view_container)

        fun bindView(item: DetailsItemsModel) {
            shimmer.startShimmerAnimation()
            titleitem.visibility = View.GONE
            valueItem.visibility = View.GONE
            Utils.cleanFocus(itemView)


            shimmer.postDelayed({
                shimmer.stopShimmerAnimation()
                shimmer.visibility = View.GONE

                titleitem.text = item.item.keys.first()
                titleitem.visibility = View.VISIBLE
                valueItem.text = item.item.values.first()
                valueItem.visibility = View.VISIBLE

                itemView.contentDescription = "${item.item.keys.first()} ${item.item.values.first()}"
                itemView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                itemView.isFocusable = true
                if (adapterPosition == 0) {
                    Utils.forceAndRequestFocus(itemView)
                }
            }, 2000L)
        }
    }
}
