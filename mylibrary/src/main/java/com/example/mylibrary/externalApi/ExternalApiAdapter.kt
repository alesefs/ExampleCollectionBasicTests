package com.example.mylibrary.externalApi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.externalApi.repository.models.PostModel

class ExternalApiAdapter(
    private val posts: List<PostModel>
) : RecyclerView.Adapter<ExternalApiAdapter.ExternalApiAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExternalApiAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_external_api, parent, false)

        return ExternalApiAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExternalApiAdapterViewHolder, position: Int) {
        holder.bindView(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    class ExternalApiAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postId: TextView = itemView.findViewById(R.id.textViewExternalApiId)
        private val postTitle: TextView = itemView.findViewById(R.id.textViewExternalApiTitle)
        private val postBody: TextView = itemView.findViewById(R.id.textViewExternalApiBody)

        fun bindView(post: PostModel) {
            postId.text = itemView.context.getString(R.string.id_external_api, post.id, post.userId)
            postTitle.text = post.title
            postBody.text = post.body
        }
    }
}
