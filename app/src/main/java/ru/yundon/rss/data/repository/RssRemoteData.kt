package ru.yundon.rss.data.repository

//
//class RssRemoteData {
//
//    suspend fun getNewsList(newsName: String?): List<RssDbModel>{
//
//        val listRssItemDto: List<RssItemDto> = try {
//            when(newsName){
//                BREAKING_NEWS -> RssApiClient.ApiRetrofit.getBreakingNews().channelDto.itemDtoList
//                HARDWARE_NEWS -> RssApiClient.ApiRetrofit.getHardwareNews().channelDto.itemDtoList
//                GADGETS_NEWS -> RssApiClient.ApiRetrofit.getGadgetsNews().channelDto.itemDtoList
//                SOFTWARE_NEWS -> RssApiClient.ApiRetrofit.getSoftwareNews().channelDto.itemDtoList
//                GAMES_NEWS -> RssApiClient.ApiRetrofit.getGameNews().channelDto.itemDtoList
//                else -> emptyList()
//            }
//        }catch (e: Exception){
//            emptyList()
//        }
//
//
//        val listRssNews = listRssItemDto.map {
//            RssDbModel(
//                it.title,
//                it.link,
//                it.description,
//                it.pubDate,
//                it.enclosure.url,
//                isFavorites = false
//            )
//        }
//        return listRssNews
//    }
//}