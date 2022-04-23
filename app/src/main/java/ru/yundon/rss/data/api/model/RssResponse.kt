package ru.yundon.rss.data.api.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "rss")
data class RssResponse(
    @Element(name="channel")
    val channel: RssChannel
)






