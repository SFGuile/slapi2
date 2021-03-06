package com.slyynewwebservice.web.api;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


 


import com.slyynewwebservice.models.QueryProducedate;
import com.slyynewwebservice.models.QueryProducedateDetails;
import com.slyynewwebservice.models.QueryProducedateRet;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller

public class QueryProducedateController {
	
	@RequestMapping(value="/queryproducedate",
	        method = RequestMethod.POST,
	        consumes = "application/json; charset=UTF-8",
	        produces = "application/json; charset=UTF-8")

	@ResponseBody
	public QueryProducedateRet  queryproducedate(@RequestBody QueryProducedate producedateupload) throws SQLException, ClassNotFoundException
	{
		QueryProducedateRet queryproduce=new QueryProducedateRet();
		List<QueryProducedateDetails> producedatelist=new ArrayList<QueryProducedateDetails>();
		queryproduce.setRetcode(0);
		queryproduce.setProdno(producedateupload.getProdno());
		queryproduce.setBatchno(producedateupload.getBatchno());
		queryproduce.setProdadd(producedateupload.getProdadd());
		queryproduce.setRecordscount(0);
		queryproduce.setRetmsg("");
		
	    Connection conn = null;
        ResultSet rs = null;
        PreparedStatement p =null;
        Connection connserver = null;
        ResultSet rsserver = null;
        ResultSet rsprodet = null;
        PreparedStatement pserver =null;
        PreparedStatement pprodet =null;
      
     
	  
	       String url = "jdbc:jtds:sqlserver://192.168.0.241/dataexchange";
	       
	        
	        String driver = "net.sourceforge.jtds.jdbc.Driver";
	        String sqluserName = "sa";
	        String sqlpassword = "";
	        try {
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url, sqluserName, sqlpassword);
	            p = conn.prepareStatement("SELECT COUNT(*) as total FROM cli_jflogin where cli_no=? and password=?");
	            p.setString(1,producedateupload.getUsername());
	            p.setString(2, producedateupload.getPassword());
	            rs=p.executeQuery();
	        
	            int myexists=0;
	            rs.next();
	            myexists=rs.getInt("total");
	            if(myexists==0)
	            {
	            	queryproduce.setRetcode(-11);
	            	return queryproduce;
	            }
	        }
	        catch(SQLException se)
	        {
	        	queryproduce.setRetcode(-12);
	        	String expstr=se.toString();
	         
           	if (expstr.length()>1000)
           	{
           		expstr.substring(0, 999);
           	}
           	  queryproduce.setRetmsg(expstr);
   			   return queryproduce;
	        }
	        catch(ClassNotFoundException ce){
	        	queryproduce.setRetcode(-13);
	        	String expstr=ce.toString();
	         
           	if (expstr.length()>1000)
           	{
           		expstr.substring(0, 999);
           	}
                queryproduce.setRetmsg(expstr);
			   return queryproduce;
	         }
	        finally
	        {
	        	   if(rs!=null)
		        	try
		        	{
		              rs.close();
		              rs=null;
		        	} 
		        	catch (SQLException e) {
		        	queryproduce.setRetcode(-13);
		        	String expstr=e.toString();
		         
	            	if (expstr.length()>1000)
	            	{
	            		expstr.substring(0, 999);
	            	}
	            	
	            	  queryproduce.setRetmsg(expstr);
	   			      return queryproduce;
	   			      
	               }
		        	if(p!=null)
		        	try
		        	{
		        	  p.close();
		        	  p=null;
		        	}
		        	catch (SQLException e) {
		        		queryproduce.setRetcode(-14);
			        	String expstr=e.toString();
			         
		            	if (expstr.length()>1000)
		            	{
		            		expstr.substring(0, 999);
		            	}
		            	  queryproduce.setRetmsg(expstr);
		   			      return queryproduce;
		               }
	        	if(conn!=null && !conn.isClosed())
		        	try
		        	{
			            conn.close();
			            conn=null;
		        	}
		        	catch (SQLException e) {
		        		queryproduce.setRetcode(-15);
			        	String expstr=e.toString();
			         
		            	if (expstr.length()>1000)
		            	{
		            		expstr.substring(0, 999);
		            	}
		            	  queryproduce.setRetmsg(expstr);
		   			      return queryproduce;
		               }
	        }
	        
	        String[] serveradd=new String[3];
	        serveradd[0]="jdbc:jtds:sqlserver://192.168.0.254/yyxt01";
	        serveradd[1]="jdbc:jtds:sqlserver://192.168.0.254/yyxt02";
	        serveradd[2]="jdbc:jtds:sqlserver://192.168.0.240/yyxt03";
	        int myrecordcounts=0;
	        for (int i=0;i<3;i++)
	        {
	        	try {
	            connserver = DriverManager.getConnection(serveradd[i], sqluserName, sqlpassword);
		           
	            pserver = connserver.prepareStatement("SELECT COUNT(*) as total FROM prod_det where prod_no=? and batch_no=? and prod_add=?");
	            pserver.setString(1,producedateupload.getProdno());
	            pserver.setString(2, producedateupload.getBatchno());
	            pserver.setString(3, producedateupload.getProdadd());
	            rsserver=pserver.executeQuery();
	     
	            rsserver.next();
	            int myexists=rsserver.getInt("total");
	            if (myexists>0)
	            {
	            	if (i!=2)
	            	{
	            	   pprodet = connserver.prepareStatement("SELECT produce_date FROM prod_det where prod_no=? and batch_no=? and prod_add=?");
	            	   pprodet.setString(1,producedateupload.getProdno());
	            	   pprodet.setString(2,producedateupload.getBatchno());
	            	   pprodet.setString(3,producedateupload.getProdadd());
	            	   rsprodet=pprodet.executeQuery();
	            	    
	            	
	                   while (rsprodet.next()) {
	                	   String produceStr = rsprodet.getString("produce_date");
	                	   QueryProducedateDetails  producedetail=new QueryProducedateDetails();
	                	   producedetail.setProducedate(produceStr);
	                	   producedetail.setBelongto(i+1);
	                	   producedatelist.add( producedetail);
	                	   
	                	   myrecordcounts++;
	                    
	                   }
	            	}
	            	else
	            	{
	            		   QueryProducedateDetails  producedetail=new QueryProducedateDetails();
	                	   producedetail.setProducedate("2100-01-01");
	                	   producedetail.setBelongto(i+1);
	                	   producedatelist.add( producedetail);
	                	   myrecordcounts++;
	            	}
	            	
	            	
	            }
	            
	            
	            
	            }
	        	
	        	catch(SQLException se)
		        {
		        	queryproduce.setRetcode(-12);
		        	String expstr=se.toString();
		         
	           	if (expstr.length()>1000)
	           	{
	           		expstr.substring(0, 999);
	           	}
	           	  queryproduce.setRetmsg(expstr);
	   			   return queryproduce;
		        }
		       
		        finally
		        {
		        	   if(rs!=null)
			        	try
			        	{
			              rs.close();
			              rs=null;
			        	} 
			        	catch (SQLException e) {
			        	queryproduce.setRetcode(-13);
			        	String expstr=e.toString();
			         
		            	if (expstr.length()>1000)
		            	{
		            		expstr.substring(0, 999);
		            	}
		            	
		            	  queryproduce.setRetmsg(expstr);
		   			      return queryproduce;
		   			      
		               }
			        	if(p!=null)
			        	try
			        	{
			        	  p.close();
			        	  p=null;
			        	}
			        	catch (SQLException e) {
			        		queryproduce.setRetcode(-14);
				        	String expstr=e.toString();
				         
			            	if (expstr.length()>1000)
			            	{
			            		expstr.substring(0, 999);
			            	}
			            	  queryproduce.setRetmsg(expstr);
			   			      return queryproduce;
			               }
		        	if(conn!=null && !conn.isClosed())
			        	try
			        	{
				            conn.close();
				            conn=null;
			        	}
			        	catch (SQLException e) {
			        		queryproduce.setRetcode(-15);
				        	String expstr=e.toString();
				         
			            	if (expstr.length()>1000)
			            	{
			            		expstr.substring(0, 999);
			            	}
			            	  queryproduce.setRetmsg(expstr);
			   			      return queryproduce;
			               }
		        }
	        	
	            
	        }
	        
	       
	        queryproduce.setRecordscount(myrecordcounts);
	        if ( myrecordcounts==0)
	        	queryproduce.setRetcode(-16);
	        else
	        	queryproduce.setProducedetails(producedatelist);
	        
	        
		return queryproduce;
	}

}
