package madridgug.macros.method

class SafeCalls {
    @MakeParamSafe
    static Integer inc(Integer a) {
        // if (!a) { return 1 }
        return a + 1
    }
}
