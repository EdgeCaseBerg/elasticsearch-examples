package com.github.edgecaseberg.elasticexamples

import org.elasticsearch.client.Client
import org.elasticsearch.index.query._

/** Extend this trait and provide a configured client, then mix it into ElasticSearchService */
trait ElasticClient {
	val client: Client
	def close: Unit
}

/** Helper Class to make our service interface a little bit easier */
case class SearchRequest(
	index: String,
	docType: Option[String] = None,
	keywords: Option[String] = None
)

/** Service with methods to perform search requests against an ElasticSearch instance
 *
 *  To instantiate you need to mix in an ElasticClient that provides a Client
 *  to query.
 *  {{{
 *  trait ECImpl { val client = ... }
 *  val elasticSearchService = new ElasticSearchService wih ECImpl
 *  //use elasticSearchService which will query the client configured in ECImpl
 *  }}}
 *
 */
class ElasticSearchService {
	this: ElasticClient =>

	/** Creates a match all request and returns the JSON string for it
	 *
	 *  @param searchRequest The SearchRequest to be used in creating the query
	 *  @return Return's the string JSON for an ES query
	 */
	def matchAll(searchRequest: SearchRequest) = {
		val queryBuilder = client.prepareSearch(searchRequest.index)
		searchRequest.docType.map { docType =>
			queryBuilder.setTypes(docType)
		}
		val matchAll = QueryBuilders.matchAllQuery()
		queryBuilder.setQuery(matchAll)
		queryBuilder.toString
	}

	/** Creates a match keyword request and returns the JSON string for it
	 *
	 *  @param searchRequest The SearchRequest to be used in creating the query
	 *  @return Return's the string JSON for an ES query
	 */
	def matchKeyword(searchRequest: SearchRequest) = {
		val queryBuilder = client.prepareSearch(searchRequest.index)
		searchRequest.docType.map { docType =>
			queryBuilder.setTypes(docType)
		}
		val matchWord = QueryBuilders.matchQuery("_all", searchRequest.keywords.getOrElse("*"))
		queryBuilder.setQuery(matchWord)
		queryBuilder.toString
	}

	/** Creates a query that will only return data that match the given categories
	 *
	 * @param categories A list of string tuples of field names to value to match
	 * @return Return's the string JSON for an ES query
	 */
	def matchCategories(categories: List[Tuple2[String, String]]) = {
		val andFilter = new BoolQueryBuilder()
		categories.map {
			case (field, value) =>
				andFilter.must(new TermQueryBuilder(field, value))
		}
		andFilter.toString
	}
}