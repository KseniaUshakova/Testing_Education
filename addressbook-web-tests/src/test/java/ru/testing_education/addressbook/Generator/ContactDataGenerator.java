package ru.testing_education.addressbook.Generator;

import ru.testing_education.addressbook.model.ContactInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


  public static void main(String[] args) throws IOException {

    int count = Integer.parseInt(args[0]);

    File file = new File(args[1]);

    List<ContactInfo> contacts = generareContact(count);
    save(contacts, file);

  }

  private static void save(List<ContactInfo> contacts, File file) throws IOException {

    Writer write = new FileWriter(file);

    for (ContactInfo contact: contacts){
      write.write(String.format("%s;%s;%s;%s;%s;%s\n",contact.getFirstName(), contact.getMiddleName(), contact.getSecondName(),
              contact.getHomePhone(), contact.getAddress(), contact.getEmail1()));
    }
    write.close();

  }

  private static List<ContactInfo> generareContact(int count) {

    List<ContactInfo> contacts = new ArrayList<ContactInfo>();

    for (int i=0; i<count; i++) {

      contacts.add(new ContactInfo()
              .withFirstName(String.format("test_name %s",i)).withMiddleName(String.format("test_m_name %s",i))
              .withSecondName(String.format("test_s_name %s",i))
              .withHomePhone("12345").withAddress(String.format("test_address %s",i))
              .withEmail1(String.format("test_%s@mail.ru",i)));
    }
    return contacts;
  }
}
