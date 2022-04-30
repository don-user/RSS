package ru.yundon.rss.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Query("SELECT * FROM rss_table")
    fun getListRssNews(): Flow<List<RssDbModel>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRssNewsItem(item: RssDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRssNewsList(list: List<RssDbModel>)


}