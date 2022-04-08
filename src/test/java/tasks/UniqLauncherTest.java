package tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;


public class UniqLauncherTest {
    @Test
    void testWithPref() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn");
        writer.println("dgif786DDDvcsu9");
        writer.println("DGIF786dDDvcSU9");
        writer.println("777f786DDDvcsu9");
        writer.println("djfjhfvxdh856DFjk");
        writer.println("hjgkahihKH96DVsv");
        writer.println("knokahIHKH96DVsv");
        writer.println("cjhglcxf6rFDHj00");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx");
        pw.println("1 djfjhfvxdh856DFjk");
        pw.println("1 cjhglcxf6rFDHj00");
        pw.close();

        UniqLauncher.main("-i -u -o fileOu -c -s 3 fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx"));
        Scanner scOu = new Scanner(new FileReader("fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testNotPrefAndNotReg() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn");
        writer.println("dgif786DDDvcsu9");
        writer.println("dgif786dddvcsu9");
        writer.println("777F786dDDvcSU9");
        writer.println("888F786dDDvcSU9");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx");
        pw.println("dgif786DDDvcsu9");
        pw.println("dgif786dddvcsu9");
        pw.close();

        UniqLauncher.main("-u -o fileOu -s 3 fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx"));
        Scanner scOu = new Scanner(new FileReader("fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testNotUniAndNotN() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn");
        writer.println("dgif786DDDvcsu9");
        writer.println("DGIF786dDDvcSU9");
        writer.println("777f786DDDvcsu9");
        writer.println("djfjhfvxdh856DFjk");
        writer.println("knokahIHKH96DVsv");
        writer.println("knoKAHIHKH96DVsv");
        writer.println("cjhglcxf6rFDHj00");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx");
        pw.println("2 dgif786DDDvcsu9");
        pw.println("1 777f786DDDvcsu9");
        pw.println("1 djfjhfvxdh856DFjk");
        pw.println("2 knokahIHKH96DVsv");
        pw.println("1 cjhglcxf6rFDHj00");
        pw.close();

        UniqLauncher.main("-i -o fileOu -c fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx"));
        Scanner scOu = new Scanner(new FileReader("fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }

    @Test
    void testEmptyStr() throws IOException {
        PrintWriter writer = new PrintWriter("fileIn");
        writer.println("dgif786DDDvcsu9");
        writer.println("dgif786DDDvcsu9");
        writer.println("dgif786DDDvcsu9");
        writer.println("");
        writer.println("");
        writer.println("dgif786DDDvcsu9");
        writer.println("dgif786DDDvcsu9");
        writer.println("");
        writer.println("dgif786DDDvcsu9");
        writer.println("dgif786DDDvcsu9");
        writer.close();

        PrintWriter pw = new PrintWriter("fileEx");
        pw.println("3 dgif786DDDvcsu9");
        pw.println("2 ");
        pw.println("2 dgif786DDDvcsu9");
        pw.println("1 ");
        pw.println("2 dgif786DDDvcsu9");
        pw.close();

        UniqLauncher.main("-i -o fileOu -c fileIn".trim().split(" "));

        Scanner scEx = new Scanner(new FileReader("fileEx"));
        Scanner scOu = new Scanner(new FileReader("fileOu"));
        while ((scEx.hasNextLine()) || (scOu.hasNextLine())) {
            String strEx = scEx.nextLine();
            String strOu = scOu.nextLine();
            Assertions.assertEquals(strEx, strOu);
        }
    }
}