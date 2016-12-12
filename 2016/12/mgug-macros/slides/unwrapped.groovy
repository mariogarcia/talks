import compile.old.global.*

@MicroService
class Greetings {
    String 'GET/raffle'() {
        String winner = csv('/home/mario/Downloads/mgug.csv',['time', 'name'])
          .shuffle()
          .find()
          .name
    
        return toJson(winner: winner)
    }
}

new Greetings().main()