import java.util.ArrayList;

/**
 * Created by Mean Machine on 2/20/2016.
 */
public class Node {

    String behavior = "";
    String response = "";
    Node parent = null;
    ArrayList<Node> children = new ArrayList<Node>();
    int tier;

    public Node getParent() {
        return parent;
    }
    public Node() {}



    public Node(Node parent, int tier)
    {
        this.parent = parent;
        System.out.println("this child's parent is now " + parent.getResponse() + " " + parent.getBehavior());
        this.tier = tier;
    }

    public String toString()
    {
        if(response != "")
        {
            return response;
        }
        else
        {
            return behavior;
        }
    }

    public boolean hasNextChild()
    {
        if(this.getParent() == null)
        {
            return false;
        }
        if(this.getParent() != null) {
            ArrayList<Node> temp = this.getParent().getChildren();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i) == this) {
                    if (i == temp.size() - 1)
                        return false;
                }
            }
        }
        return true;
    }

    public boolean hasChild()
    {
        if(children.size()>0)
        {
            return true;
        }
        return false;
    }

    public Node nextChild()
    {
        if(this.getParent()  != null) {
            ArrayList<Node> temp = this.getParent().getChildren();
            for (int i = 0; i < temp.size() - 1; i++) {
                if (temp.get(i) == this) {
                    return temp.get(i + 1);
                }
            }
        }
        return this;
    }

    public void setParent(Node parent)
    {
        System.out.println(behavior + response + "parent is now " + parent.getResponse() + " " + parent.getBehavior());
        this.parent = parent;
    }

    public Node getChild(int child)
    {
        return this.children.get(child);
    }

    public ArrayList<Node> getChildren()
    {
        return this.children;
    }

    public void addChild(Node child)
    {
        children.add(child);
        System.out.println("child was created for parent " + this.behavior + " " + this.response);
    }

    public void addChild(Node child,int index)
    {
        children.add(index,child);
        System.out.println("child was created for parent" + this.behavior + " " + this.response);
    }

    public String getBehavior()
    {
        return behavior;
    }

    public String getResponse()
    {
        return response;
    }

    public void setBehavior(String behavior)
    {

        this.behavior=behavior;
        //System.out.println(behavior+"'s parent is " + parent.getResponse() + " " + parent.getBehavior());
    }

    public void setResponse(String response)
    {
        //System.out.println(response+"'s parent is " + parent.getResponse() + " " + parent.getBehavior());
        this.response=response;
    }
}
