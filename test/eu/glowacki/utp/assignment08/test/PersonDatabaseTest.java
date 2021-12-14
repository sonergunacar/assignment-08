package eu.glowacki.utp.assignment08.test;

import eu.glowacki.utp.assignment08.Person;
import eu.glowacki.utp.assignment08.PersonDatabase;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonDatabaseTest {

    File file = null;

    @Before
    public void setup() {
    	//FIXME enter valid file path
        file = new File(" ");
    }

    @Test
    public void serializeAndDeserialize() throws Exception {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Person person1 = new Person("Soner", "Gunacar", sdf.parse("01/05/1988"));
        Person person2 = new Person("Test", "Test", sdf.parse("01/12/1988"));
        Person person3 = new Person("Soner", "Gunacars", sdf.parse("01/03/1988"));
        Person person4 = new Person("Test2", "Test2", sdf.parse("01/12/1988"));

        List<Person> personList = new ArrayList<>(List.of(person1, person2, person3, person4));
        PersonDatabase personDatabase = new PersonDatabase(personList);
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            personDatabase.serialize(dos);
        }
        try (FileInputStream fis = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(fis)) {
            PersonDatabase pd = PersonDatabase.deserialize(dis);
            System.out.println("sortedByFirstName = ");
            List<Person> sortedByFirstName = pd.sortedByFirstName();
            sortedByFirstName.forEach(System.out::println);

            System.out.println("sortedBySurnameFirstNameAndBirthdate = ");
            List<Person> sortedBySurnameFirstNameAndBirthdate = pd.sortedBySurnameFirstNameAndBirthdate();
            sortedBySurnameFirstNameAndBirthdate.forEach(System.out::println);

            System.out.println("sortedByBirthdate = ");
            List<Person> sortedByBirthdate = pd.sortedByBirthdate();
            sortedByBirthdate.forEach(System.out::println);

            assertEquals(4, pd.getPersonList().size());

            System.out.println("Persons born on this day - 01/12/1988:");
            for (Person person : pd.bornOnDay(sdf.parse("01/12/1988"))) {
                System.out.println(person.getFirstName() + " " + person.getSurname());
            }
        }


    }

}