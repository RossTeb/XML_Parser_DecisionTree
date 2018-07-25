import java.util.Scanner;

/**
 * Created by Mean Machine on 2/20/2016.
 */
public class main {
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        Tree tree = new Tree("xml.xml");

        //System.out.println(tree.breadthFirst("Evade"));

        String in="";
        boolean go = true;
        while(go)
        {
            System.out.print("'Breadth' or 'Depth' First search?('quit' to exit) : ");
            in = input.nextLine();
            if(in.equals("quit"))
            {
                go = false;
                continue;
            }
            else
            {
                if(in.equals("Breadth")||in.equals("breadth")) {
                    System.out.print("Event ('quit' to exit) : ");
                    in = input.nextLine();
                    System.out.println(tree.breadthFirst(in));
                }
                else if(in.equals("Depth")||in.equals("depth"))
                {
                    System.out.print("Event ('quit' to exit) : ");
                    in = input.nextLine();
                    System.out.println(tree.depthFirst(in));
                }
                else
                {
                    System.out.println("Please type either breadth or depth case does not matter for first letter");
                    continue;
                }
            }
        }
    }
}
