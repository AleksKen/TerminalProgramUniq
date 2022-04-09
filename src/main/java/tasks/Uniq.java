package tasks;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Uniq {

    private final String outputFileName;
    private final boolean register;
    private final int N;
    private final boolean unique;
    private final boolean prefix;
    private final String inputFileName;

    public Uniq(String outputFileName, boolean register, int N, boolean unique, boolean prefix, String inputFileName) {
        this.outputFileName = outputFileName;
        this.register = register;
        this.N = N;
        this.unique = unique;
        this.prefix = prefix;
        this.inputFileName = inputFileName;
    }

    //метод для получения массива из читаемых данных
    public ArrayList<PrefixAndStr> fileToArrStr(Scanner sc) {
        ArrayList<PrefixAndStr> arr = new ArrayList<>();
        String curStr = sc.nextLine();
        arr.add(new PrefixAndStr(1, curStr));
        while (sc.hasNextLine()) {
            curStr = sc.nextLine();
            if (curStr.isEmpty()) {
                if (arr.get(arr.size() - 1).getStr().isEmpty()) {
                    arr.add(new PrefixAndStr(arr.get(arr.size() - 1).getPref() + 1, arr.get(arr.size() - 1).getStr()));
                    arr.remove(arr.size() - 2);
                } else {
                    arr.add(new PrefixAndStr(1, curStr));
                }
            } else {
                if (register) {
                    if (curStr.substring(N).equalsIgnoreCase(arr.get(arr.size() - 1).getStr().substring(N))) {
                        arr.add(new PrefixAndStr(arr.get(arr.size() - 1).getPref() + 1, arr.get(arr.size() - 1).getStr()));
                        arr.remove(arr.size() - 2);
                    } else
                        arr.add(new PrefixAndStr(1, curStr));
                } else {
                    if (curStr.substring(N).equals(arr.get(arr.size() - 1).getStr().substring(N))) {
                        arr.add(new PrefixAndStr(arr.get(arr.size() - 1).getPref() + 1, arr.get(arr.size() - 1).getStr()));
                        arr.remove(arr.size() - 2);
                    } else
                        arr.add(new PrefixAndStr(1, curStr));
                }
            }
        }
        return arr;
    }

    //попытка прочитать файл или данные с консоли
    public ArrayList<PrefixAndStr> readInput(String fileName) {
        ArrayList<PrefixAndStr> arr = new ArrayList<>();
        if (fileName != null) {
            try (Scanner sc = new Scanner(new File(inputFileName))) {
                File inputFile = new File(inputFileName);
                if (!(inputFile.exists())) {
                    boolean fileCreated = inputFile.createNewFile();
                    if (!fileCreated) {
                        throw new IOException();
                    }
                }
                arr = fileToArrStr(sc);
            } catch (IOException e) {
                System.out.println("ошибка" + e);
            }
        }
        return arr;
    }

    //вывод в файл или на консоль
    public void writerOutput(ArrayList<PrefixAndStr> arr) {
        PrintWriter pw = new PrintWriter(System.out);
        if (outputFileName != null)
            try {
                pw = new PrintWriter(outputFileName);
            } catch (IOException e) {
                System.out.println("ошибка" + e);
            }
        for (PrefixAndStr prefixAndStr : arr) {
            if (unique) {
                if (prefixAndStr.getPref() == 1) {
                    if (prefix) {
                        if (prefixAndStr.getStr().isEmpty())
                            pw.println(prefixAndStr.getPref());
                        else
                            pw.println(prefixAndStr.getPref() + " " + prefixAndStr.getStr());
                    } else {
                        pw.println(prefixAndStr.getStr());
                    }
                }
            } else {
                if (prefix) {
                    if (prefixAndStr.getStr().isEmpty())
                        pw.println(prefixAndStr.getPref());
                    else
                        pw.println(prefixAndStr.getPref() + " " + prefixAndStr.getStr());
                } else {
                    pw.println(prefixAndStr.getStr());
                }
            }
        }
        pw.close();
    }
}