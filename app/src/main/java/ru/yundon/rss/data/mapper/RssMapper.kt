package ru.yundon.rss.data.mapper

import ru.yundon.rss.data.api.dto.RssItemDto
import ru.yundon.rss.data.database.RssDbModel
import ru.yundon.rss.domain.RssEntity

class RssMapper {

    fun mapDtoListToDbModelList(dtoList: List<RssItemDto>) : List<RssDbModel>{
        return dtoList.map {
            RssDbModel(
                title = it.title,
                link = it.link,
                description = it.description,
                pubDate = it.pubDate,
                imageUrl = it.enclosure.url,
                isFavorites = false
            )
        }
    }

    fun mapDbModelListToEntityList(dbModel: List<RssDbModel>): List<RssEntity>{
        return dbModel.map {
            RssEntity (
                title = it.title,
                link = it.link,
                description = it.description,
                pubDate = it.pubDate,
                imageUrl = it.imageUrl,
                isFavorites = false
            )
        }
    }

    fun mapRssEntityToDbModel(entity: RssEntity) : RssDbModel = RssDbModel(
        title = entity.title,
        link = entity.link,
        description = entity.description,
        pubDate = entity.pubDate,
        imageUrl = entity.imageUrl,
        isFavorites = entity.isFavorites
    )
}