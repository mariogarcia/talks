package generators;

import java.util.Objects;
import java.util.stream.Stream;

public interface SimpleProperty {

    //tag::sum[]
    public static Integer SUM(Integer... values) {
        return Stream
            .of(values)
            .filter(Objects::nonNull)
            .reduce((a,b) -> a + b)
            .orElse(0);
    }
    //end::sum[]
}
