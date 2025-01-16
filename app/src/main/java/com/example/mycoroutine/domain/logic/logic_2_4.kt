package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

private val TAG: String = "Logic_2_4"
private val dispatcher = newSingleThreadContext(name = "ServiceCall")
private val factory = DocumentBuilderFactory.newInstance()

fun logic_2_4() = runBlocking {
    GlobalScope.async(dispatcher) {
        val headlines = fetchRssHeadlines()

        headlines.forEach { Log.d(TAG, it) }
        headlines.count()
    }
}

private fun fetchRssHeadlines(): List<String> {
    val builder = factory.newDocumentBuilder()
    val xml = builder.parse("https://www.npr.org/rss/rss.php?id=1001")
    val news = xml.getElementsByTagName("channel").item(0)

    return (0 until news.childNodes.length)
        .map { news.childNodes.item(it) }
        .filter { Node.ELEMENT_NODE == it.nodeType }
        .map { it as Element }
        .filter { "item" == it.tagName }
        .map { it.getElementsByTagName("title").item(0).textContent }
}
