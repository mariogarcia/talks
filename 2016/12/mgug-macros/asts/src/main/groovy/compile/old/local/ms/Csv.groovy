package compile.old.local.ms

/**
 * @since 0.1.0
 */
trait Csv {

    /**
     * Parses a CSV file separated with commas and returns a map per
     * line
     *
     * @param path where the csv file is (absolute path)
     * @return a list with the entries of that file
     * @since 0.1.0
     */
    static List<Map> csv(String path, List<String> headers) {
        new File(path)
          .readLines()
          .collect { String line ->
              return [headers, line.split(',')]
                .transpose()
                .collectEntries { [(it.first()): it.last()] }
          }
    }
}
