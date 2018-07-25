import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Mean Machine on 2/20/2016.
 */
public class Tree {

    String xmlFile = "";
    Node root = new Node();
    Scanner s;
    File file;

    public Tree(String xml) {
        file = new File(xml);
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        xmlFile = xml;
        createTree(root,s.nextLine());
    }
    public String breadthFirst(String behavior)
    {
        Node current = root;
        boolean found = false;
        ArrayList<Node> responses = new ArrayList<Node>();
        LinkedList<Node> queue = new LinkedList<Node>();
        Node foundBehavior = new Node();
        boolean stop = false;
        while (found == false)
        {
            if(current.toString().equals(behavior))
            {
                queue.clear();
                found = true;
                foundBehavior=current;
                break;
            }
            if(!current.hasNextChild())
            {
                if(current.getParent() != null)
                {
                    if (current.hasChild())
                    {
                        queue.addLast(current);
                        current = queue.remove().getChild(0);
                        continue;
                    }
                    else if (!queue.isEmpty())
                    {
                        current = queue.remove().getChild(0);
                        continue;
                    }
                    else
                    {
                        return "";
                    }
                }
                else if(current == root)
                {
                    current = current.getChild(0);
                    continue;
                }
                else
                {
                    current = queue.remove().getChild(0);
                    continue;
                }
            }
            else
            {
                if(current.hasChild())
                {
                    queue.add(current);
                }
                current = current.nextChild();
            }



        }
        //node has been found in list now we need to find the responses

        while(stop == false)
        {
            if(current == foundBehavior)
            {
                if(current.hasChild()) {
                    current = current.getChild(0);
                    continue;
                }
                else {
                    return "No Responses to Node " + current+". "+current+" is a response to the behavior " + current.getParent();
                }
            }
            if(!current.hasNextChild()) {

                if (current.getParent() != null) {
                    if (current.hasChild()) {
                        queue.addLast(current);
                        current = queue.remove().getChild(0);
                        continue;
                    } else if (!queue.isEmpty()) {
                        responses.add(current);
                        current = queue.remove().getChild(0);
                        continue;
                    } else {
                        responses.add(current);
                        stop = true;
                        continue;
                    }
                } else if (current == root) {
                    current = current.getChild(0);
                    continue;
                } else {
                    current = queue.remove().getChild(0);
                    continue;
                }
            }
            else
            {
                if(current.hasChild())
                {
                    queue.add(current);
                }
                else
                {
                    responses.add(current);
                }
                current = current.nextChild();
            }
        }
        int x = (int)(Math.random()*responses.size());
        return responses.get(x).toString();

    }


    public String depthFirst(String behavior) {
        Node current = root;
        boolean found = false;
        ArrayList<Node> responses = new ArrayList<Node>();
        Node foundBehavior = new Node();
        boolean stop = false;
        while(found == false)
        {
            if(current.toString().equals(behavior))
            {
                found = true;
                foundBehavior=current;
                break;
            }
            if(current.hasChild())
            {
                current = current.getChild(0);
                continue;
            }
            else if(current.hasNextChild())
            {
                current = current.nextChild();
                continue;
            }
            else if (current.getParent().hasNextChild())
            {
                current =  current.getParent().nextChild();
                continue;
            }
            else
            {
                while(current.getParent().hasNextChild() == false)
                {
                    if(current.getParent() == root)
                    {
                        return"";
                    }
                    else
                    {
                        current = current.getParent();
                    }

                }
                current = current.getParent().nextChild();
                continue;
            }
        }
        if(foundBehavior.hasChild()==false)
        {
            return "No Responses to Node " + current+". "+current+" is a response to the behavior " + current.getParent();
        }

        //node has been found in list now we need to find the responses
        while(stop == false)
        {
            while (current.hasChild()) {
                current = current.getChild(0);
            }
            while (current.hasNextChild()) {
                responses.add(current);
                if (current.hasChild()) {
                    current = current.getChild(0);
                    continue;
                }
                current= current.nextChild();
            }
            if(current.hasChild())
            {
                continue;
            }
            responses.add(current);
            if(current.getParent() == foundBehavior)
            {
                break;
            }
            if(current.getParent().hasNextChild())
            {
                current = current.getParent().nextChild();
            }
            else
            {
                while(current.getParent().hasNextChild()== false)
                {
                    if(current.getParent() == foundBehavior)
                    {
                        stop = true;
                        break;
                    }
                    current = current.getParent();
                }
                current = current.getParent().nextChild();
                if(current == foundBehavior)
                {
                    break;
                }
                continue;
            }
        }
        int x = (int)(Math.random()*responses.size());
        return responses.get(x).toString();
    }

    private Node createTree(Node head,String file)
    {
        //System.out.println(file);
        if(file.contains("/root"))
        {
            System.out.println("Tree Has been Created from: " + xmlFile);
            return root;
        }
        else if(file.equals("<root>"))
        {
            System.out.println("root");
            root = new Node();
            root.setBehavior("ROOT");
            Node child = new Node(root,1);
            root.addChild(child);
            return(createTree(child, s.nextLine()));
        }
        else if(head.tier == 0)
        {
            Node child = new Node(root,head.tier+1);
            root.addChild(child);
            return(createTree(child,s.nextLine()));
        }
        else if(file.contains("/node"))
        {

            System.out.println("/node end tier: " + head.tier);


            if(s.hasNext())
            {
                String temp = s.nextLine();
                if (head.tier == 1&& !temp.contains("/root"))
                {
                    Node child = new Node(root, head.tier);
                    root.addChild(child);
                    return createTree(child, temp);
                } else if (!temp.contains("/node")&&head.tier!= 1)
                {
                    Node child = new Node(head.getParent(), head.tier);
                    head.getParent().addChild(child);
                    return createTree(child, temp);
                }
                else
                {
                    return createTree(head.getParent(), temp);
                }
            }
            else
            {
                System.out.println("Bad Things Happened");
                return head;
            }
        }
           else if (file.contains("/")&&!file.contains("/node"))
            {

                // Node child = new Node(head);
                System.out.println(file + " tier: " + (head.tier));
                //System.out.println("tier: "+tier);
                if(file.contains("behavior"))
                {
                    if (!file.contains("behavior=\"\"")) {
                        int behaviorIndex = file.indexOf("behavior=\"") + 10;
                        head.setBehavior(file.substring(behaviorIndex, file.indexOf("\"", behaviorIndex + 1)));
                    }
                }


                if(file.contains("response"))
                {
                    int responseIndex = file.indexOf("response=\"") + 10;
                    head.setResponse(file.substring(responseIndex, file.indexOf("\"", responseIndex + 1)));
                }

                if(s.hasNext()) {

                    String temp = s.nextLine();

                    if (!temp.contains("</node>")) {
                        Node child = new Node(head.getParent(), head.tier);
                        head.getParent().addChild(child);
                        return (createTree(child, temp));
                    } else {
                        //System.out.println(file);
                       // System.out.println("HERE");

                        return (createTree(head.getParent(), temp));
                    }
                }
                else{
                    System.out.println("Bad Things Happened");
                    return head;
                }

            }
            else
            {

                System.out.println(file + " tier: " + (head.tier));
                if(file.contains("behavior"))
                {
                    int behaviorIndex = file.indexOf("behavior=\"") + 10;
                    head.setBehavior(file.substring(behaviorIndex, file.indexOf("\"", behaviorIndex + 1)));
                }

                if(file.contains("response"))
                {
                    int responseIndex = file.indexOf("response=\"") + 10;
                    if (!file.substring(responseIndex, responseIndex + 1).contains("\"")) {
                        head.setResponse(file.substring(responseIndex, file.indexOf("\"", responseIndex + 1)));
                    }
                }
                Node child = new Node(head, head.tier + 1);
                head.addChild(child);

                return (createTree(child, s.nextLine()));
            }
    }
}
