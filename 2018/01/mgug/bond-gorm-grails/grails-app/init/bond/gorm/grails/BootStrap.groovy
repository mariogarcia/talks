package bond.gorm.grails

import bond.Film
import bond.db.Queries

class BootStrap {

  def init = { servletContext ->
    Queries
      .loadDataset()
      .collect { new Film(it) }
      .each { it.save() }
  }

  def destroy = {
  }
}
