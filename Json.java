// Java program to read JSON from a file
 
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
 
public class Json
{
    public static void main(String[] args) throws Exception 
    {

        Object obj = new JSONParser().parse(new FileReader("employee.json"));
        JSONObject jo = (JSONObject) obj;
        
        String first_name = (String) jo.get("first_name");
        String last_name = (String) jo.get("last_name");
        String date_of_birth = (String) jo.get("date_of_birth");
        String aadahr_id = (String) jo.get("aadahr_id");
        String gender = (String) jo.get("gender");
        String email = (String) jo.get("email");
        String username = (String) jo.get("username");
        String password = (String) jo.get("password");
        
        String query = ("insert into user values(\""+first_name+"\",\""+last_name+"\",\""
                                                  +date_of_birth+"\",\""+aadahr_id+"\",\""+gender
                                                        +"\",\""+email+"\",\""+username+"\",\""+password+"\");");
             
        System.out.println(query);
         
        
    }
}

