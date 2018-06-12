package fortune.cookies

interface CookiesRepository {
    /**
     * Counts how many cookies are in the database
     * @return
     * @since 0.1.0
     */
    Integer count()

    /**
     * Gets a random cookie given a random number for the offset
     *
     * @param offset random number to pick a cookie located at that offset
     * @return a cookie
     * @since 0.1.0
     */
    Map getCookieAt(Integer offset)

    /**
     * List cookies given an offset and a maximum number of cookies
     *
     * @param params an instance of {@link Selectors.ListCookiesParams}
     * @return a list of cookies
     * @since 0.1.0
     */
    List<Map> list(Selectors.ListCookiesParams params)

    /**
     * Creates a new cookie
     *
     * @param params all required params to create a new cookie
     * @return a map containing the new cookie
     * @since 0.1.0
     */
    Map create(Selectors.CreateCookieParams params)
}

