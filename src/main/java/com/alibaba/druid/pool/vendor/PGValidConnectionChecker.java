package com.alibaba.druid.pool.vendor;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.pool.ValidConnectionChecker;
import com.alibaba.druid.pool.ValidConnectionCheckerAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.util.JdbcUtils;
public class PGValidConnectionChecker extends ValidConnectionCheckerAdapter implements ValidConnectionChecker, Serializable {
  private static long serialVersionUID=-2227528634302168877L;
  private int defaultQueryTimeout=1;
  private String defaultValidateQuery="SELECT 'x'";
  public PGValidConnectionChecker(){
    configFromProperties(System.getProperties());
  }
  public boolean isValidConnection(  Connection conn,  String validateQuery,  int validationQueryTimeout) throws Exception {
    if (validateQuery == null || validateQuery.isEmpty()) {
      validateQuery=this.defaultValidateQuery;
    }
    if (conn.isClosed()) {
      return false;
    }
    if (conn instanceof DruidPooledConnection) {
      conn=((DruidPooledConnection)conn).getConnection();
    }
    if (conn instanceof ConnectionProxy) {
      conn=((ConnectionProxy)conn).getRawObject();
    }
    int queryTimeout=validationQueryTimeout < 0 ? defaultQueryTimeout : validationQueryTimeout;
    Statement stmt=null;
    ResultSet rs=null;
    try {
      stmt=conn.createStatement();
      stmt.setQueryTimeout(queryTimeout);
      rs=stmt.executeQuery(validateQuery);
      return true;
    }
  finally {
      JdbcUtils.close(rs);
      JdbcUtils.close(stmt);
    }
  }
}
