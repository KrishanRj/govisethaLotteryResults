
package govisethalotteryresults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author KRISHAN
 */
public class Database {
    
    public static Statement getStatement(){
        try{
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1","root",""); 
            Statement statement = con.createStatement();
            return statement;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
