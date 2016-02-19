package com.github.edgecaseberg.elasticexamples

import org.scalatest._

class ElasticSearchServiceTest extends ElasticTest {

	"The ElasticSearchService" should "create match all queries" in {
		val query = elasticSearchService.matchAll(SearchRequest("test"))
		assertResult(Queries.matchAll)(query)
	}

	it should "create match keyword queries" in {
		val query = elasticSearchService.matchKeyword(SearchRequest("test", keywords = Some("word")))
		assertResult(Queries.matchWord)(query)
	}

	it should "create a filtered category query" in {
		val query = elasticSearchService.matchCategories(List(("myField", "test"), ("otherField", "value")))
		assertResult(Queries.matchCategories)(query)
	}

}
