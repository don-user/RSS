package ru.yundon.rss.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RssEntity::class], version = 1, exportSchema = false)
abstract class RssDatabase: RoomDatabase() {

    abstract fun rssDao(): RssDao

    companion object{
        @Volatile
        private var RSS_TABLE: RssDatabase? = null

        fun buildRssDatabase(context: Context): RssDatabase{
            val table = RSS_TABLE
            if (table != null){
                return table
            }
            synchronized(this){
                val tableRssNews = Room.databaseBuilder(context, RssDatabase::class.java, "RssNews").build()
                RSS_TABLE = tableRssNews
                return tableRssNews
            }
        }
    }
}