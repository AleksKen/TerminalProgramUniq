package tasks;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

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
        Uniq uniq = new Uniq(outputFileName, register, N, unique, prefix, inputFileName);
        uniq.readInput(inputFileName);
        uniq.writerOutput();
    }
}