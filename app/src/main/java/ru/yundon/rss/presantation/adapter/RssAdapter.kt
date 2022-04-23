package ru.yundon.rss.presantation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.yundon.rss.R
import ru.yundon.rss.data.room.database.RssEntity
import ru.yundon.rss.databinding.RssNewsItemBinding


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

            fun bind(listEntity: RssEntity, onItemCallback: ItemClickListener) = with(itemBinding) {

                dateNews.text = listEntity.pubDate
                nameNews.text = listEntity.title
                itemLink.text = listEntity.link

                Picasso.get()
                    .load(listEntity.imageUrl)
                    .fit()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(image)

                favoritesImage.setImageResource(
                    if (listEntity.isFavorites) R.drawable.favorite_orange
                    else R.drawable.favorite_border_orange
                )

                favoritesImage.setOnClickListener {
                    onItemCallback.onFavoriteClick(listEntity)
                }
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        fun updateRssList(listEntity: List<RssEntity>){
            rssList.clear()
            rssList.addAll(listEntity)
            notifyDataSetChanged()
        }

    interface ItemClickListener {
        fun onFavoriteClick(item: RssEntity)
    }
}

