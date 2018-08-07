import java.sql.*;  
public class Insert extends DOM
{   
  public static void main(String[] args)
  { 

   
    try
      {     
        // Class.forName("com.mysql.jdbc.Driver");     
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");    
        Statement stmt=con.createStatement();   
        String sql= ("insert into emp_records values(\""+emp.firstname+"\",\""+emp.lastname+"\",\""
                                                  +emp.dob+"\",\""+emp.aadahr_id+"\",\""+emp.gender
                                                        +"\",\""+emp.email+"\",\""+emp.username+"\",\""+emp.password+"\");");
      // String sql ="insert into emp_records(first_name,last_name,DOB,aadahr_id,gender,email,username,password)values(emp.firstname,emp.lastname,emp.dob,emp.aadahr_id,emp.gender,emp.email,emp.username,emp.password)";
       //String sql ="insert into emp_records(first_name,last_name,DOB,aadahr_id,gender,email,username,password)values("sarvesh","singh","15/05/1994","123456789125","male","sar@codekraft.in","sar","123")";  
      // System.out.println(sql);

      stmt.executeUpdate(sql);// while(rs.next())    
          //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));   
          con.close();     
      }
      catch(Exception e)
      { 
        System.out.println(e);
                
      }  
  }      
}


