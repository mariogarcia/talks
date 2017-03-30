package gorm

import bond.db.Queries
import greach.Film
import io.cirill.relay.RelayHelpers

class BootStrap {

    def init = { servletContext ->
      Queries.loadDataset().each { Map film ->
        def savedFilm = new Film(title: film.title,
                 year: film.year,
                 bond: film.bond).save(flush:true)

        println RelayHelpers.toGlobalId("Film", savedFilm.id.toString())
      }
    }

    def destroy = {

    }
}
