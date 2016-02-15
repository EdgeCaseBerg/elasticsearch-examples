package com.github.edgecaseberg.elasticexamples

import org.scalatest._

class ElasticSearchServiceTest extends ElasticTest {

	"The ElasticSearchService" should "create match all queries" in {
		val query = elasticSearchService.matchAll(SearchRequest("test"))
		assertResult(Queries.matchAll)(query)
	}
}
