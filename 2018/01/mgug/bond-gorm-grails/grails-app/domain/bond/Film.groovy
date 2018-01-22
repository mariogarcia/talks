package bond

// tag::Film[]
class Film {

  static graphql = true // exposed via GraphQL

  String title
  Integer year

  static constraints = {
    year nullable: false // GraphQL validation
  }
}
// end::Film[]

/*
// tag::lastFilm[]
import org.grails.gorm.graphql.entity.dsl.GraphQLMapping

class Film {
  static graphql = GraphQLMapping.build {
    query('lastFilm', Film) {
      dataFetcher { env ->
        Film.last(sort: "year")
      }
    }
  }

  String title
  Integer year

  static constraints = {
    year nullable: false
  }
}
// end::lastFilm[]
*/
