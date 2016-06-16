package feedback;

import static helios.Validators.inList;
import static helios.Validators.required;
import static helios.Validators.minOfString;
import static helios.Helios.validate;

import java.util.List;
import java.util.stream.Stream;

import helios.Helios;
import helios.ValidatorError;

public class ValidationService {

    public static class Person {
        public String name;
        public Integer age;
        public String address;

        @Override
        public String toString() {
            return "Person(" + name + "," + age + "," + address + "";
        }
    }

    // tag::validatePerson[]
    public List<ValidatorError> validatePerson(final Person person) {
        return validate("person", person,
            (Person p) -> validate("name", p.name, required(), minOfString(50)),
            (Person p) -> validate("age", p.age, required()));
    }
    // end::validatePerson[]

    public static Integer SUM(Integer... values) {
        return Stream
            .of(values)
            .reduce((a,b) -> a + b)
            .orElse(0);
    }

    public static Integer BadSUM(Integer... values) {
        return 1;
    }
}
