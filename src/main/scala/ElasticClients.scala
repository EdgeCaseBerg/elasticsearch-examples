package com.github.edgecaseberg.elasticexamples

import org.elasticsearch.client._
import org.elasticsearch.client.transport._
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport._
import java.net.{ InetAddress, InetSocketAddress }

/** This is to illustrate how you can construct a Transport Client in ES 2.2.0
 *
 *  You can mix this trait into other classes to provide a client to ES
 *
 *  @note it is the responsibility of the call to call `close` on the client
 */
trait TcpClient extends ElasticClient {
	private val settings: Settings = Settings.settingsBuilder()
		.put("cluster.name", "elasticsearch")
		.build();

	val client: Client = new TransportClient.Builder().settings(settings).build()
		.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
}

import org.elasticsearch.node._
import org.elasticsearch.node.NodeBuilder._

/** This is to illustrate how you can construct a Nodeclient in ES 2.2.0
 *
 *  You can mix this trait into other classes to provide a client to ES
 *
 *  @note it is the responsibility of the caller to node.close
 */
trait NodeClient extends ElasticClient {
	val node: Node = nodeBuilder()
		.clusterName("elasticsearch")
		.client(true)
		.node();
	val client: Client = node.client();
}

/** This is to illustrate how to start a local node and supply a client
 *
 *  You can mix this trait into other classes to provide a client to the
 *  locally running node created by this trait.
 *
 *  @note it is the responsibility of the caller to node.close
 */
trait LocalNodeClient extends ElasticClient {
	val node: Node = nodeBuilder()
		.clusterName("elasticsearch")
		.settings(Settings.settingsBuilder().put("http.enabled", false))
		.local(true)
		.node();
	val client: Client = node.client();
}