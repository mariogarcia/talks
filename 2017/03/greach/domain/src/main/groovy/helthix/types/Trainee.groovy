package helthix.types

import gql.Field
import gql.GraphQL

/**
 * A person who may train under the supervision of a {@link Trainer}
 *
 * @since 0.1.0
 */
@GraphQL(desc = 'Works under the supervision of a Trainer')
class Trainee  {

  /**
   * Person name
   *
   * @since 0.1.0
   */
  @Field(desc = 'Person\'s name')
  String name

  /**
   * The diet the trainee is following (if any)
   *
   * @since 0.1.0
   */
  @Field(desc = 'Trainee\'s diet')
  Diet diet

  /**
   * The trainer supervising the trainee
   *
   * @since 0.1.0
   */
  @Field(desc = 'Trainee\'s trainer')
  Trainer trainer
}
