package madridgug.parrot

class Loops {

    // tag::multi_assign[]
    List<String> returnValues() {
        List<String> values = []
        for (def (String u, Integer v) = ['bar', 42]; v < 45; u++, v++) {
            values << "$u $v"
        }
        return values
    }
    // end::multi_assign[]
}
