package greach

import grails.converters.JSON

class GraphController {

    def relayService

    def index() {
      String query = request.JSON.query.toString()
	    Map vars = request.JSON.variables
	    def result = relayService.query(query, null, vars?:[:])

	    render(result as JSON)
    }
}
