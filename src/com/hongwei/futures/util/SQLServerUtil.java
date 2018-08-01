package com.hongwei.futures.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import com.hongwei.futures.model.FuServer;

public class SQLServerUtil {
	private static Connection connect=null;  
    private static String driver=null;  
    private static String url=null;  
    private static String user=null;  
    private static String pwd=null;  
    /** 
     * 获取链接 
     * @return 
     */  
    public static Connection getConnect(FuServer server){  
        try{  
            driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
            url="jdbc:sqlserver://" + server.getDbIp() + ":" + server.getDbPort() + ";databaseName=" + server.getDbName() + ";Connect Timeout=500";
            user=server.getDbUsername();  
            pwd=server.getDbPassword();  
            Class.forName(driver);  
            //获取数据库链接  
            if (null == connect || connect.isClosed()) {
            	connect=DriverManager.getConnection(url,user,pwd);  
            	System.out.println(url);
            }
            return connect;  
        }catch(Exception e){  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    /** 
     * 准备SQL参数，并进行相应类型转换 
     * @param ps 
     * @param params 
     */  
    public static void PrepareCommand(PreparedStatement ps,Object[] params){  
        if(params==null||params.length==0){  
            return;  
        }  
        try{  
            for(int i=0;i<params.length;i++){  
                int parameterIndex=i+1;  
                if(params[i].getClass()==String.class){  
                    ps.setString(parameterIndex, params[i].toString());  
                }  
                else if(params[i].getClass()==short.class){  
                    ps.setShort(parameterIndex,Short.parseShort((String) params[i]) );  
                }  
                else if(params[i].getClass()==long.class){  
                    ps.setLong(parameterIndex, Long.parseLong((String) params[i]));  
                }  
                else if(params[i].getClass()==Integer.class){  
                    ps.setInt(parameterIndex, Integer.valueOf(params[i].toString()));  
                }  
                else if(params[i].getClass()==Date.class){  
                    java.util.Date date=(java.util.Date) params[i];  
                    ps.setDate(parameterIndex, new java.sql.Date(date.getTime()));  
                }  
                else if(params[i].getClass()==byte.class){  
                    ps.setByte(parameterIndex, (Byte)params[i]);  
                }  
                else if(params[i].getClass()==float.class){  
                    ps.setFloat(parameterIndex, Float.parseFloat((String) params[i]));  
                }  
                else if(params[i].getClass()==boolean.class){  
                    ps.setBoolean(parameterIndex, Boolean.parseBoolean((String) params[i]));  
                } 
                else if(params[i].getClass()==BigDecimal.class){  
                    ps.setBigDecimal(parameterIndex, new BigDecimal(params[i].toString()));  
                } 
                else if(params[i].getClass()==Timestamp.class){  
                    ps.setTimestamp(parameterIndex, new Timestamp(new Date().getTime()));  
                } 
                else{  
                    throw new Exception("参数准备出错：数据类型不可见"+params[i].getClass().toString());  
                }  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 执行语句（eg:insert update delete) 
     * @param sql 
     * @param params OracleParameter[] 
     * @return 
     * @throws Exception int(sql 影响的行数) 
     */  
    public static int ExecuteNonQuery(String sql,Object[] params,Connection conn)throws Exception{  
        PreparedStatement ps=null;  
        try{  
            ps=conn.prepareStatement(sql);  
            PrepareCommand(ps,params);  
            return ps.executeUpdate();  
        }catch(Exception e){  
            throw new Exception("executeNonQuery方法出错："+e.getMessage());  
        }finally{  
            try {  
                if(ps!=null&&(!ps.isClosed()))  
                    ps.close();  
            } catch (Exception e2) {  
                throw new Exception("ExecuteScalar方法出错："+e2.getMessage());  
            }  
        }  
    }  
    
    /** 
     * 重载执行语句（eg:insert update delete) 
     * @param sql 
     * @param params OracleParameter[] 
     * @return id
     * @throws Exception int(sql 影响的行数) 
     */  
    public static int ExecuteNonQueryId(String sql,Object[] params,Connection conn)throws Exception{  
        PreparedStatement ps=null;  
        ResultSet rs = null;
        try{  
            ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
            PrepareCommand(ps,params); 
            int result=ps.executeUpdate(); 
            int id=0;
            if(result>0){
	            rs=ps.getGeneratedKeys();//ResultSet 指示键值 
				if(rs.next()){
					id = rs.getInt(1);//得到第一个键值 
				}
            }
			return id;
        }catch(Exception e){  
            throw new Exception("executeNonQueryId方法出错："+e.getMessage());  
        }finally{  
            try {  
                if(rs!=null&&(!rs.isClosed()))  
                    rs.close();  
                if(ps!=null&&(!ps.isClosed()))  
                    ps.close();  
            } catch (Exception e2) {  
                throw new Exception("ExecuteScalar方法出错："+e2.getMessage());  
            }  
        }  
    }  
    
    /** 
     * 获取结果集语句 
     * @param sql 
     * @param params 
     * @return 
     * @throws Exception 
     */  
    public static ArrayList ExecuteReader(String sql,Object[] params,FuServer server)throws Exception{  
        PreparedStatement ps=null;  
        Connection conn=null;  
        ResultSet rs=null;  
        try {  
            conn=getConnect(server);  
            ps=conn.prepareStatement(sql);  
            PrepareCommand(ps,params);  
            rs=ps.executeQuery();  
            ArrayList list=new ArrayList(); 
            ResultSetMetaData rsmd=rs.getMetaData();  
            int column=rsmd.getColumnCount();  
              
            if(rs.next()){  
                for(int i=1;i<=column;i++){  
                	list.add(rs.getObject(i));  
                }  
            }  
            return list;  
        } catch (Exception e) {  
            throw new Exception("ExcuteReader方法出错："+e.getMessage());  
        } finally{  
            try {  
                if(rs!=null&&(!rs.isClosed()))  
                    rs.close();  
                if(ps!=null&&(!ps.isClosed()))  
                    ps.close();  
            } catch (Exception e2) {  
                throw new Exception("ExecuteScalar方法出错："+e2.getMessage());  
            }  
        }  
    }  
    /** 
     * 获取单个字段的值的语句（用名字指定字段） 
     * @param sql 
     * @param name 
     * @param params 
     * @return 
     * @throws Exception 
     */  
    public static Object ExecuteScalar(String sql,String name,Object[] params,FuServer server)throws Exception{  
        PreparedStatement ps=null;  
        Connection conn=null;  
        ResultSet rs=null;  
        try {  
            conn=getConnect(server);  
            ps=conn.prepareStatement(sql);  
            PrepareCommand(ps,params);  
              
            rs=ps.executeQuery();  
            if(rs.next()){  
                return rs.getObject(name);  
            }else{  
                return null;  
            }  
        } catch (Exception e) {  
            throw new Exception("ExecuteScalar方法出错："+e.getMessage());  
        } finally{  
            try {  
                if(rs!=null&&(!rs.isClosed()))  
                    rs.close();  
                if(ps!=null&&(!ps.isClosed()))  
                    ps.close();  
            } catch (Exception e2) {  
                throw new Exception("ExecuteScalar方法出错："+e2.getMessage());  
            }  
        }  
    }  
    
    
    /** 
     * 获取指定下标字段的值的语句（用下标指定字段） 
     * @param sql 
     * @param index 
     * @param params 
     * @return 
     * @throws Exception 
     */    
    public static Object executeScalar(String sql,int index,Object[] params,FuServer server)  
        throws Exception{  
        PreparedStatement ps=null;  
        Connection conn=null;  
        ResultSet rs=null;  
        try {  
            conn=getConnect(server);  
            ps=conn.prepareStatement(sql);  
            PrepareCommand(ps,params);  
            rs=ps.executeQuery();  
            if(rs.next()){  
                return rs.getObject(index);  
            }else{  
                return null;  
            }  
        } catch (Exception e) {  
            throw new Exception("ExecuteScalar方法出错："+e.getMessage());  
        } finally{  
            try {  
                if(rs!=null&&(!rs.isClosed()))  
                    rs.close();  
                if(ps!=null&&(!ps.isClosed()))  
                    ps.close();  
            } catch (Exception e2) {  
                throw new Exception("ExecuteScalar方法出错："+e2.getMessage());  
            }  
        }  
    }
    /**
     * 关闭连接
     * @param rs
     * @param ps
     * @param conn
     * @throws Exception
     */
    public static void colseConnection(Connection conn)throws Exception{
    	try {  
            if(conn!=null&&(!conn.isClosed()))  
                conn.close();  
        } catch (Exception e) {  
            throw new Exception("ExecuteScalar方法出错："+e.getMessage());  
        }  
    }

}
