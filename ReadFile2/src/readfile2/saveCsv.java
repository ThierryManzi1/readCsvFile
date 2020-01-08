/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class saveCsv {
//    private String empName;
//    private int empSalary;
//    private int empDeptId;
//    
//    public void readdata(){
//        try(Scanner input = new Scanner(new File("src/readfile2/emp_data.txt"))){
//            while(input.hasNextLine()){
//                empName="";
//                String line;
//                
//                line = input.nextLine();
//                
//                //if line variable has no data ther re-iterate the loop
//                if(line.length()<=0)
//                    continue;
//                
//                //now proces the line of text for each data item
//                try(Scanner data = new Scanner(line)){
//                    while(!data.hasNextInt()){
//                        empName+=data.next()+" ";
//                    }
//                    empName = empName.trim();
//                    
//                    //get salary
//                    if(data.hasNextInt()){
//                        empSalary = data.nextInt();
//                    }
//                    
//                    //get department id
//                    if (data.hasNextInt()){
//                        empDeptId = data.nextInt();
//                    }
//                }
//                
//                
//                //check data
//                //System.out.println(empName+"\t"+empSalary+"\t"+empDeptId+"\t");
//                saveData();//call the method to save the data into the database
//            }
//        }catch(IOException e){
//            System.out.print(e.getMessage());
//        }
//            
//    }
//   //now save the data into the database
//    private void saveData(){
//        try(Connection conn = connect();
//                PreparedStatement pstat = conn.prepareStatement("INSERT INTO employee VALUES(?,?,?)")){
//        
//            pstat.setString(1, empName);
//            pstat.setInt(2, empSalary);
//            pstat.setInt(3, empDeptId);
//            
//            pstat.executeUpdate();
//        
//        }catch(SQLException e){
//                System.out.println(e);
//                }
//     
//    }
     public void displayData(){
            try(Connection conn = connect();
                    Statement stat = conn.createStatement()){
                boolean hasResultSet= stat.execute("SELECT * FROM employee");
                if (hasResultSet){
                    ResultSet result = stat.getResultSet();
                    ResultSetMetaData metaData = result.getMetaData();
                    
                    //get number of columns
                    int columnCount = metaData.getColumnCount();
                    
                    //display column labels
                    for (int i=1; i<= columnCount; i++){
                        System.out.print(metaData.getColumnLabel(i)+"\t\t");
                    }
                    System.out.println();
                    // display data
                    while(result.next()){
                        System.out.printf("%-20s%10d%13d%n", result.getString("emp_name"), result.getInt("salary"), result.getInt("deptId"));
                    }
                    
                }
            }catch(SQLException e){
                System.out.println(e);
            }
                    
        }
    public void storeData(){
        try{
             BufferedReader br = new BufferedReader(new FileReader("src/readfile2/emp_data.txt"));
             String  line;
             
             while((line=br.readLine())!=null){
                 String[] value = line.split(",");
                 String sql ="INSERT INTO employee (emp_name,salary,deptId) " +"values('"+value[0]+"','"+value[1]+"','"+value[2]+"' )";
                 Connection conn = connect();
                 PreparedStatement pst = conn.prepareStatement(sql);
                 pst.executeUpdate();
             }
             br.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
    }    
    //create a connection into the database
    private Connection connect() {
        //Class.forName("com.mysql.jdbc.Driver");
        Connection con = null;
       String ret = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/employee", "root", "titi");
             //System.out.print("Connected");
        } catch (Exception e) {
            ret = e.getMessage();
           e.printStackTrace();
           return null;
        }
        
        return con;
        
    }



}