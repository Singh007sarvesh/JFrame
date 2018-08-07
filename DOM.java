import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.*;
 
public class SaxParserFactory
{
  public static boolean isEmailValid(String email)
  {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";
                             
    Pattern pat = Pattern.compile(emailRegex);
    if (email == null)
        return false;
        return pat.matcher(email).matches();
  }     
  public static void main(String[] args) throws Exception
  {
    SAXParserFactory parserFactor = SAXParserFactory.newInstance();
  
    SAXParser parser = parserFactor.newSAXParser();
  
    SAXHandler handler = new SAXHandler();
    parser.parse(ClassLoader.getSystemResourceAsStream("employee.xml"),handler);

    //Printing the list of employees obtained from XML

    for ( Employee emp : handler.empList)
    {
      Connection con=new Connection();
      con.insert(emp);
    }
  }
}

 
