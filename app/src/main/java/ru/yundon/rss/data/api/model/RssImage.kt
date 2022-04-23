package ru.yundon.rss.data.api.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "enclosure")
data class RssImage(
    @Attribute(name="url")
    val url: String
)