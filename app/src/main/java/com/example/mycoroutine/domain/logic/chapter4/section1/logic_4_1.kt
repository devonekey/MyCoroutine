package com.example.mycoroutine.domain.logic.chapter4.section1

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.newFixedThreadPoolContext
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

private val feeds = listOf(
    "https://www.npr.org/rss/rss.php?id=1001",
    "http://rss.cnn.com/rss/cnn_topstories.rss",
    "http://feeds.foxnews.com/foxnews/politics?format=xml",
    "htt:myNewsFeed"
)
private val dispatcher = newFixedThreadPoolContext(2, "IO")
private val factory = DocumentBuilderFactory.newInstance()

private fun asyncFetchRssHeadlines(
    feed: String,
    dispatcher: CoroutineDispatcher
) = GlobalScope.async(dispatcher) {
    val builder = factory.newDocumentBuilder()
    val xml = builder.parse(feed)
    val news = xml.getElementsByTagName("channel").item(0)

    (0 until news.childNodes.length)
        .map { news.childNodes.item(it) }
        .filter { Node.ELEMENT_NODE == it.nodeType }
        .map { it as Element }
        .filter { "item" == it.tagName }
        .map { it.getElementsByTagName("title").item(0).textContent }
}
