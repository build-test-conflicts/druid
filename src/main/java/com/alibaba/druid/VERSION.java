package com.alibaba.druid;
public final class VERSION {
  public static int MajorVersion=1;
  public static int MinorVersion=1;
  public static int RevisionVersion=10;
  public static String getVersionNumber(){
    return VERSION.MajorVersion + "." + VERSION.MinorVersion+ "."+ VERSION.RevisionVersion;
  }
}
