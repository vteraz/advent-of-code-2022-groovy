package day4

class Range {
    int start
    int finish

    Range(String row) {
        def split = row.split("-")
        start = split[0] as int
        finish = split[1] as int
    }

    boolean contains(Range anotherRange) {
        start <= anotherRange.start && finish >= anotherRange.finish
    }

    boolean overlaps(Range anotherRange) {
        anotherRange.start >= start && anotherRange.start <= finish
    }
}
