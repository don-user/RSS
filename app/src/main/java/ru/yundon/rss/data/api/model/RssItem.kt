package ru.yundon.rss.data.api.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "item")
data class RssItem(
    @PropertyElement(name="title")
    val title: String,
    @PropertyElement(name="description")
    val description: String,
    @PropertyElement(name="link")
    val link: String,
    @PropertyElement(name="pubDate")
    val pubDate: String,
    @Element(name="enclosure")
    val enclosure: RssImage
)