package helthix.relayr

import helthix.utils.SystemResources
import groovy.json.JsonSlurper
import graphql.schema.DataFetchingEnvironment

/**
 * @since 0.1.0
 */
class Queries {

  /**
   * @param env
   * @return
   * @since 0.1.0
   */
  static Map<String,Object> findLastFilm(DataFetchingEnvironment env) {
    def datasetURL = SystemResources.classpathAsURL('/data/bond.json')
    def inputStream = datasetURL.openStream()
    def datasetData = new JsonSlurper().parse(inputStream) as List<Map>
    def lastFilm = datasetData.last()

    return lastFilm
  }
}
