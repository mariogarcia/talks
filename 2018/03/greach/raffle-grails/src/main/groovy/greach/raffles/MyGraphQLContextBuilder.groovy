package greach.raffles

import org.grails.gorm.graphql.plugin.DefaultGraphQLContextBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.grails.web.servlet.mvc.GrailsWebRequest

class MyGraphQLContextBuilder extends DefaultGraphQLContextBuilder {

    @Autowired
    RaffleService raffleService

    @Override
    Map buildContext(GrailsWebRequest request) {
        Map context = super.buildContext(request)
        context.raffleService = raffleService
        context
    }
}
