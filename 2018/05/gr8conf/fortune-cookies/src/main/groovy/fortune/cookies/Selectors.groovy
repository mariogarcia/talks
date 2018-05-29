package fortune.cookies

import graphql.schema.DataFetchingEnvironment

/**
 * Functions in this class extract parameters to be used in {@link CookiesServiceImpl} functions
 *
 * @since 0.1.0
 */
class Selectors {

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

    static class CreateCookieParams {
        String author
        String text
    }

    static CreateCookieParams createCookieParams(DataFetchingEnvironment env) {
        return new CreateCookieParams(
            author: env.arguments.author,
            text: env.arguments.text
        )
    }
}
