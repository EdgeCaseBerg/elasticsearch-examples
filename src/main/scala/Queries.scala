package com.github.edgecaseberg.elasticexamples

object Queries {
	val matchAll =
		"""{
  "query" : {
    "match_all" : { }
  }
}"""
	val matchWord =
		"""{
  "query" : {
    "match" : {
      "_all" : {
        "query" : "word",
        "type" : "boolean"
      }
    }
  }
}"""
	val matchCategories =
		"""{
  "bool" : {
    "must" : [ {
      "term" : {
        "myField" : "test"
      }
    }, {
      "term" : {
        "otherField" : "value"
      }
    } ]
  }
}"""
}