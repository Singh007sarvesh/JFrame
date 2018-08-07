import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class JSON 
{
    public static void main(String[] args) throws Exception 
    {

        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/codekraft","root","");  
        Statement stmt=con.createStatement(); 
        Object obj = new JSONParser().parse(new FileReader("employee.json"));
        JSONArray emp = (JSONArray) obj;
        Iterator<String> i = emp.iterator();
        while(i.hasNext())
        {

            Object a = (Object)i.next();
            JSONObject j = (JSONObject)a;
            JSONObject jo= (JSONObject)j.get("employee");
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
            stmt.executeUpdate(query);

        }
        con.close();
    }
}
