import java.lang.String;

public class Printer {

    private String queue;
    private int pendingPagesCount;
    private int totalPrintedCount;

    public Printer() {
        queue = "";
        pendingPagesCount = 0;
        totalPrintedCount = 0;
    }


    public void append(String text) {
        queue = queue + text;
        queue = queue + ("\n");
    }

    public void append(String text, int count) {
        queue= queue + "В документе - " + count + " страниц \n";
        append(text);
        pendingPagesCount = getPendingPagesCount() + count;
    }

    public void append(String text, String name, int count) {
        queue = queue + "Название документа - " + name + ". \n";
        append(text, count);

    }

    public void print() {
        System.out.println(queue);
        clear();
    }


    public int getPendingPagesCount() {
        return pendingPagesCount;
    }

    public int getTotalPrintedCount() {
        return totalPrintedCount;
    }

    public void clear() {
        queue = "";
        this.totalPrintedCount = getTotalPrintedCount() + getPendingPagesCount();
        this.pendingPagesCount = 0;

    }
}