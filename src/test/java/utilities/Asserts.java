package utilities;

import org.apache.log4j.Logger;
import org.junit.Assert;


public class Asserts {

    private static Logger log = Logger.getLogger("Asserts");



    public static void expectToBeEqual(Object actual, Object expected) {
        log.info("Comparing values--- Actual:- " + actual + ", Expected:- " + expected);
        org.testng.Assert.assertEquals(actual, expected);
    }


    public static void expectToBeTrue(boolean condition) {
        org.testng.Assert.assertTrue(condition);
    }


    public static void expectToBeTrue(boolean condition, String sMessage) {
        log.info("---------------------Expecting value to be TRUE , Received:-" + condition + "---------------------");
        org.testng.Assert.assertTrue(condition, sMessage);
    }


    public static void expectToBeEqual(Object actual, Object expected, String sMessage) {
        log.info("Comparing values--- Actual:- " + actual + ", Expected:- " + expected);
        org.testng.Assert.assertEquals(actual, expected, sMessage);
    }


}
