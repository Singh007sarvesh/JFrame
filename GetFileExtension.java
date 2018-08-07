import java.io.File;
import java.util.*;
public class GetFileExtension
{

    /**
     * Get File extension in java
     * @param args
     */
    public static void main(String[] args) 
    {
        File file = new File("employee.xml");
        File file1 = new File("employee.csv");
        File file2 = new File("employee.json");
        ArrayList<Employee> employees;
        if(getFileExtension(file).equals("xml"))
        {
            SaxParserFactory saxParserFactory=new SaxParserFactory();
            // employees = saxParserFactory.parse();
        }
        else
        {
            if(getFileExtension(file).equals("csv"))
            {

                // employees = saxParserFactory.parse();

            }
            else
            {
                if(getFileExtension(file).equals("csv"))
                {

                }  
            }
        }
        // DatabaseConnection db = new DatabaseConnection();
        // db.insertEmployees(employees);
        
        
    }

    private static String getFileExtension(File file) 
    {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

}