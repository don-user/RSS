package ru.yundon.rss.data.api.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "channel")
data class RssChannel(
    @Element(name="item")
    val itemList: List<RssItem>
)