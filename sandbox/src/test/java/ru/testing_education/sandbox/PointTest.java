package ru.testing_education.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

// There are 7 tests: 4 positive/ 3 negative
  @Test
  static void testDistance1pos() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);

    Assert.assertEquals(p1.distance(p2),0.0);
  }

  @Test
  static void testDistance2pos() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(7,0);

    Assert.assertEquals(p1.distance(p2),7.0);
  }

  @Test
  static void testDistance3pos() {
    Point p1 = new Point(0,10);
    Point p2 = new Point(0,2);

    Assert.assertEquals(p1.distance(p2),8.0);
  }

  @Test
  static void testDistance4pos() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(2,2);

    Assert.assertEquals(p1.distance(p2),Math.sqrt(8));
  }


  @Test
  static void testDistance5neg() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);

    Assert.assertEquals(p1.distance(p2),0);
  }

  @Test
  static void testDistance6neg() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(-8,0);

    Assert.assertEquals(p1.distance(p2),6.0);
  }

  @Test
  static void testDistance7neg() {
    Point p1 = new Point(2,0);
    Point p2 = new Point(0,0);

    Assert.assertEquals(p1.distance(p2),2);
  }


}
