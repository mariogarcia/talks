package generators;

import java.util.stream.Stream;

public interface SimpleProperty {

    //tag::sum[]
    public static Integer SUM(Integer... values) {
        return Stream
            .of(values)
            .reduce((a,b) -> a + b)
            .orElse(0);
    }
    //end::sum[]
}
