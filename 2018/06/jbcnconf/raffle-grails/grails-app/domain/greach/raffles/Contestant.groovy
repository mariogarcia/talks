package greach.raffles

class Contestant {
    static graphql = true // <---- Hello GraphQL

    static belongsTo = [Raffle]

    String name

    static constraints = {
        name nullable: false
    }
}
