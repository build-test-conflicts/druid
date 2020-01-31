package com.alibaba.druid.sql.dialect.mysql.ast.statement;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.druid.sql.ast.SQLCommentHint;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlObjectImpl;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitor;
public class MySqlLockTableStatement extends MySqlStatementImpl {
  private List<Item> items=new ArrayList<Item>();
  @Override public void accept0(  MySqlASTVisitor visitor){
    if (visitor.visit(this)) {
      acceptChild(visitor,items);
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
  public List<Item> getItems(){
    return items;
  }
  public void setItems(  List<Item> items){
    this.items=items;
  }
  public LockType getLockType(){
    if (items.size() == 1) {
      return items.get(0).lockType;
    }
    return null;
  }
  public SQLExprTableSource getTableSource(){
    if (items.size() == 1) {
      return items.get(0).tableSource;
    }
    return null;
  }
public static class Item extends MySqlObjectImpl {
    private SQLExprTableSource tableSource=new SQLExprTableSource();
    private LockType lockType;
    private List<SQLCommentHint> hints;
    @Override public void accept0(    MySqlASTVisitor visitor){
      if (visitor.visit(this)) {
        acceptChild(visitor,tableSource);
      }
      visitor.endVisit(this);
    }
    public SQLExprTableSource getTableSource(){
      return tableSource;
    }
    public void setTableSource(    SQLExprTableSource tableSource){
      if (tableSource != null) {
        tableSource.setParent(this);
      }
      this.tableSource=tableSource;
    }
    public LockType getLockType(){
      return lockType;
    }
    public void setLockType(    LockType lockType){
      this.lockType=lockType;
    }
    public List<SQLCommentHint> getHints(){
      return hints;
    }
    public void setHints(    List<SQLCommentHint> hints){
      this.hints=hints;
    }
  }
}
