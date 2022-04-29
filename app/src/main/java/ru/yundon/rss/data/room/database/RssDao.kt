package ru.yundon.rss.data.room.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Query("SELECT * FROM rss_table")
    fun getListRssNews(): Flow<List<RssDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRssNewsItem(item: RssDbModel)

    @Delete
    suspend fun deleteItem(item: RssDbModel)

}