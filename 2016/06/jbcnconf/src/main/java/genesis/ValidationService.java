package genesis;

import static helios.Validators.inList;
import static helios.Validators.required;
import static helios.Helios.validate;

import java.util.List;

import helios.Helios;
import helios.ValidatorError;

public class ValidationService {

    //tag::person[]
    public static class Person {
        public String name;
        public Integer age;
        public String address;
    }
    //end::person[]

    private final ConfigurationService configurationService;

    public ValidationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    // tag::validate[]
    public List<ValidatorError> validateString(final String type) {
        return Helios.validate("type",
                               type,
                               required(),
                               inList("one", "two"));
    }
    // end::validate[]

    // tag::validatePerson[]
    public List<ValidatorError> validatePerson(final Person person) {
        return validate("person", person,
            (Person p) -> validate("name", p.name, required()),
            (Person p) -> validate("age", p.age, required()));
    }
    // end::validatePerson[]
}
