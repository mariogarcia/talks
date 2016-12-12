package compile.extension

class ListExtension {

    static <T> List<T> shuffle(List<T> self) {
        Collections.shuffle(self)
        return self
    }
}
