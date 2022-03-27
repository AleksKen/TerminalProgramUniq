package tasks;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UniqLauncher {

    @Option(name = "-o")
    private String outputFileName;

    @Option(name = "-i", usage = "case sensitive")
    private boolean register;

    @Option(name = "-s")
    private int N = 0;

    @Option(name = "-u", usage = "display only unique strings")
    private boolean unique;

    @Option(name = "-c", usage = "count lines")
    private boolean prefix;

    @Argument()
    private String inputFileName;

    public UniqLauncher() {
    }


    public static void main(String[] args) throws IOException {
        new UniqLauncher().parseArgs(args);
    }

    public void parseArgs(String[] args) throws IOException {
        CmdLineParser uniqLauncher = new CmdLineParser(this);
        try {
            uniqLauncher.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("неверно заданные аргументы");
        }
        readInput(inputFileName);
        stringReplace();
        writerOutput();
    }

    private ArrayList<PrefixAndStr> arr = new ArrayList<>();

    //метод для получения массива из читаемых данных
    public void fileToArrStr(Scanner sc) {
        while (sc.hasNextLine()) {
            String curStr = sc.nextLine();
            if (curStr.isEmpty())
                break;
            PrefixAndStr pas = new PrefixAndStr();
            pas.add(0, curStr);
            arr.add(pas);
        }
    }

    //попытка прочитать файл или данные с консоли
    public void readInput(String fileName) {
        if (fileName != null) {
            try (Scanner sc = new Scanner(new File(inputFileName + ".txt"))) {
                File inputFile = new File(inputFileName + ".txt");
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

    //изменение массива под данные задачи
    public void stringReplace() {
        int i = 1;
        while (i < arr.size()) {
            if (register) {
                if (arr.get(i).getStr().substring(N).equalsIgnoreCase(arr.get(i - 1).getStr().substring(N))) {
                    arr.get(i - 1).change(arr.get(i).getPref() + 1);
                    arr.remove(i);
                } else i++;
            } else {
                if (arr.get(i).getStr().substring(N).equals(arr.get(i -1).getStr().substring(N))) {
                    arr.get(i - 1).change(arr.get(i).getPref() + 1);
                    arr.remove(i);
                } else i++;
            }
        }
    }

    //вывод в файл или на консоль
    public void writerOutput() {
        if (outputFileName != null) {
            try (PrintWriter pw = new PrintWriter(new File(outputFileName + ".txt"))) {
                File outFile = new File(outputFileName + ".txt");
                if (!outFile.exists()) {
                    boolean fileCreated = outFile.createNewFile();
                    if (!fileCreated) {
                        throw new IOException();
                    }
                }
                if (unique) {
                    for (PrefixAndStr prefixAndStr : arr) {
                        if (prefix) {
                            if (prefixAndStr.getPref() == 0) {
                                pw.println(prefixAndStr.getWithPref());
                            }
                        } else {
                            if (prefixAndStr.getPref() == 0) {
                                pw.println(prefixAndStr.getNotPref());
                            }
                        }
                    }
                } else {
                    for (PrefixAndStr prefixAndStr : arr) {
                        if (prefix) {
                            pw.println(prefixAndStr.getWithPref());
                        } else {
                            pw.println(prefixAndStr.getNotPref());
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("ошибка" + e);
            }
        } else {
            if (unique) {
                for (PrefixAndStr prefixAndStr : arr) {
                    if (prefix) {
                        if (prefixAndStr.getPref() == 0) {
                            System.out.println(prefixAndStr.getWithPref());
                        }
                    } else {
                        if (prefixAndStr.getPref() == 0) {
                            System.out.println(prefixAndStr.getNotPref());
                        }
                    }
                }
            } else {
                for (PrefixAndStr prefixAndStr : arr) {
                    if (prefix) {
                        System.out.println(prefixAndStr.getWithPref());
                    } else {
                        System.out.println(prefixAndStr.getNotPref());
                    }
                }
            }
        }
    }
}

