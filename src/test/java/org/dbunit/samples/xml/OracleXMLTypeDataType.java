// $Id$
// Copyright 2010 ${CLIENT}
// John Hurst (john.b.hurst@gmail.com)
// 2010-11-22

package org.dbunit.samples.xml;

import oracle.sql.OPAQUE;
import oracle.xdb.XMLType;
import org.dbunit.dataset.datatype.StringDataType;
import org.dbunit.dataset.datatype.TypeCastException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OracleXMLTypeDataType extends StringDataType {

  public OracleXMLTypeDataType() {
    super("XMLTYPE", Types.VARCHAR);
  }

  public Object getSqlValue(int column, ResultSet resultSet) throws SQLException, TypeCastException {
    Object obj = resultSet.getObject(column);
    if (resultSet.wasNull() || obj == null) {
      return null;
    }
    else {
      XMLType xt = XMLType.createXML((OPAQUE) obj);
      return xt.getStringVal();
    }
  }

  public void setSqlValue(Object value, int column, PreparedStatement statement) throws SQLException, TypeCastException {
    if (value == null) {
      statement.setNull(column, Types.NULL);
    }
    else {
      String s = (String) value;
      Connection connection = statement.getConnection();
      XMLType xt = XMLType.createXML(connection, s);
      statement.setObject(column, xt);
    }
  }

  public int compare(Object o1, Object o2) throws TypeCastException {
    if (o1 == null && o2 == null) {
      return 0;
    }
    if (o1 == null) {
      return -1;
    }
    if (o2 == null) {
      return 1;
    }
    String s1 = ((String) o1).replace("\r\n", "\n").replace("\r", "\n");
    String s2 = ((String) o2).replace("\r\n", "\n").replace("\r", "\n");
    return s1.compareTo(s2);
  }

}
