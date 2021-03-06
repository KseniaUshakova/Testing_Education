package ru.testing_education.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point other) {
    return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
  }

}
