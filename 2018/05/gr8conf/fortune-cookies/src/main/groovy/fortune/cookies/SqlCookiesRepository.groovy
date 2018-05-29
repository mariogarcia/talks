package fortune.cookies

import javax.inject.Inject
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j

/**
 * Database access to cookies
 *
 * @since 0.1.0
 */
@Slf4j
class SqlCookiesRepository implements CookiesRepository {

    /**
     * Database connection
     *
     * @since 0.1.0
     */
    @Inject
    Sql sql

    /**
     * Counts how many cookies are in the database
     * @return
     * @since 0.1.0
     */
    Integer count() {
        log.debug('count cookies from database')

        return sql
            .firstRow('SELECT count(1) as count FROM cookies')
            .get('count') as Integer
    }

    /**
     * Gets a random cookie given a random number for the offset
     *
     * @param offset random number to pick a cookie located at that offset
     * @return a cookie
     * @since 0.1.0
     */
    Map getCookieAt(Integer offset) {
        log.debug('get random cookie from database')

        return sql.firstRow("SELECT * FROM cookies OFFSET :offset", offset: offset)
    }

    /**
     * List cookies given an offset and a maximum number of cookies
     *
     * @param params an instance of {@link Selectors.ListCookiesParams}
     * @return a list of cookies
     * @since 0.1.0
     */
    List<Map> list(Selectors.ListCookiesParams params) {
        log.debug('list cookies from database')

        sql.rows('SELECT * from cookies', params.offset, params.max)
    }

    /**
     * Creates a new cookie
     *
     * @param params all required params to create a new cookie
     * @return a map containing the new cookie
     * @since 0.1.0
     */
    Map create(Selectors.CreateCookieParams params) {
        log.debug('create new cookie')

        List<String> keyColumns = ['ID', 'AUTHOR', 'TEXT']
        List<GroovyRowResult> results = sql.executeInsert(
            'INSERT INTO cookies (AUTHOR, TEXT) VALUES (:author, :text)',
            keyColumns,
            author: params.author,
            text: params.text
        )

        return results.find()
    }
}
