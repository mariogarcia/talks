package madridgug.parrot

import static java.util.stream.Collectors.toList

import java.util.stream.Stream
import java.util.function.Function
import groovy.transform.CompileStatic

class Functions {

    // tag::lambdas_others[]
    // Sin parentesis
    static Function<Integer, Integer> inc = x -> x + 1
    // Con valor por defecto (No existe en Java)
    static Function<Integer, Integer> dob = (x = 0) -> x * 2
    // Con parentesis
    static Function<Integer, Integer> tri = (Integer x) -> x * 3
    // Con llaves
    static Function<Integer, Integer> com = (Integer x) -> {
        x + 4
    }
    // end::lambdas_others[]

    static Integer x2(Integer x) {
        return x * 2
    }

    // @CompileStatic
    // tag::lambdas_only[]
    static Integer applyLambdas() {
        return Stream
            .of(1, 2, 3)
            .filter(y -> y % 2 == 0)
            .map(y -> y + 1)
            .mapToInt(Integer::intValue)
            .sum()
    }
    // end::lambdas_only[]

    // @CompileStatic
    // tag::lambdas_only2[]
    static Integer applyLambdas2() {
        return Stream
            .of(1, 2, 3)
            .reduce((Integer acc, Integer val) -> {
              return acc + val
        }).orElse(1)
    }
    // end::lambdas_only2[]

    // @CompileStatic
    // tag::both[]
    static Integer applyBoth() {
        return Stream
            .of(1, 2, 3)
            .filter { y -> y % 2 == 0 } // closure syntax
            .map( y -> y + 1 ) // lambda syntax
            .mapToInt(Integer::intValue) // method ref
            .sum()
    }
    // end::both[]

    // tag::method_references[]
    @CompileStatic
    static Integer applyMethodReferences(Integer x) {
        return Optional
            .ofNullable(x)
            .map(Functions::x2)
            .map(inc)
            .orElse(1)
    }
    // end::method_references[]
}
