package com.example.mylibrary.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.commons.Utils
import okhttp3.internal.Util

class MainMenuAdapter(
    private val listButton: List<MainMenuButtonsModel>,
    private val onClickListener: (buttonItem: MainMenuButtonsModel) -> Unit
) : RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_button_custom, parent, false)
        return MainMenuViewHolder(itemView, onClickListener)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bindView(listButton[position])
    }

    override fun getItemCount(): Int = listButton.size

    class MainMenuViewHolder(
        itemView: View,
        private val onClickListener: (itemClick: MainMenuButtonsModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val buttonCast: ConstraintLayout = itemView.findViewById(R.id.menu_btn_cast)
        private val textButton: TextView = itemView.findViewById(R.id.txt_btn)
        private val imageButton: ImageView = itemView.findViewById(R.id.img_btn)

        fun bindView(item: MainMenuButtonsModel) {
            textButton.text = item.title
            imageButton.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.image))
            Utils.changeColorToImageView(item.key, imageButton, itemView.context)

            buttonCast.setOnClickListener {
                onClickListener.invoke(item)
            }
        }

    }
}
