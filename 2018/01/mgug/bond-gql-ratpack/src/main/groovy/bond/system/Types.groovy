package bond.system

import gql.DSL
import graphql.schema.GraphQLObjectType

/**
 * Defines all types related to system services information
 *
 * @since 0.1.0
 */
class Types {

  //             _             _     _
  //    ___ _  _| |_ _ __ _  _| |_  | |_ _  _ _ __  ___ ___
  //   / _ \ || |  _| '_ \ || |  _| |  _| || | '_ \/ -_|_-<
  //   \___/\_,_|\__| .__/\_,_|\__|  \__|\_, | .__/\___/__/
  //                |_|                  |__/|_|

  static final GraphQLObjectType GraphQLSystemHealth = DSL.type('SystemHealth') {
    description 'Gives vital information about the actual state of the system'

    field 'version', GraphQLString
    field 'os', GraphQLString
    field 'totalMemory', GraphQLBigInteger
    field 'availableMemory', GraphQLBigInteger
    field 'usedMemory', GraphQLBigInteger
  }
}
