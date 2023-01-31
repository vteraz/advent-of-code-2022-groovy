package day7

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = ["name", "previousDir"])
@ToString(includes = ["name"])
class Dir {
    String name
    Dir previousDir
    Set<Dir> subDirectories = []
    Set<File> files = []

    Dir(String name) {
        this.name = name
    }

    long getTotalSize() {
        def result = 0L

        result += files.sum(0) { it.size }

        if (!subDirectories.isEmpty()) {
            subDirectories.each {
                result += it.getTotalSize()
            }
        }
        result
    }
}
