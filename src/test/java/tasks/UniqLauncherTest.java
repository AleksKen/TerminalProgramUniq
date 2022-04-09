package tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;


public class UniqLauncherTest {
    @Test
    void testAllInclusive() throws IOException {

        UniqLauncher.main("-i -u -o testData/fileOu -c -s 3 testData/fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("testData/fileExAll"));
        Scanner scOu = new Scanner(new FileReader("testData/fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testNotPrefAndNotReg() throws IOException {

        UniqLauncher.main("-u -o testData/fileOu -s 3 testData/fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("testData/fileExNotPrReg"));
        Scanner scOu = new Scanner(new FileReader("testData/fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testNotUniAndNotN() throws IOException {

        UniqLauncher.main("-i -o testData/fileOu -c testData/fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("testData/fileExNotUniN"));
        Scanner scOu = new Scanner(new FileReader("testData/fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testEmptyStr() throws IOException {

        UniqLauncher.main("-i -o testData/fileOu -c testData/FileInEmpty".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("testData/fileExEmpty"));
        Scanner scOu = new Scanner(new FileReader("testData/fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testEmptyStrUniq() throws IOException {

        UniqLauncher.main("-i -u -o testData/fileOu -c testData/FileInEmpty".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("testData/fileExEmptyUniq"));
        Scanner scOu = new Scanner(new FileReader("testData/fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }
}