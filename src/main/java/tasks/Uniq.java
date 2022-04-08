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

    private final ArrayList<PrefixAndStr> arr = new ArrayList<>();

    //метод для получения массива из читаемых данных
    public void fileToArrStr(Scanner sc) {
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
    }

    //попытка прочитать файл или данные с консоли
    public void readInput(String fileName) {
        if (fileName != null) {
            try (Scanner sc = new Scanner(new File(inputFileName))) {
                File inputFile = new File(inputFileName);
                if (!(inputFile.exists())) {
                    boolean fileCreated = inputFile.createNewFile();
                    if (!fileCreated) {
                        throw new IOException();
                    }
                }
                fileToArrStr(sc);
            } catch (IOException e) {
                System.out.println("ошибка" + e);
            }
        }
    }

    //вывод в файл или на консоль
    public void writerOutput() {
        if (outputFileName != null) {
            try (PrintWriter pw = new PrintWriter(outputFileName)) {
                File outFile = new File(outputFileName);
                if (!outFile.exists()) {
                    boolean fileCreated = outFile.createNewFile();
                    if (!fileCreated) {
                        throw new IOException();
                    }
                }
                for (PrefixAndStr prefixAndStr : arr) {
                    if (unique) {
                        if (prefixAndStr.getPref() == 1) {
                            if (prefix) {
                                pw.println(prefixAndStr.getPref() + " " + prefixAndStr.getStr());
                            } else {
                                pw.println(prefixAndStr.getStr());
                            }
                        }
                    } else {
                        if (prefix) {
                            pw.println(prefixAndStr.getPref() + " " + prefixAndStr.getStr());
                        } else {
                            pw.println(prefixAndStr.getStr());
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("ошибка" + e);
            }
        } else {
            for (PrefixAndStr prefixAndStr : arr) {
                if (unique) {
                    if (prefixAndStr.getPref() == 1) {
                        if (prefix) {
                            System.out.println(prefixAndStr.getPref() + " " + prefixAndStr.getStr());
                        } else {
                            System.out.println(prefixAndStr.getStr());
                        }
                    }
                } else {
                    if (prefix) {
                        System.out.println(prefixAndStr.getPref() + " " + prefixAndStr.getStr());
                    } else {
                        System.out.println(prefixAndStr.getStr());
                    }
                }
            }
        }
    }
}