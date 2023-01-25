package day3

def input = new File("./input.txt")
def alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
def result = 0

input.eachLine {
    def compartmentLength = it.length() / 2 as int
    def compartment1 = it.substring(0, compartmentLength)
    def compartment2 = it.substring(compartmentLength, it.length())

    for (i in 0..<compartmentLength) {
        def charAt = compartment1.charAt(i)
        if (compartment2.indexOf(charAt.hashCode()) > -1) {
            def priority = alphabet.indexOf(charAt as int) + 1
            result += priority
            break
        }
    }
}

println result
assert result == 8018
