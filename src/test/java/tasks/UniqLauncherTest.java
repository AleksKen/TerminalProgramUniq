package tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;


public class UniqLauncherTest {
    @Test
    void testWithPref() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn.txt");
        writer.println("dgif786DDDvcsu9");
        writer.println("DGIF786dDDvcSU9");
        writer.println("777f786DDDvcsu9");
        writer.println("djfjhfvxdh856DFjk");
        writer.println("hjgkahihKH96DVsv");
        writer.println("knokahIHKH96DVsv");
        writer.println("cjhglcxf6rFDHj00");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx.txt");
        pw.println("0 djfjhfvxdh856DFjk");
        pw.println("0 cjhglcxf6rFDHj00");
        pw.close();

        UniqLauncher.main("-i -u -o fileOu -c -s 3 fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx.txt"));
        Scanner scOu = new Scanner(new FileReader("fileOu.txt"));
        while (scEx.hasNextLine()) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testNotPrefAndNotReg() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn.txt");
        writer.println("dgif786DDDvcsu9");
        writer.println("dgif786dddvcsu9");
        writer.println("777F786dDDvcSU9");
        writer.println("888F786dDDvcSU9");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx.txt");
        pw.println("dgif786DDDvcsu9");
        pw.println("dgif786dddvcsu9");
        pw.close();

        UniqLauncher.main("-u -o fileOu -s 3 fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx.txt"));
        Scanner scOu = new Scanner(new FileReader("fileOu.txt"));
        while (scEx.hasNextLine()) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testNotUniAndNotN() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn.txt");
        writer.println("dgif786DDDvcsu9");
        writer.println("DGIF786dDDvcSU9");
        writer.println("777f786DDDvcsu9");
        writer.println("djfjhfvxdh856DFjk");
        writer.println("knokahIHKH96DVsv");
        writer.println("knoKAHIHKH96DVsv");
        writer.println("cjhglcxf6rFDHj00");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx.txt");
        pw.println("1 dgif786DDDvcsu9");
        pw.println("0 777f786DDDvcsu9");
        pw.println("0 djfjhfvxdh856DFjk");
        pw.println("1 knokahIHKH96DVsv");
        pw.println("0 cjhglcxf6rFDHj00");
        pw.close();

        UniqLauncher.main("-i -o fileOu -c fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx.txt"));
        Scanner scOu = new Scanner(new FileReader("fileOu.txt"));
        while (scEx.hasNextLine()) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

}