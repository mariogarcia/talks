package bond.db

import bond.util.SystemResources
import graphql.schema.DataFetchingEnvironment

class Contest {
  static List<Map> loadWinners(env)  {
    def max = env.arguments.max ?: 5
    def contestants = SystemResources
      .classpathAsURL('/asistentes-mgug.csv')
      .openStream()
      .readLines()

    Collections.shuffle(contestants)

    return contestants
      .collect { [name: it] }
      .take(max)
  }
}
