package ru.testing_education.addressbook.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.testing_education.addressbook.model.ContactInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Count of contacts")
  public int count;

  @Parameter(names = "-f", description = "Target File")
  public String file;


  public static void main(String[] args) throws IOException {

    ContactDataGenerator contactGenerator = new ContactDataGenerator();
    JCommander jComander = new JCommander(contactGenerator);

    try {
      jComander.parse(args);

    } catch (ParameterException ex) {
      jComander.usage();
      return;
    }
    contactGenerator.run();

  }

  private void run() throws IOException {
    List<ContactInfo> contacts = generateContact(count);
    save(contacts, new File(file));
  }


  private void save(List<ContactInfo> contacts, File file) throws IOException {

    Writer write = new FileWriter(file);

    for (ContactInfo contact : contacts) {
      write.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getMiddleName(), contact.getSecondName(),
              contact.getHomePhone(), contact.getAddress(), contact.getEmail1(), contact.getPhoto()));
    }
    write.close();

  }

  private List<ContactInfo> generateContact(int count) {

    List<ContactInfo> contacts = new ArrayList<ContactInfo>();

    for (int i = 0; i < count; i++) {

      contacts.add(new ContactInfo()
              .withFirstName(String.format("test_name %s", i)).withMiddleName(String.format("test_midlename %s", i))
              .withSecondName(String.format("test_surname %s", i))
              .withHomePhone("12345").withAddress(String.format("test_address %s", i))
              .withEmail1(String.format("test_%s@mail.ru", i))
              .withPhoto(new File("src/test/resources/2063424_cats.jpg")));
    }
    return contacts;
  }
}
