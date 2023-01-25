package day5

class Move {
    int cratesNumber
    int fromStack
    int toStack

    static Move fromString(String moveDefinition) {
        def split = moveDefinition.split(" ")
        def move = new Move()
        move.cratesNumber = split[1] as int
        move.fromStack = split[3] as int
        move.toStack = split[5] as int
        return move
    }
}
