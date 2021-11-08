package meehan.matthew.spaceapp.ui.articleList

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.color.MaterialColors
import meehan.matthew.spaceapp.R
import meehan.matthew.spaceapp.data.ArticleResponseItem
import meehan.matthew.spaceapp.databinding.ArticleViewHolderBinding

class ArticleAdapter(
    private val onClick: (ArticleResponseItem) -> Unit
) : ListAdapter<ArticleResponseItem, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {

    private class ArticleDiffCallback: DiffUtil.ItemCallback<ArticleResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ArticleResponseItem,
            newItem: ArticleResponseItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ArticleResponseItem,
            newItem: ArticleResponseItem
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(ArticleViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ArticleViewHolder(
        private val binding: ArticleViewHolderBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleResponseItem) {
            binding.apply {
                articleImageView.load(article.imageUrl) {
                    placeholder(
                        ColorDrawable(MaterialColors.getColor(binding.root, R.attr.colorPrimary))
                    )
                }
                articleTitleTextView.text = article.title
                articleSubtitleTextView.text = article.newsSite
                articleLayoutView.setOnClickListener {
                    onClick.invoke(article)
                }
            }
        }
    }
}