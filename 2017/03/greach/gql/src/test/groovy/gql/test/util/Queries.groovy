package gql.test.util

import groovy.json.JsonSlurper
import graphql.schema.DataFetchingEnvironment

class Queries {

  @SuppressWarnings('UnusedMethodParameter')
  static Map<String,Object> findLastFilm(DataFetchingEnvironment env) {
    def datasetPath = SystemResources.classpath('data/bond.json')
    def datasetData = new JsonSlurper().parse(datasetPath.toFile()) as List<Map>
    def lastFilm = datasetData.last()

    return lastFilm
  }
}
