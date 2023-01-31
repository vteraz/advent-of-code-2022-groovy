package day7

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@TupleConstructor
@ToString(includes = "name")
class File {
    String name
    long size
}
