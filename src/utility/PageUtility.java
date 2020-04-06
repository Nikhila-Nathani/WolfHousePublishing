package utility;

import java.util.List;
import java.util.Objects;

public class PageUtility {

    public static void displayOptions(String columns, List<Object> objects){
        System.out.println("------------------------------------------------");
        System.out.println(columns);
        System.out.println("------------------------------------------------");
        int count =1;
        for(Object o : objects){
            System.out.print(count+"\t\t");
            System.out.print(o.toString());
            System.out.println();
            count+=1;
        }
    }
}
