// tag::refactored[]
import static ratpack.groovy.Groovy.ratpack

import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

import ratpack.server.ServerConfigBuilder
import ratpack.groovy.sql.SqlModule
import fortune.Config
import fortune.FortuneModule

ratpack {
  serverConfig { ServerConfigBuilder config -> // <1>
    config
      .yaml("cookies.yml")
      .require("", Map)
  }

  bindings {
    module GraphQLModule
    module SqlModule
    module FortuneModule // <2>
  }

  handlers {
    post('graphql', GraphQLHandler)
    get('graphql/browser', GraphiQLHandler)
  }
}
// end::refactored[]

/**
// tag::withData[]
import static ratpack.groovy.Groovy.ratpack

import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

import graphql.schema.GraphQLSchema
import fortune.SchemaProvider

import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.groovy.sql.SqlModule
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource
import groovy.sql.Sql

ratpack {
  bindings {
        module GraphQLModule
        module SqlModule
        providerType GraphQLSchema, SchemaProvider
        bindInstance DataSource, new JdbcDataSource(
          URL: "jdbc:h2:mem::test;DB_CLOSE_DELAY=-1",
          user: "sa",
          password: ""
        )
        bindInstance new Service() { // <1>
          void onStart(StartEvent e) {
            Sql sql = e.registry.get(Sql) // <2>

            sql.execute '''
            CREATE TABLE cookies  (
              ID INT PRIMARY KEY AUTO_INCREMENT,
              AUTHOR VARCHAR(255),
              TEXT VARCHAR(500)
            )
            '''

            sql.execute '''
            INSERT INTO cookies
              (AUTHOR, TEXT)
            VALUES
              ('Anonymous', 'Dont talk to strangers')
            '''

            sql.execute '''
            INSERT INTO cookies
              (AUTHOR, TEXT)
            VALUES
              ('Anonymous', 'The greatest risk is not taking one')
            '''
          }
        }
  }

  handlers {
    post('graphql', GraphQLHandler)
    get('graphql/browser', GraphiQLHandler)
  }
}
// end::withData[]
 **/

/**
// tag::embeddedDataSource[]
import static ratpack.groovy.Groovy.ratpack

import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

import graphql.schema.GraphQLSchema
import fortune.SchemaProvider

import ratpack.groovy.sql.SqlModule
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

ratpack {
  bindings {
        module GraphQLModule
        module SqlModule // <1>
        providerType GraphQLSchema, SchemaProvider
        bindInstance DataSource, new JdbcDataSource( // <2>
            URL: "jdbc:h2:mem::test;DB_CLOSE_DELAY=-1",
            user: "sa",
            password: ""
        )
  }

  handlers {
    post('graphql', GraphQLHandler)
    get('graphql/browser', GraphiQLHandler)
  }
}
// end::embeddedDataSource[]
**/

/**
// tag::initial[]
import static ratpack.groovy.Groovy.ratpack

import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

import graphql.schema.GraphQLSchema
import fortune.SchemaProvider

ratpack {
  bindings {
        module GraphQLModule // <1>
        providerType GraphQLSchema, SchemaProvider
  }

  handlers {
    post('graphql', GraphQLHandler) // <2>
    get('graphql/browser', GraphiQLHandler) // <3>
  }
}
// end::initial[]
**/
