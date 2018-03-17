package greach.raffles

class BootStrap {

    static final List NAMES = [
        ['John', 'Peter', 'Paul'],
        ['Harris', 'Scott', 'James']
    ]

    def init = { servletContext ->
        Raffle raffle = new Raffle(title: 'T-Shirt').save()

        NAMES
            .combinations()
            .collect { list -> [name: "${list.first()} ${list.last()}"] }
            .each { next ->
              Contestant contestant =
                new Contestant(name: next.name).save()
            }
    }

    def destroy = {
    }
}
