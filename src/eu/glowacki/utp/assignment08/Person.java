package eu.glowacki.utp.assignment08;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;

	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	// do
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		try {
			output.writeBytes(_firstName+","+_surname+","+_birthdate.getTime()+"\n");
		}catch (IOException ex){
			throw new Assignment08Exception("Person serialize error:",ex);
		}
	}

	//do
	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		try(InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr)) {
			String[] personLine =  reader.readLine().split(",");
			String name=personLine[0];
			String surname=personLine[1];
			long birthDate=Long.parseLong(personLine[2]);
			return new Person(name,surname,new Date(birthDate));
		} catch (IOException ex) {
			throw new Assignment08Exception("Person deserialize error:",ex);
		}
	}

	@Override
	public int compareTo(Person otherPerson) {
		int i = _surname.compareTo(otherPerson.getSurname());
		if (i != 0) return i;
		i = _firstName.compareTo(otherPerson.getFirstName());
		if (i != 0) return i;
		return _birthdate.compareTo(otherPerson.getBirthDate());
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getSurname() {
		return _surname;
	}

	public Date getBirthDate() {
		return _birthdate;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Person{" +
				"_firstName='" + _firstName + '\'' +
				", _surname='" + _surname + '\'' +
				", _birthdate=" + sdf.format(_birthdate) +
				'}';
	}
}