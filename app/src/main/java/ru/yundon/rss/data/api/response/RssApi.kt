package ru.yundon.rss.data.api.response

import retrofit2.http.GET
import ru.yundon.rss.data.api.model.RssResponse

interface RssApi {

    @GET("breaking/rss")
    suspend fun getBreakingNews(): RssResponse

    @GET("hardware-news/rss")
    suspend fun getHardwareNews(): RssResponse

    @GET("gadgets/rss")
    suspend fun getGadgetsNews(): RssResponse

    @GET("software-news/rss")
    suspend fun getSoftwareNews(): RssResponse

    @GET("games/rss")
    suspend fun getGameNews(): RssResponse
}