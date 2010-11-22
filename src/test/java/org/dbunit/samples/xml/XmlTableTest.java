// $Id$
// John Hurst (john.b.hurst@gmail.com)
// 2010-11-22

package org.dbunit.samples.xml;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.OPAQUE;
import oracle.sql.OpaqueDescriptor;
import oracle.xdb.XMLType;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.samples.AbstractDbUnitTestCase;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;

public class XmlTableTest extends AbstractDbUnitTestCase {

  @Test
  public void testLoad() throws Exception {
    DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(), getDataSet("test-load.xml"));
    String val = new JdbcTemplate(getDataSource()).queryForObject(
      "SELECT extract(e.data, '//@a').getStringVal() FROM xml_table e WHERE id = 2",
      String.class
    );
    assertEquals("A", val);
  }

  @Test
  public void testSave() throws Exception {
    JdbcTemplate template = new JdbcTemplate(getDataSource());
    template.update("DELETE FROM xml_table");
    template.update(
      "INSERT INTO xml_table(id, data) " +
        "VALUES (21, XMLType('<result attr=\"aaa\"/>'))"
    );
    IDataSet expected = getDataSet("test-save.xml");
    IDataSet actual = new DatabaseDataSet(getDatabaseConnection(), false);
    Assertion.assertEquals(expected.getTable("xml_table"), actual.getTable("xml_table"));

  }

  // This is the method used to insert XMLType by the standard DbUnit 2.4.0 data type support.
  // It doesn't seem to work.
  @Test
  public void insertXmlAsOPAQUE() throws Exception {
    OracleConnection connection = (OracleConnection) getDataSource().getConnection();
    OraclePreparedStatement statement = (OraclePreparedStatement) connection.prepareStatement("INSERT INTO xml_table (id, data) VALUES (?, ?)");
    statement.setInt(1, 11);
    OpaqueDescriptor opaqueDescriptor = OpaqueDescriptor.createDescriptor("SYS.XMLTYPE", connection);
    OPAQUE opaque = new OPAQUE(opaqueDescriptor, "<document/>".getBytes(), connection);
    statement.setOPAQUE(2, opaque);
    statement.execute();
    statement.close();
    connection.commit();
    connection.close();
  }

  // This is the method I use to insert XMLType, and is demonstrated by the data type support in this sample project.
  // It works for me.
  @Test
  public void insertXmlAsXMLType() throws Exception {
    OracleConnection connection = (OracleConnection) getDataSource().getConnection();
    OraclePreparedStatement statement = (OraclePreparedStatement) connection.prepareStatement("INSERT INTO xml_table (id, data) VALUES (?, ?)");
    statement.setInt(1, 12);
    XMLType xt = XMLType.createXML(connection, "<document/>");
    statement.setObject(2, xt);
    statement.execute();
    statement.close();
    connection.commit();
    connection.close();
  }

}
