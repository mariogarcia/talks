package greach

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import io.cirill.relay.RelayService
import io.cirill.relay.RelayHelpers

@TestMixin(GrailsUnitTestMixin)
@TestFor(RelayService)
@Mock([Film])
class FilmSpec extends Specification {
    void "test film"() {
	    when:
	    def film = new Film(title: "DR. NO", year: 1962)
	    film.save(flush: true)

	    def idString = RelayHelpers.toGlobalId("Film", film.id.toString())
	    def query = """
	        query {
	            node(id: "$idString") {
	                ... on Film {
	                    title
	                }
	            }
	        }
	    """
	    def result = service.query(query, null, [:])

      then:
      result.data.node.title == film.title
      /* result.data is an object representing the resolved query:
       * "node": {
       *     "name": "Ralph"
       * }
       */
    }
}
