package ru.yundon.rss.room.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Query("SELECT * FROM rss_table")
    fun getListRssNews(): Flow<List<RssEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRssNewsItem(item: RssEntity)

    @Delete
    suspend fun deleteItem(item: RssEntity)

}