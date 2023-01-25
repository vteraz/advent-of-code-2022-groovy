package day5

def input = new File("./input.txt").text


def split = input.split("\r\n\r\n")
def crates = split[0].readLines().reverse()
def movesLines = split[1].readLines()
def cratesStacks = [:] as Map<Integer, Deque<String>>

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
                    cratesStacks.put(index, new ArrayDeque<String>())
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
            def cratesToBeMoved = []
            for (i in 0..<move.cratesNumber) {
                cratesToBeMoved.add(from.poll())
            }
            cratesToBeMoved.reverse().each {
                to.push(it as String)
            }
        }
def result = cratesStacks.values().collect() { it.pop() }.join()
assert result == "MHQTLJRLB"


