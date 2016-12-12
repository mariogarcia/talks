package runtime

class PlainMetaClass {

    String withStringUtils(Closure<String> execution) {
        // STORING ORIGINAL METACLASS
        MetaClass sourceMetaClass = String.metaClass

        // ADDING METHOD
        String.metaClass.asMD5 = { StringUtils.encodeAsMD5(delegate) }

        // EXECUTING FUNCTION
        String result = execution.doCall()

        // RESTORING STRING
        String.metaClass = sourceMetaClass

        // RETURNING RESULT
        return result
    }
}
