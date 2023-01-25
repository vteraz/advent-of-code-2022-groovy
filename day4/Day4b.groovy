package day4

def input = new File("./input.txt")
def result = input.readLines()
        .collect {
            def split = it.split(",")
            new Tuple<Range>(new Range(split[0]), new Range(split[1]))
        }
        .findAll {
            it.get(0).overlaps(it.get(1)) || it.get(1).overlaps(it.get(0))
        }
        .size()

println result
assert result == 770
