// $Id$
// Copyright 2010 ${CLIENT}
// John Hurst (john.b.hurst@gmail.com)
// 2010-11-22

package org.dbunit.samples.xml;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleXmlDataTypeFactory extends Oracle10DataTypeFactory {

  private static final Logger logger = LoggerFactory.getLogger(OracleXmlDataTypeFactory.class);

  protected static final DataType XMLTYPE_TYPE = new OracleXMLTypeDataType();

  public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException
  {
    if(logger.isDebugEnabled()) {
      logger.debug("createDataType(sqlType={}, sqlTypeName={}) - start", String.valueOf(sqlType), sqlTypeName);
    }
    // XMLTYPE
    if ("XMLTYPE".equals(sqlTypeName) || "SYS.XMLTYPE".equals(sqlTypeName)) {
      return XMLTYPE_TYPE;
    }
    return super.createDataType(sqlType, sqlTypeName);
  }
}
