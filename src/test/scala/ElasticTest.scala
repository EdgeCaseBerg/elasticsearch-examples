package com.github.edgecaseberg.elasticexamples

import org.scalatest.{ FlatSpec, BeforeAndAfter }

trait ElasticTest extends FlatSpec with BeforeAndAfter {

	/** SearchService with Local Running Node for testing purposes */
	lazy val elasticSearchService = new ElasticSearchService with LocalNodeClient

	before {
		elasticSearchService.client //Bother the lazy value so it loads
		//load any data... (TODO)
	}

	after {
		elasticSearchService.close
	}
}
