package ru.testing_education.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args){

    String[] langs = {"Java", "Phyton", "C++", "Perl"};

 //   for (int i=0; i<langs.length; i++) {
 //     System.out.println( "I want to learn " + langs[i]);
 //   }

   // List<String> languages= new ArrayList<String>();
   // languages.add ("PHP");
   // languages.add ("C#");

    List<String> languages= Arrays.asList("Java", "Phyton", "C++", "Perl");

    for (String l : languages) {
      System.out.println( "I want to learn " + l); }
    }
}
