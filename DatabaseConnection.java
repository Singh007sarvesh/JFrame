import java.sql.*;
public class DatabaseConnection
{
	Statement statement;
	Connection connection;
	DatabaseConnection()throws Exception
  	{
  		//Class.forName("com.mysql.jdbc.Driver");  
  		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");    
	    statement=connection.createStatement();
  	}
	public void DatabaseConnection1(Employee emp)
  	{ 
	    try
	    {     
	              
	      //SaxParserFactory saxParserFactory=new SaxParserFactory();
	    	EmailValidate emailValidate=new EmailValidate();
	    	if(emailValidate.isEmailValid(emp.email));
	    	else
	    	{
	    		System.out.println("Plz Enter Valid Email");
	        	return;
	      	}
	      	String sql= ("insert into emp_records values(\""+emp.firstName+"\",\""+emp.lastName+"\",\" " +emp.dob+"\",\""+emp.aadharId+"\",\""+emp.gender
	                                                        +"\",\""+emp.email+"\",\""+emp.userName+"\",\""+emp.password+"\");");
	      	statement.executeUpdate(sql);  
	    }
	    catch(Exception e)
	    { 
	      System.out.println(e);
	                
	    }  
  	}
  	public void closeConnection() 
  	{
  		try
  		{
  			connection.close();
  		}
  		catch(Exception ex)
  		{
  			System.out.println(ex);
  		}
  	}  
}