package tasks;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.*;
import java.util.Scanner;


public class UniqLauncherTest {

    private boolean fun(String ex) throws FileNotFoundException {
        Scanner scEx = new Scanner(new FileReader(ex));
        Scanner scOu = new Scanner(new FileReader("testData/fileOu"));
        boolean flag = true;
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            if (!(strEx.equals(strOu))) {
                flag = false;
                break;
            }
        }
        File file = new File("testData/fileOu");
        file.delete();
        return flag;
    }

    @Test
    void testAllInclusive() throws IOException, CmdLineException {
        UniqLauncher.main("-i -u -o testData/fileOu -c -s 3 testData/fileIn".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExAll"));
    }

    @Test
    void testNotPrefAndNotReg() throws IOException, CmdLineException {
        UniqLauncher.main("-u -o testData/fileOu -s 3 testData/fileIn".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExNotPrReg"));
    }

    @Test
    void testNotUniAndNotN() throws IOException, CmdLineException {
        UniqLauncher.main("-i -c testData/fileIn -o testData/fileOu".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExNotUniN"));
    }

    @Test
    void testEmptyStr() throws IOException, CmdLineException {
        UniqLauncher.main("-i -o testData/fileOu -c testData/FileInEmpty".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExEmpty"));
    }

    @Test
    void testEmptyStrUniq() throws IOException, CmdLineException {
        UniqLauncher.main("-i -u -o testData/fileOu -c testData/FileInEmpty".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExEmptyUniq"));
    }

    @Test
    void testTheBiggestN() throws IOException, CmdLineException {
        UniqLauncher.main("-i -c -o testData/fileOu -s 25 testData/fileIn".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExBig"));
    }

    @Test
    void testBiggertN() throws IOException, CmdLineException {
        UniqLauncher.main("-i -o testData/fileOu -s 7 testData/fileInBigger".trim().split(" "));
        Assert.assertTrue(fun("testData/fileExBigger"));
    }
}