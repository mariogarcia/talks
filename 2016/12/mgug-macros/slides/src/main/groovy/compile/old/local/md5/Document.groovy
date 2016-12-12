package compile.old.local.md5

@MD5
class Document {

    File file
    String name

    void printMD5() {
        println "===>${nameAsMD5()}"
        println "===>${fileAsMD5()}"
    }
}
