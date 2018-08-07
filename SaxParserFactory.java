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
   
  public SaxParserFactory()
  {
    try
    {
      SAXParserFactory parserFactor = SAXParserFactory.newInstance();
      SAXParser parser = parserFactor.newSAXParser();
      SaxHandler handler = new SaxHandler();
      parser.parse(ClassLoader.getSystemResourceAsStream("employee.xml"),handler);

    //Printing the list of employees obtained from XML
      DatabaseConnection databaseConnection=new DatabaseConnection();
      for ( Employee emp : handler.empList)
      {
        
        databaseConnection.DatabaseConnection1(emp);
        //con.insert(emp);
      }
      databaseConnection.closeConnection();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }

  }

}

 
