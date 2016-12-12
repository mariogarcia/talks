package compile.old.local.ms

import groovy.transform.CompileStatic

/**
 * Service to raffle anything. You only need a csv file with the
 * contestants and... good luck!!!
 *
 * @since 0.1.0
 */
@MicroService
@CompileStatic
class RaffleService {

    /**
     * This endpoint reads a csv file and returns a radonmly chosen
     * winner
     *
     * @return a JSON string with the name of the winner
     * @since 0.1.0
     */
    String 'GET/members/random'() {
        String winner =
            csv('/home/mario/Downloads/mgug.csv', ['date', 'name'])
              .shuffle()
              .find()
              .name

        return toJson(winner: winner)
    }
}
