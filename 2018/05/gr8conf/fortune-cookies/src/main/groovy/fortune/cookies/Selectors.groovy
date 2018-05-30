package fortune.cookies

import graphql.schema.DataFetchingEnvironment

/**
 * Functions in this class extract parameters to be used in {@link CookiesServiceImpl} functions
 *
 * @since 0.1.0
 */
class Selectors {

    /**
     * This type maps params to list cookies
     *
     * @since 0.1.0
     */
    static class ListCookiesParams {
        Integer offset
        Integer max
    }

    /**
     * Extracts parameters to list fortune cookies
     *
     * @param env an instance of {@link DataFetchingEnvironment}
     * @return a map with required params
     * @since 0.1.0
     */
    static ListCookiesParams listCookiesParams(DataFetchingEnvironment env) {
        return new ListCookiesParams(offset: env.arguments.offset, max: env.arguments.max)
    }

    /**
     * This type maps params to create a new cookie
     *
     * @since 0.1.0
     */
    static class CreateCookieParams {
        String author
        String text
    }

    /**
     * Extracts parameters to create a new fortune cookie
     *
     * @param env an instance of {@link DataFetchingEnvironment}
     * @return a map with required params
     * @since 0.1.0
     */
    static CreateCookieParams createCookieParams(DataFetchingEnvironment env) {
        Map<String,?> cookie = env.arguments.cookie

        return new CreateCookieParams(
            author: cookie.author,
            text: cookie.text
        )
    }
}
