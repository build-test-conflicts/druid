package com.alibaba.druid.sql.dialect.mysql.ast.statement;
import java.util.List;
import com.alibaba.druid.sql.ast.SQLCommentHint;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitor;
public class MySqlLockTableStatement extends MySqlStatementImpl {
  private SQLExprTableSource tableSource;
  private LockType lockType;
  private List<SQLCommentHint> hints;
  public SQLExprTableSource getTableSource(){
    return tableSource;
  }
  public void setTableSource(  SQLExprTableSource tableSource){
    if (tableSource != null) {
      tableSource.setParent(this);
    }
    this.tableSource=tableSource;
  }
  public void setTableSource(  SQLName name){
    setTableSource(new SQLExprTableSource(name));
  }
  public LockType getLockType(){
    return lockType;
  }
  public void setLockType(  LockType lockType){
    this.lockType=lockType;
  }
  public void accept0(  MySqlASTVisitor visitor){
    if (visitor.visit(this)) {
      acceptChild(visitor,tableSource);
    }
    visitor.endVisit(this);
  }
  public static enum LockType;
{
  }
  public String name;
  void LockType(  String name){
    this.name=name;
  }
{
  }
  public List<SQLCommentHint> getHints(){
    return hints;
  }
  public void setHints(  List<SQLCommentHint> hints){
    this.hints=hints;
  }
}
