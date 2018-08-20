package ru.testing_education.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTest {

  @Test
  public void test0() {
    Equation e = new Equation(1, 1, 1);

    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void test1() {
    Equation e = new Equation(1, 2, 1);

    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void test2() {
    Equation e = new Equation(1, 6, 1);

    Assert.assertEquals(e.rootNumber(),2);
  }

  @Test
  public void testLine() {
    Equation e = new Equation(0, 6, 1);

    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void testZero() {
    Equation e = new Equation(0, 0, 1);

    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void testEmpty() {
    Equation e = new Equation(0, 0, 0);

    Assert.assertEquals(e.rootNumber(),-1);
  }




}
