package helthix.types

import gql.GraphQL

/**
 * A diet could be done by anyone
 *
 * @since 0.1.0
 */
@GraphQL(desc = 'A person could follow a diet')
class Diet {

  /**
   * Diet title. It describes the type of diet.
   *
   * @since 0.1.0
   */
  String title
}
