package tasks;

import java.io.*;
import java.util.ArrayList;

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
    public ArrayList<PrefixAndStr> fileToArrStr(BufferedReader br) throws IOException {
        ArrayList<PrefixAndStr> arr = new ArrayList<>();
        if (!(br.ready())) {
            throw new IOException("IOException");
        }
        String curStr = br.readLine();
        PrefixAndStr curState = new PrefixAndStr(1, curStr);
        while (br.ready()) {
            curStr = br.readLine();
            if (register) {
                if (N <= curStr.length()) {
                    if (curStr.substring(N).equalsIgnoreCase(curState.getStr().substring(N))) {
                        curState = new PrefixAndStr(curState.getPref() + 1, curState.getStr());
                    } else {
                        arr.add(curState);
                        curState = new PrefixAndStr(1, curStr);
                    }
                } else {
                    curState = new PrefixAndStr(curState.getPref() + 1, curState.getStr());
                }
            } else {
                if (N <= curStr.length()) {
                    if (curStr.substring(N).equals(curState.getStr().substring(N))) {
                        curState = new PrefixAndStr(curState.getPref() + 1, curState.getStr());
                    } else {
                        arr.add(curState);
                        curState = new PrefixAndStr(1, curStr);
                    }
                } else {
                    curState = new PrefixAndStr(curState.getPref() + 1, curState.getStr());
                }
            }
        }
        if ((arr.isEmpty()) || (!(arr.get(arr.size() - 1).equals(curState))))
            arr.add(curState);
        return arr;
    }

    //попытка прочитать файл или данные с консоли
    public ArrayList<PrefixAndStr> readInput(String fileName) throws IOException {
        ArrayList<PrefixAndStr> arr;
        if (fileName != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
                arr = fileToArrStr(br);
            } catch (IOException e) {
                throw new IOException("IOException");
            }
        } else {
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            arr = fileToArrStr(sc);
        }
        return arr;
    }

    //вывод в файл или на консоль
    public void writerOutput(ArrayList<PrefixAndStr> arr) throws IOException {
        PrintWriter pw = new PrintWriter(System.out);
        if (outputFileName != null)
            try {
                pw = new PrintWriter(outputFileName);
            } catch (IOException e) {
                throw new IOException("IOException");
            }
        for (PrefixAndStr prefixAndStr : arr) {
            if ((!unique) || (prefixAndStr.getPref() == 1)) {
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