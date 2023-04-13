import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;

/**
 * Created by a.sosnina on 1/27/2022.
 */
public class NodeLinkFinder extends RecursiveAction {
    private static TreeSet<String> allLinks;
    private Node node;

    public NodeLinkFinder(Node node) {
        if(allLinks == null) allLinks = new TreeSet<>();
        this.node = node;
    }

    @Override
    protected void compute() {
        List<NodeLinkFinder> taskList = new ArrayList<>();
        //TreeSet<String> childLinks = new TreeSet<>();
        node.findChilds();
        synchronized (allLinks) {
            if(node.getChilds().size() > 0) {
                for (String child : node.getChilds()) {
                    if (!allLinks.contains(child)) {
                        allLinks.add(child);
                        NodeLinkFinder task = new NodeLinkFinder(new Node(child));
                        task.fork();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        taskList.add(task);
                    }
                }
            }
        }

        for (NodeLinkFinder task : taskList) {
            task.join();
        }
    }

    public TreeSet<String> getAllLinks() {
        return allLinks;
    }


}
