import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
//import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.sql.*;
import org.json.simple.parser.ParseException;
class Json
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)throws Exception
    {
        //JSON parser object to parse read file
        JSONParser json = new JSONParser();
         
        try (FileReader reader = new FileReader("employee.json"))
        {
            //Read JSON file
            Object obj = json.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
           // System.out.println(employeeList);
            Iterator<String> iterator = employeeList.iterator();
           // Class.forName("com.mysql.jdbc.Driver");  
           // Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");    
       //     Statement stmt=con.createStatement(); 
            while (iterator.hasNext()) 
            {
                Object empobj = (Object)iterator.next();
                JSONObject empob = (JSONObject)empobj;
                JSONObject employeeObject = (JSONObject) empob.get("employee");
         
        //Get employee first name
                String firstName = (String) employeeObject.get("firstname");   
         
        //Get employee last name
                String lastName = (String) employeeObject.get("lastname"); 
         
        //Get employee website name
                String date_of_birth = (String) employeeObject.get("dob");   
                System.out.println(date_of_birth);

                String aadahr_id = (String) employeeObject.get("aadahr_id");   
                System.out.println(aadahr_id);

                String gender = (String) employeeObject.get("gender");   
                System.out.println(gender);

                String email = (String) employeeObject.get("email");   
                System.out.println(email);

                String username = (String) employeeObject.get("username");   
                System.out.println(username);

                String password = (String) employeeObject.get("password");   
                System.out.println(password);

                String query = "insert into emp_records values(\""+firstName+"\",\""+lastName+"\",\""
                                                  +date_of_birth+"\",\""+aadahr_id+"\",\""+gender
                                                        +"\",\""+email+"\",\""+username+"\",\""+password+"\");";
               // System.out.println(query);

              //  stmt.executeUpdate(query);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}