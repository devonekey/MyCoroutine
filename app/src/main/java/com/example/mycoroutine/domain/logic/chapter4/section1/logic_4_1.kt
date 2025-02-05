package com.example.mycoroutine.domain.logic.chapter4.section1

import com.example.mycoroutine.domain.model.Article
import com.example.mycoroutine.domain.model.Feed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.newFixedThreadPoolContext
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

private val feeds = listOf(
    Feed("npr", "https://www.npr.org/rss/rss.php?id=1001"),
    Feed("cnn", "http://rss.cnn.com/rss/cnn_topstories.rss"),
    Feed("fox", "http://feeds.foxnews.com/foxnews/politics?format=xml"),
    Feed("inv", "htt:myNewsFeed")
)
private val dispatcher = newFixedThreadPoolContext(2, "IO")
private val factory = DocumentBuilderFactory.newInstance()

fun logic_4_1(requests: MutableList<Deferred<List<Article>>>) = feeds.mapTo(requests) {
    asyncFetchArticles(it, dispatcher)
}

private fun asyncFetchArticles(
    feed: Feed,
    dispatcher: CoroutineDispatcher
) = GlobalScope.async(dispatcher) {
    val builder = factory.newDocumentBuilder()
    val xml = builder.parse(feed.url)
    val news = xml.getElementsByTagName("channel").item(0)

    (0 until news.childNodes.length)
        .map { news.childNodes.item(it) }
        .filter { Node.ELEMENT_NODE == it.nodeType }
        .map { it as Element }
        .filter { "item" == it.tagName }
        .map {
            val title = it.getElementsByTagName("title").item(0).textContent
            val summary = it.getElementsByTagName("description").item(0).textContent

            Article(feed.name, title, summary)
        }
}

