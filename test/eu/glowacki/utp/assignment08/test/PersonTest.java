package eu.glowacki.utp.assignment08.test;

import eu.glowacki.utp.assignment08.Assignment08Exception;
import eu.glowacki.utp.assignment08.Person;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;

import static org.junit.Assert.*;

public class PersonTest {

    File file = null;

    @Before
    public void setup() throws Exception {
        //FIXME enter valid file path
        file = new File(" ");

    }

    @Test
    public void serializeAndDeserialize() {
        Person person = new Person("soner", "gunacar", new Date());
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
             person.serialize(dos);
        } catch (FileNotFoundException e) {
            fail("FileNotFound: enter valid path");
        } catch (IOException | Assignment08Exception exception) {
            fail(exception.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(fis)) {
            Person person2 = Person.deserialize(dis);
            assertEquals("soner", person2.getFirstName());
        } catch (FileNotFoundException e) {
            fail("FileNotFound: enter valid path");
        } catch (IOException | Assignment08Exception exception) {
            fail(exception.getMessage());
        }

    }

}