package day5

def input = new File("./input.txt").text


def split = input.split("\r\n\r\n")
def crates = split[0].readLines().reverse()
def movesLines = split[1].readLines()
def cratesStacks = [:] as Map<Integer, Stack<String>>

crates.subList(1, crates.size()).each { line ->
    def lineSplit = line.split("(?<=\\G.{4})")
    lineSplit
            .collect {
                it.trim()
                        .replaceAll("\\[", "")
                        .replaceAll("]", "")
            }
            .eachWithIndex { crate, index ->
                if (!cratesStacks.containsKey(index)) {
                    cratesStacks.put(index, new Stack<String>())
                }
                def currentStack = cratesStacks.get(index)
                if (!crate.isEmpty()) {
                    currentStack.push(crate)
                }
            }
}

movesLines.collect { Move.fromString(it) }
        .each { move ->
            def from = cratesStacks.get(move.fromStack - 1)
            def to = cratesStacks.get(move.toStack - 1)
            for (i in 0..<move.cratesNumber) {
                to.push(from.pop())
            }
        }
def result = cratesStacks.values().collect() { it.pop() }.join()
assert result == "RLFNRTNFB"
