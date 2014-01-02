import java.io.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DocFreq {
	
	 	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost/abtdb";
	    static final String USER = "root";
	   
	    
	    public static void main(String[] args) throws IOException {

	    	   
	    		Set<String> words = new HashSet<String>();
	    	
	    		for(int m=34; m<36; m++)
	    		{
	    			System.out.println(m);
	    		File file = new File("C:/Users/abtpst/Desktop/HW/NLP/Mails/Training Corpus/second coming/"+m+".txt");
	            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	            String line = null;
	            while( (line = br.readLine())!= null ){
	                    // \\s+ means any number of whitespaces between tokens
	               // line.replaceAll("[^a-zA-Z0-9\\s]", " ");
	            	//System.out.println(line);
	            	String [] tokens = line.split("[^a-zA-Z0-9\\-\']");
	                for(String g : tokens)
	                {
	                	g=g.toLowerCase();
	                //	System.out.println(g);
	                	//g=g.replace("'", "");
	    	            words.add(g);
	                }
	            }
	         //System.out.println(words);
	    	 Connection c = null;
	         Statement s = null;
	         PreparedStatement pstmt=null;
	         words.remove(null);
	         for(String w : words)
	         {
	        	
	         try
	         {
	            
	        	Class.forName("com.mysql.jdbc.Driver");
	            c = DriverManager.getConnection(DB_URL,USER,"");
	            
	            s=c.createStatement();
	            
	            pstmt = c.prepareStatement("select * from JapeTab where Term=?");
	            pstmt.setString(1, w);
	            //sql = "select * from JapeTab where Term='"+w+"'";
	            ResultSet rs = pstmt.executeQuery();
	            int val=0;
	            
	            if(rs.next())
	            {
	            
	            		val=Integer.parseInt(rs.getString(2))+1;
	            		pstmt = c.prepareStatement("update JapeTab set DocumentFreq=? where Term=?");
	            		pstmt.setInt(1, val);
	            		pstmt.setString(2, w);
	            		pstmt.executeUpdate(); 
	            	//sql = "update JapeTab set DocumentFreq="+val+" where Term='"+w+"'";
	            	//s.executeUpdate(sql);
	            }
	            
	            else
	            {
	            	pstmt = c.prepareStatement("insert into JapeTab values(?,1)");
	            	pstmt.setString(1, w);
	            	pstmt.executeUpdate();
	            	//sql = "insert into JapeTab values('"+w+"',1)";
	            	//s.executeUpdate(sql);	            	
	            }
	            
	            pstmt.close();
	            s.close();
	            c.close();
	            
	        }
	        
	        catch (SQLException se)
	        {
	            se.printStackTrace();
	        }
	        
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        finally 
	        {
	            try
	            {
	                if(s!=null)
	                    s.close();
	                if(pstmt!=null)
	                pstmt.close();
	                
	            }
	            
	            catch (SQLException se2)
	            {}
	            
	        }
	        
	        try 
	        {
	            if(c!=null)
	                c.close();
	        }
	        
	        catch(SQLException se)
	        {
	            se.printStackTrace();
	        }
	    
	    }
	         Connection cd = null;
	         Statement sd = null;
	         
	         try
	         {
	            
	        	Class.forName("com.mysql.jdbc.Driver");
	            cd = DriverManager.getConnection(DB_URL,USER,"");
	            
	            sd=cd.createStatement();
	            
	            String sql;
	            
	            sql = "delete from japetab where Term=''";
	            sd.executeUpdate(sql);
	           
	            sd.close();
	            cd.close();
	            
	        }
	        
	        catch (SQLException se)
	        {
	            se.printStackTrace();
	        }
	        
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        finally 
	        {
	            try
	            {
	                if(sd!=null)
	                    sd.close();
	                
	            }
	            
	            catch (SQLException se2)
	            {}
	            
	        }
	        
	        try 
	        {
	            if(cd!=null)
	                cd.close();
	        }
	        
	        catch(SQLException se)
	        {
	            se.printStackTrace();
	        }
	    
	    }
	    }
	    
	}


