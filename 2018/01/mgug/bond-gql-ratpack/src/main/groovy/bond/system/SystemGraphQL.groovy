package bond.system

import gql.DSL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLFieldDefinition

import javax.inject.Inject

/**
 * Defines all queries for fetching system information
 *
 * @since 0.1.0
 */
class SystemGraphQL {

  /**
   * Service accessing system information
   *
   * @since 0.1.0
   */
  @Inject
  SystemService systemService

  /**
   * Defines current system status query
   *
   * @return an instance of {@link GraphQLFieldDefinition}
   * @since 0.1.0
   */
  GraphQLFieldDefinition getSystemStatus() {
    return DSL.field('status') {
      description 'shows system current status'

      type Types.GraphQLSystemHealth
      fetcher { DataFetchingEnvironment env ->
        return [
          os: systemService.systemOS,
          version: systemService.systemVersion,
          totalMemory: systemService.totalMemory,
          availableMemory: systemService.availableMemory,
          usedMemory: systemService.usedMemory,
        ]
      }
    }
  }
}
