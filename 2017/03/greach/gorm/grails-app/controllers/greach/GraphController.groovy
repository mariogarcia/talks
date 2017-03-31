package greach

import grails.converters.JSON

/**
 * Example of Grails 3,2,X controller exposing a GraphQL endpoint
 *
 * @since 0.1.0
 */
class GraphController {

    def relayService

    def index() {
      String query = request.JSON.query.toString()
	    Map vars = request.JSON.variables
	    def result = relayService.query(query, null, vars?:[:])

	    render(result as JSON)
    }
}
