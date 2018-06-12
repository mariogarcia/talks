package greach.raffles

import static org.grails.gorm.graphql.entity.dsl.GraphQLMapping.build

class Raffle {
    static hasMany = [winners: Contestant]
    // tag::custom[]
    static graphql = build {
        mutation('generateWinners', [Contestant]) {

            argument('id', Long) // <---- ARGUMENTS
            argument('noWinners', Integer)

            dataFetcher { env ->  // <---- DATA FETCHER
                return env
                    .context
                    .raffleService
                    .generateWinners(env.arguments.id,
                                     env.arguments.noWinners)
            }
        }
    }
    // end::custom[]

    String title

    static constraints = {
        title nullable: false
    }
}
