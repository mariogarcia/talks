package helthix.types

import gql.GraphQL

/**
 * A trainer supervises a trainee or a set of trainees
 *
 * @since 0.1.0
 */
@GraphQL(desc = 'A trainer supervises a trainee or a set of trainees')
class Trainer {

  /**
   * Person name
   *
   * @since 0.1.0
   */
  String name

  /**
   * List of trainees under the trainer supervision
   *
   * @since 0.1.0
   */
  List<Trainee> trainees
}
