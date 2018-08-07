package ru.testing_education.sandbox;

public class Task2_Distance {

  public static void main(String[] args) {

    Point p1 = new Point(-7, 0);
    Point p2 = new Point(7, 0);

    //   System.out.println("Расстояние между двумя точками с координатами (" + p1.x + ";" + p1.y
    //          + ") и (" + p2.x + ";" + p2.y + ") равно " + distance(p1,p2));

    System.out.println("Расстояние между двумя точками с координатами (" + p1.x + ";" + p1.y
            + ") и (" + p2.x + ";" + p2.y + ") равно " + p1.distance(p2));

  }

  //  Реализация подсчета расстояния через функцию
  //   public static double distance (Point p1, Point p2) {
  //    return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
  //  }


}
