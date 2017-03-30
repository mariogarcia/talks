package bond.db

import bond.util.SystemResources
import groovy.json.JsonSlurper
import graphql.schema.DataFetchingEnvironment

/**
 * @since 0.1.0
 */
class Queries {

  static List<Map> loadDataset()  {
    def datasetURL = SystemResources.classpathAsURL('/data/bond.json')
    def inputStream = datasetURL.openStream()

    return new JsonSlurper().parse(inputStream) as List<Map>
  }

  /**
   * @param env
   * @return
   * @since 0.1.0
   */
  static Map<String,Object> findLastFilm(DataFetchingEnvironment env) {
    return loadDataset().last()
  }

  /**
   * @param env
   * @return
   * @since 0.1.0
   */
  static Map<String,Object> findByYear(DataFetchingEnvironment env) {
    def year = "${env.arguments.year}"

    return loadDataset().find(byYear(year))
  }

  static Closure<Boolean> byYear(String year) {
    return { Map<String,Object> m -> m.year == year } as Closure<Boolean>
  }

  /**
   * @param env
   * @return
   * @since 0.1.0
   */
  static List<Map<String,Object>> byBondActorNameLike(DataFetchingEnvironment env) {
    def name = env.arguments.name

    return loadDataset().findAll(contains("$name"))
  }

  static Closure<Boolean> contains(String name) {
    return { Map<String,Object> m -> "${m.bond}".contains(name) } as Closure<Boolean>
  }
}
