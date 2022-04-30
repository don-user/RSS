package ru.yundon.rss.presantation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.yundon.rss.R
import ru.yundon.rss.data.database.RssDbModel
import ru.yundon.rss.databinding.RssNewsItemBinding
import ru.yundon.rss.domain.RssEntity


class RssAdapter(private val onItemClickListener: ItemClickListener): RecyclerView.Adapter<RssAdapter.RssViewHolder>() {

        private val rssList = mutableListOf<RssEntity>()
        private lateinit var binding: RssNewsItemBinding


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssViewHolder {
            binding = RssNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RssViewHolder(binding)
        }

        override fun onBindViewHolder(holder: RssViewHolder, position: Int) {
            holder.bind(rssList[position], onItemClickListener)
        }

        override fun getItemCount(): Int = rssList.size


        class RssViewHolder(private val itemBinding: RssNewsItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

            fun bind(listDbModel: RssEntity, onItemCallback: ItemClickListener) = with(itemBinding) {

                dateNews.text = listDbModel.pubDate
                nameNews.text = listDbModel.title
                itemLink.text = listDbModel.link

                Picasso.get()
                    .load(listDbModel.imageUrl)
                    .fit()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(image)

                favoritesImage.setImageResource(
                    if (listDbModel.isFavorites) R.drawable.favorite_orange
                    else R.drawable.favorite_border_orange
                )

                favoritesImage.setOnClickListener {
                    onItemCallback.onFavoriteClick(listDbModel)
                }
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        fun updateRssList(listDbModel: List<RssEntity>){
            rssList.clear()
            rssList.addAll(listDbModel)
            notifyDataSetChanged()
        }

    interface ItemClickListener {
        fun onFavoriteClick(item: RssEntity)
    }
}

