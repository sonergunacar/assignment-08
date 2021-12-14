package eu.glowacki.utp.assignment08;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class PersonDatabase {

    private final List<Person> personList;

    public List<Person> getPersonList() {
        return personList;
    }

    public PersonDatabase(List<Person> personList) {
        this.personList = personList;
    }

    // do
    public static PersonDatabase deserialize(DataInputStream input) throws Exception {
        List<Person> personList = new ArrayList<>();
        BufferedReader d = new BufferedReader(new InputStreamReader(input));
        String value;
        while ((value = d.readLine()) != null) {
            InputStream inputStream = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
            DataInputStream dis = new DataInputStream(inputStream);
            personList.add(Person.deserialize(dis));
        }
        return new PersonDatabase(personList);
    }

    // do
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        for (Person person : personList) {
            person.serialize(output);
        }
    }

    // done
    public List<Person> sortedByFirstName() {
        return personList.stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
    }

    // done
    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        return personList.stream().sorted(Person::compareTo).collect(Collectors.toList());
    }

    // done
    public List<Person> sortedByBirthdate() {
        return personList.stream().sorted(Comparator.comparing(Person::getBirthDate)).collect(Collectors.toList());

    }

    // done
    public List<Person> bornOnDay(Date date) {
        return personList.stream().filter(person -> person.getBirthDate().equals(date)).collect(Collectors.toList());
    }
}