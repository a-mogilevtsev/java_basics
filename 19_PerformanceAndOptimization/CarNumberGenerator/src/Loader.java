import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        int cores = Runtime.getRuntime().availableProcessors();
        int partSize = 999 / cores;
        int from = 1;
        int to = 0;
        for (int i = 0; i < cores; i++) {
            from = partSize * i;
            if (i == cores - 1) {
                to = 999;
            } else {
                to = partSize * (i + 1);
            }
            generateAndWrite(from, to, i);
        }


        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void generateAndWrite(int from, int to, int partNum) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String outputFile = String.format("res/numbers%s.txt", String.valueOf(partNum));
                    PrintWriter writer = new PrintWriter(outputFile);
                    char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
                    StringBuilder builder = new StringBuilder();
                    for (int number = from; number < to; number++) {
                        int regionCode = 199;
                        for (char firstLetter : letters) {
                            for (char secondLetter : letters) {
                                for (char thirdLetter : letters) {
                                    builder.append(firstLetter);
                                    builder.append(padNumber(number, 3));
                                    builder.append(secondLetter);
                                    builder.append(thirdLetter);
                                    builder.append(padNumber(regionCode, 2));
                                    builder.append("\n");
                                }
                            }
                        }
                        writer.write(builder.toString());
                        builder = new StringBuilder();
                    }
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        if (padSize > 0) {
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < padSize; i++) {
                prefix.append('0');
            }
            numberStr = prefix.append(numberStr);
        }
        return numberStr.toString();
    }
}
