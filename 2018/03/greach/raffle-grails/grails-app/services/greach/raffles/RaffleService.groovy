package greach.raffles

import grails.gorm.transactions.Transactional

class RaffleService {

    @Transactional
    List<Contestant> generateWinners(Long raffleId, Integer noWinners) {
        Raffle raffle = Raffle.get(raffleId)
        List<Contestant> contestants = Contestant.list()

        Collections.shuffle(contestants)
        List<Contestant> winners = contestants.take(noWinners)

        return winners
    }
}
