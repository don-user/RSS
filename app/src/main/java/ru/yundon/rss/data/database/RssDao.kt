package ru.yundon.rss.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Query("SELECT * FROM rss_table")
    fun getListRssNews(): Flow<List<RssDbModel>>

    suspend fun insertRssNews(): Flow<List<RssDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRssNewsList(list: List<RssDbModel>)


}