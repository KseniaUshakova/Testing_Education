package ru.testing_education.sandbox;

public class Rectangle {

  public double ab;
  public double bc;

  public Rectangle (double ab, double bc) {
    this.ab=ab;
    this.bc=bc;
  }

  public double area() {
    return this.ab * this.bc;

  }

}
