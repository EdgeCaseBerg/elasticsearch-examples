package com.github.edgecaseberg.elasticexamples

import org.elasticsearch.client._
import org.elasticsearch.client.transport._
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport._
import java.net.{ InetAddress, InetSocketAddress }

/** This is to illustrate how you can construct a Transport Client in ES 2.2.0
 *
 *  You can mix this trait into other classes to provide a client to ES
 */
trait TcpClient extends ElasticClient {
	private val settings: Settings = Settings.settingsBuilder()
		.put("cluster.name", "elasticsearch")
		.build();

	val client = new TransportClient.Builder().settings(settings).build()
		.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
}