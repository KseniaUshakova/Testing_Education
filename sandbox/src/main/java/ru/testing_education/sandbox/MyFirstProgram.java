package ru.testing_education.sandbox;

public class MyFirstProgram {

  public static void main(String[] args)

  {
    hello("Everybody");

    Square s = new Square(9);


    System.out.println("Площадь кврадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(7,6);


    System.out.println("Площадь прямоугольника со сторонами " + r.ab + " и " + r.bc + "=" + r.area());

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody);
  }




}