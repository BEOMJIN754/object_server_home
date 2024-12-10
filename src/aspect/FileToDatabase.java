package aspect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FileToDatabase {

	public static void main(String[] args) {
		  String filePath = "data/basicArchitecture.txt"; 
	        String dbUrl = "jdbc:mysql://localhost:3306/objectlms"; 
	        String dbUser = "root"; 
	        String dbPassword = "0304"; 
	        
	        String insertQuery = "INSERT INTO basicArchitecture (mId, mName, professor, credit, nRoot) VALUES (?, ?, ?, ?, ?)";
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
	             Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
	            
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split("\\s+", 5); 
	                
	                int mId = Integer.parseInt(parts[0]);  
	                String mName = parts[1];             
	                String professor = parts[2];          
	                int credit = Integer.parseInt(parts[3]); 
	                String nRoot = parts[4];             

	                pstmt.setInt(1, mId);
	                pstmt.setString(2, mName);
	                pstmt.setString(3, professor);
	                pstmt.setInt(4, credit);
	                pstmt.setString(5, nRoot);
	                
	                pstmt.executeUpdate();
	            }
	            System.out.println("File data inserted into database successfully.");
//	        String insertQuery = "INSERT INTO law (mId, mName, nRoot) VALUES (?, ?, ?)";
//	        
//	        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
//		             Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//		             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
//		            
//		            String line;
//		            while ((line = br.readLine()) != null) {
//		                String[] parts = line.split("\\s+", 3); 
//		                
//		                int mId = Integer.parseInt(parts[0]);  // 강좌 ID
//		                String mName = parts[1];              // 강좌명
//		                String nRoot = parts[2];              // 수업 시간 (nRoot)
//
//		                pstmt.setInt(1, mId);
//		                pstmt.setString(2, mName);
//		                pstmt.setString(3, nRoot);
//		              
//		                pstmt.executeUpdate();
//		            }
//	            
//	            System.out.println("File data inserted into database successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
