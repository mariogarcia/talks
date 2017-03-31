package greach

import grails.converters.JSON

// tag::controller[]
class GraphController {

    def relayService // provided by plugin

    def index() {
      def query = request.JSON.query.toString()
      def vars = request.JSON.variables
      def result = relayService.query(query, null, vars?:[:])

	    render(result as JSON)
    }
}
// end::controller[]
