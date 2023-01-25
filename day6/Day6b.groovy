package day6

def input = new File("./input.txt").text
def windowSize = 14
def window = new HashSet<String>()

for (i in 0..<input.size() - windowSize + 1) {
    input.substring(i, Math.min(input.size(), windowSize + i)).each { window.add(it) }
    if (window.size() == windowSize) {
        assert 1912 == i + windowSize
        break
    } else {
        window = new HashSet<String>()
    }
}


