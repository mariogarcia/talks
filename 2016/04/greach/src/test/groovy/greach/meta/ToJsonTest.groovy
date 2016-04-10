package greach.meta

class ToJsonTest extends GroovyTestCase {

    void testToJson() {
        assertScript '''
        package greach.meta

        import groovy.json.JsonSlurper

        @ToJson
        class User {
            Long id
            String name
            String username
        }

        User john = new User(
            name: "john",
            username: "john23",
            id: 289)

        String json = john.toJson()
        Map map     = new JsonSlurper().parseText(json)

        map.id       == john.id
        map.name     == john.name
        map.username == john.username
        '''
    }
}
