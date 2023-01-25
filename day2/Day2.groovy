package day2

interface Shape {
    int score()

    Class<Shape> defeats()

    Class<Shape> loses()
}

class Rock implements Shape {
    @Override
    int score() {
        return 1
    }

    @Override
    Class<Shape> defeats() {
        Scissors.class
    }

    @Override
    Class<Shape> loses() {
        Paper.class
    }
}

class Scissors implements Shape {
    @Override
    int score() {
        return 3
    }

    @Override
    Class<Shape> defeats() {
        Paper.class
    }

    @Override
    Class<Shape> loses() {
        Rock.class
    }
}

class Paper implements Shape {
    @Override
    int score() {
        return 2
    }

    @Override
    Class<Shape> defeats() {
        Rock.class
    }

    @Override
    Class<Shape> loses() {
        Scissors.class
    }
}

static Shape player1ShapeFactory(String id) {
    def result
    switch (id) {
        case ["A"]:
            result = new Rock()
            break
        case ["B"]:
            result = new Paper()
            break
        case ["C"]:
            result = new Scissors()
            break
        default: throw new RuntimeException(id)
    }
    result
}

static Shape player2ShapeFactory(String id, Shape opponent) {
    def result
    switch (id) {
        case ["X"]:
            result = opponent.defeats().getConstructor().newInstance()
            break
        case ["Y"]:
            result = opponent
            break
        case ["Z"]:
            result = opponent.loses().getConstructor().newInstance()
            break
        default: throw new RuntimeException(id)
    }
    result
}

def input = new File("./input.txt")

def player1Score = 0
def player2Score = 0

input.eachLine {
    def split = it.split(" ")
    def player1 = player1ShapeFactory(split[0])
    def player2 = player2ShapeFactory(split[1], player1)

    player1Score += player1.score()
    player2Score += player2.score()

    if (player1.defeats() == player2.class) {
        player1Score += 6
    } else if (player1.loses() == player2.class) {
        player2Score += 6
    } else {
        player1Score += 3
        player2Score += 3
    }
}

println player2Score

assert player2Score == 13600