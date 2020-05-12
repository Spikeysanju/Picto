package www.spikeysanju.picto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_post.view.*
import www.spikeysanju.picto.R
import www.spikeysanju.picto.model.Post

class PostAdapter: RecyclerView.Adapter<PostAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val differCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

     val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_post,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val post = differ.currentList[position]
        holder.itemView.apply {

            item_post_image.load(post.url) {
                crossfade(true)
                crossfade(200)
                transformations(
                    RoundedCornersTransformation(
                        8f,
                        8f,
                        8f,
                        8f
                    )
                )


            }

            // on item click
            setOnClickListener {
                onItemClickListener?.let { it(post) }
            }
        }

    }


    // on item click listener
    private var onItemClickListener: ((Post) -> Unit)? = null
    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }

}