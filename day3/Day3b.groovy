package day3

def input = new File("./input.txt")
def result = [0]
def groupSize = 3
def group = [] as ArrayList<String>

input.eachLine {
    group.add(it)
    if (group.size() == groupSize) {
        def groupRucksacks = group.collect { it.chars() as List<Character> }
                .sort { it.size() }

        println groupRucksacks.get(0).size()
        println groupRucksacks.get(1).size()
        println groupRucksacks.get(2).size()

        for (final def i in groupRucksacks.get(0)) {
            if (groupRucksacks.subList(1, groupSize).every { it.contains(i) }) {
                result.add(getPriority(i))
                break
            }
        }
        group = []
    }
}

static int getPriority(int it) {
    if (it < 91) {
        return it - 64 + 26
    } else {
        return it - 96
    }
}

println result.sum()
assert result.sum() == 2518
