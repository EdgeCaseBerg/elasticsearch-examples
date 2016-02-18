package com.github.edgecaseberg.elasticexamples

object Queries {
	val matchAll = """{
  "query" : {
    "match_all" : { }
  }
}"""
	val matchWord = """{
  "query" : {
    "match" : {
      "_all" : {
        "query" : "word",
        "type" : "boolean"
      }
    }
  }
}"""
}