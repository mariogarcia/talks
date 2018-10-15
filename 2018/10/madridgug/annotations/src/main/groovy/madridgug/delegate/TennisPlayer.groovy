package madridgug.delegate

class TennisPlayer {
    List<Map> wins
    String name

    @Delegate
    Integer getWinSize() {
        return wins.size()
    }
}
