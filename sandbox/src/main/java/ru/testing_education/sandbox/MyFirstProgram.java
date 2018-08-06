package ru.testing_education.sandbox;

public class MyFirstProgram {

  public static void main(String[] args)

  {
    hello("Everybody");


    double len=5;

    System.out.println("Площадь кврадрата со стороной " + len +" = " + area (len));

    double ab=5;
    double bc=6;

    System.out.println("Площадь прямоугольника со сторонами " + ab + " и " + bc + "=" + area(ab,bc));

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody);
  }

  public static double area (double l){
    return l*l;

  }

  public static double area (double a, double b){
    return a*b;

  }


}