package com.slyynewwebservice.web.api;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.slyynewwebservice.models.Gjret;
import com.slyynewwebservice.models.Gjupload;
import com.slyynewwebservice.models.Details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





@Controller
public class UploadgjbnewController {
	
	

	@RequestMapping(value ="/gjbuploadnew",
			        method = RequestMethod.POST,
			        consumes = "application/json; charset=UTF-8",
			        produces = "application/json; charset=UTF-8")

	@ResponseBody
	public Gjret uploadgjbnew(@RequestBody Gjupload gjbupload) throws SQLException, ClassNotFoundException{
		
		Gjret gjretdetails=new Gjret();
		gjretdetails.setIflackofproduct(0);
		gjretdetails.setReturncode(0);
		gjretdetails.setExpmsg("");
		
	
		   String username=gjbupload.getUsername();
		   String password=gjbupload.getPassword();
		   int belongto=gjbupload.getBelongto();
		   String listno=gjbupload.getListno();
		   String gjbno=gjbupload.getGjbno();
		   String localtime=gjbupload.getLocaltime();
		
		
		
		
		if(username== null || username.isEmpty() 
			|| password== null || password.isEmpty()
			
			|| listno==null || listno.isEmpty()
	    	|| gjbno==null || gjbno.isEmpty()
			|| localtime==null || localtime.isEmpty())
		{
			gjretdetails.setReturncode(-1);
			return gjretdetails;
		}
		Logger logger = LoggerFactory.getLogger("com.slyynewwebservice.web.api.UploadgjbnewController");
		 logger.info(username+"传入json"); 
		List<Details> detailist = new ArrayList<Details>(gjbupload.getMydetails());
		
		for(int i = 0; i < detailist.size(); i++)
		{
			String prodno=detailist.get(i).getProdno();
			double neednum=detailist.get(i).getNeednum();
			if(prodno== null || prodno.isEmpty() || neednum==0.0 )
			{
				gjretdetails.setReturncode(-1);
				return gjretdetails;
			}	
			
		}
		
		if (belongto!=1 && belongto!=2 && belongto!=3)
		{
			gjretdetails.setReturncode(-3);
			return gjretdetails;
		}
		
		    Connection conn = null;
	        ResultSet rs = null;
	        PreparedStatement p =null;
	        Connection connserver = null;
	        ResultSet rsserver = null;
	        ResultSet rsprodep = null;
	        PreparedStatement pserver =null;
	        PreparedStatement pprodep =null;
	        PreparedStatement updateGjdmain = null;
	        PreparedStatement updateGjdsub = null;
	        PreparedStatement updateGjdlog = null;
	  
	       String url = "jdbc:jtds:sqlserver://192.168.0.254/dataexchange";
	       
	        
	        String driver = "net.sourceforge.jtds.jdbc.Driver";
	        String sqluserName = "sa";
	        String sqlpassword = "";
	        try {
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url, sqluserName, sqlpassword);
	            p = conn.prepareStatement("SELECT COUNT(*) as total FROM cli_jflogin where cli_no=? and password=?");
	            p.setString(1,username);
	            p.setString(2, password);
	            rs=p.executeQuery();
	            logger.info(username+"连接dataexchange");
	            int myexists=0;
	            rs.next();
	            myexists=rs.getInt("total");
	            if(myexists==0)
	            {
	            	gjretdetails.setReturncode(-2);
	    			return gjretdetails;
	            }
	        }
	        catch(SQLException se)
	        {
	        	gjretdetails.setReturncode(-11);
	        	String expstr=se.toString();
	        	logger.error(username+"发生错误27："+expstr); 
            	if (expstr.length()>1000)
            	{
            		expstr.substring(0, 999);
            	}
	        	gjretdetails.setExpmsg(expstr);
    			return gjretdetails;
	        }
	        catch(ClassNotFoundException ce){
	        	gjretdetails.setReturncode(-6);
	        	String expstr=ce.toString();
	        	logger.error(username+"发生错误5："+expstr); 
            	if (expstr.length()>1000)
            	{
            		expstr.substring(0, 999);
            	}
	        	gjretdetails.setExpmsg(expstr);
    			return gjretdetails;
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
		        	gjretdetails.setReturncode(-11);
		        	String expstr=e.toString();
		        	logger.error(username+"发生错误7："+expstr); 
	            	if (expstr.length()>1000)
	            	{
	            		expstr.substring(0, 999);
	            	}
		        	gjretdetails.setExpmsg(expstr);
	    			return gjretdetails;
	               }
		        	if(p!=null)
		        	try
		        	{
		        	  p.close();
		        	  p=null;
		        	}
		        	catch (SQLException e) {
			        	gjretdetails.setReturncode(-11);
			        	String expstr=e.toString();
			        	logger.error(username+"发生错误8："+expstr); 
		            	if (expstr.length()>1000)
		            	{
		            		expstr.substring(0, 999);
		            	}
		    			return gjretdetails;
		               }
	        	if(conn!=null && !conn.isClosed())
		        	try
		        	{
			            conn.close();
			            conn=null;
		        	}
		        	catch (SQLException e) {
			        	gjretdetails.setReturncode(-11);
			        	String expstr=e.toString();
			        	logger.error(username+"发生错误12："+expstr); 
		            	if (expstr.length()>1000)
		            	{
		            		expstr.substring(0, 999);
		            	}
		    			return gjretdetails;
		               }
	        }
	        try
	        {
	            if (belongto==1)
	            {
	                url = "jdbc:jtds:sqlserver://192.168.0.254/yyxt01";
	           
	            
	            	 
	            }
	            else if  (belongto==2)
	            {
	            	url = "jdbc:jtds:sqlserver://192.168.0.254/yyxt02";
	             
	            }
	            else if  (belongto==3)
	            {
	            	url = "jdbc:jtds:sqlserver://192.168.0.240/yyxt03";
	            	 
	            }
	            
	           
	            logger.info(username+"连接server");
	            connserver = DriverManager.getConnection(url, sqluserName, sqlpassword);
	           
	            pserver = connserver.prepareStatement("SELECT COUNT(*) as total FROM gjd_log where gjb_no=? and cli_no=?");
	            pserver.setString(1,gjbno);
	            pserver.setString(2, username);
	            rsserver=pserver.executeQuery();
	            logger.info(username+"执行查询1");
	            rsserver.next();
	            int myexists=rsserver.getInt("total");
	            if (myexists>0)
	            {
	            	gjretdetails.setReturncode(-8);
	    			return gjretdetails;
	            }
	            
	            
	            List<Details> retdetailist = new ArrayList<Details>();
	            int lackcount=0;
	            for(int i = 0; i < detailist.size(); i++)
	    		{
	            	
	            	
	            	pprodep = connserver.prepareStatement("SELECT COUNT(*) as total FROM prod_dep where prod_no=?");
	            	pprodep.setString(1,detailist.get(i).getProdno());
	            	rsprodep=pprodep.executeQuery();
		            logger.info(username+"执行查询2");
		            rsprodep.next();
		            myexists=rsprodep.getInt("total");
		            if (myexists==0)
		            {
		            	Details dt=new Details();
		            	dt.setProdno(detailist.get(i).getProdno());
		            	dt.setNeednum(detailist.get(i).getNeednum());
		            	retdetailist.add(dt);
		            	gjretdetails.setIflackofproduct(1);
		            	lackcount++;
		            }
	    		}
	            
	            if(lackcount>0)
	            gjretdetails.setMylackdetails(retdetailist);
	            
	            if (lackcount==detailist.size())
	            {
	            	gjretdetails.setReturncode(-10);
		            return gjretdetails;
	            }
	            
	            
	            connserver.setAutoCommit(false);
	            String insertgjdmainString =
	                    "insert gjd_main ([list_no]"+
                        ",[cli_no],[local_time],[remote_time]" +
                        ",[belong_to],[now_status],[gjb_no]) " +
	                    "values (?,?,?,getdate(),?,2,?)";

	                String insertgjdsubString =
	                    "insert gjd_sub ([list_no]"+
           ",[prod_no],[need_num],[status],[lest_time])"+
	                    		" values (?,?,?,2,?)";

	                
	               String insertgjdlogString="insert gjd_log ([gjb_no],[cli_no])" +
	                      " values (?,?)";
	            updateGjdmain= connserver.prepareStatement(insertgjdmainString);
	            updateGjdmain.setString(1,listno);
	            updateGjdmain.setString(2, username);
	            updateGjdmain.setString(3,localtime);
	            updateGjdmain.setInt(4, belongto);
	            updateGjdmain.setString(5,  gjbno);
	            updateGjdmain.executeUpdate();
	            logger.info(username+"执行上传1");
	            updateGjdlog= connserver.prepareStatement(insertgjdlogString);
	            updateGjdlog.setString(1, gjbno);
	            updateGjdlog.setString(2, username);
	            updateGjdlog.executeUpdate();
	            logger.info(username+"执行上传2");
	            
	            updateGjdsub=connserver.prepareStatement(insertgjdsubString);
	            for(int i = 0; i < detailist.size(); i++)
	    		{
	    			String prodno=detailist.get(i).getProdno();
	    			double neednum=detailist.get(i).getNeednum();
	    			updateGjdsub.setString(1, listno);
	    			updateGjdsub.setString(2, prodno);
	    			updateGjdsub.setDouble(3,neednum ); 
	    			updateGjdsub.setString(4, localtime);
	    			updateGjdsub.addBatch();
	    			
	    		}
	            
	            updateGjdsub.executeBatch();
	            logger.info(username+"执行上传3");
	            connserver.commit();
	            return gjretdetails;
	            
	        }  catch(SQLException se){
	            //Handle errors for JDBC
	          
	      	  try{
	      		 if(connserver!=null)
	                  connserver.rollback();
	            }catch(SQLException se2){
	            	gjretdetails.setReturncode(-5);
	            	String expstr=se2.toString();
	            	logger.error(username+"发生错误3："+expstr); 
	            	if (expstr.length()>1000)
	            	{
	            		expstr.substring(0, 999);
	            	}
		        	gjretdetails.setExpmsg(expstr);
	    			return gjretdetails;
	            }//end try
	        	gjretdetails.setReturncode(-4);
	        	String expstr=se.toString();
	        	logger.error(username+"发生错误4："+expstr); 
            	if (expstr.length()>1000)
            	{
            		expstr.substring(0, 999);
            	}
	        	gjretdetails.setExpmsg(expstr);
    			return gjretdetails;
	            
	        }
	     /*   catch(ClassNotFoundException ce){
	        	gjretdetails.setReturncode(-6);
	        	String expstr=ce.toString();
	        	logger.error(username+"发生错误5："+expstr); 
            	if (expstr.length()>1000)
            	{
            		expstr.substring(0, 999);
            	}
	        	gjretdetails.setExpmsg(expstr);
    			return gjretdetails;
	         }*/
	        catch(Exception e){
	        	gjretdetails.setReturncode(-7);
	        	String expstr=e.toString();
	        	logger.error(username+"发生错误6："+expstr); 
            	if (expstr.length()>1000)
            	{
            		expstr.substring(0, 999);
            	}
	        	gjretdetails.setExpmsg(expstr);
    			return gjretdetails;
	         }
	        finally {
	        	
	        	
	        	if(updateGjdmain!=null)
	        	try
	        	{
	        	  updateGjdmain.close();
	        	  updateGjdmain=null;
	        	}
	        	catch (SQLException e) {
		        	gjretdetails.setReturncode(-11);
		        	String expstr=e.toString();
		        	logger.error(username+"发生错误9："+expstr); 
	            	if (expstr.length()>1000)
	            	{
	            		expstr.substring(0, 999);
	            	}
	    			return gjretdetails;
	               }
	        	if(updateGjdsub!=null)
	        	try
	        	{
	        	   updateGjdsub.close();
	        	   updateGjdsub=null;
	        	}
	        	catch (SQLException e) {
		        	gjretdetails.setReturncode(-11);
		        	String expstr=e.toString();
		        	logger.error(username+"发生错误10："+expstr); 
	            	if (expstr.length()>1000)
	            	{
	            		expstr.substring(0, 999);
	            	}
	    			return gjretdetails;
	               }
	        	
	        	if( updateGjdlog!=null)
		        	try
		        	{
		        		 updateGjdlog.close();
		        		 updateGjdlog=null;
		        	}
		        	catch (SQLException e) {
			        	gjretdetails.setReturncode(-11);
			        	String expstr=e.toString();
			        	logger.error(username+"发生错误11："+expstr); 
		            	if (expstr.length()>1000)
		            	{
		            		expstr.substring(0, 999);
		            	}
		    			return gjretdetails;
		               }
	        	
	        	if(rsserver!=null)
            	{
            		try
            		{
            			rsserver.close();
            			rsserver=null;
            	   }
            		catch(SQLException se2){
    	            	gjretdetails.setReturncode(-9);
    	            	String expstr=se2.toString();
    	            	 logger.error(username+"发生错误1："+expstr); 
    	            	if (expstr.length()>1000)
    	            	{
    	            		expstr.substring(0, 999);
    	            	}
    		        	gjretdetails.setExpmsg(expstr);
    	    			return gjretdetails;
    	            }
            	}
            	if(pserver!=null)
            	{
            	   try
            	   {
            		   pserver.close();
            		   pserver=null;
            	   }
            		catch(SQLException se2){
    	            	gjretdetails.setReturncode(-9);
    	            	String expstr=se2.toString();
    	            	logger.error(username+"发生错误2："+expstr); 
    	            	if (expstr.length()>1000)
    	            	{
    	            		expstr.substring(0, 999);
    	            	}
    		        	gjretdetails.setExpmsg(expstr);
    	    			return gjretdetails;
    	            }
            	}
            	if(rsprodep!=null)
            	{
            		try
            		{
            			rsprodep.close();
            			rsprodep=null;
            	   }
            		catch(SQLException se2){
    	            	gjretdetails.setReturncode(-9);
    	            	String expstr=se2.toString();
    	            	 logger.error(username+"发生错误21："+expstr); 
    	            	if (expstr.length()>1000)
    	            	{
    	            		expstr.substring(0, 999);
    	            	}
    		        	gjretdetails.setExpmsg(expstr);
    	    			return gjretdetails;
    	            }
            	}
            	if(pprodep!=null)
            	{
            	   try
            	   {
            		   pprodep.close();
            		   pprodep=null;
            	   }
            		catch(SQLException se2){
    	            	gjretdetails.setReturncode(-9);
    	            	String expstr=se2.toString();
    	            	logger.error(username+"发生错误22："+expstr); 
    	            	if (expstr.length()>1000)
    	            	{
    	            		expstr.substring(0, 999);
    	            	}
    		        	gjretdetails.setExpmsg(expstr);
    	    			return gjretdetails;
    	            }
            	}
	        	
	        	
	        	
	             }
		
		
	}
	
}
