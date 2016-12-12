package runtime

class PlainMetaClassTests extends GroovyTestCase {

    void testMetaClass() {
        PlainMetaClass instance = new PlainMetaClass()
        String md5 = instance.withStringUtils {
            return "amigo".asMD5()
        }

        // It was available for the scope of the closure
        assert md5 == "d94729ce13f4ee6395bfc6f1080cc986"

        // But it's not available outside
        assert 'anotherString'.metaClass.respondsTo('another', 'asMD5').size() == 0
    }

    void testMethodMissing() {
        StringX sst = new StringX("amigo")

        sst.with {
            assert encodeAsMD5()  == "d94729ce13f4ee6395bfc6f1080cc986"
            assert encodeAsSHA1() == "01f07beb8567e3a651dcf08ce9a403dc03aaf2dc"
        }
    }
}
