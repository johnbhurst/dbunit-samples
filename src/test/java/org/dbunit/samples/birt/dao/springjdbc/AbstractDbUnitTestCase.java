// $Id$
// John Hurst (john.b.hurst@gmail.com)
// 2010-07-12

package org.dbunit.samples.birt.dao.springjdbc;

import javax.sql.DataSource;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.junit.After;
import org.junit.Before;
import org.xml.sax.InputSource;

import oracle.jdbc.pool.OracleDataSource;

public class AbstractDbUnitTestCase {

  private DataSource dataSource;
  private IDatabaseConnection databaseConnection;

  @Before
  public void setupConnections() throws SQLException, DatabaseUnitException {
    OracleDataSource dataSource = new OracleDataSource();
    dataSource.setURL(System.getProperty("db.url"));
    dataSource.setUser(System.getProperty("db.userid"));
    dataSource.setPassword(System.getProperty("db.password"));
    this.dataSource = dataSource;
    DatabaseConnection databaseConnection = new DatabaseConnection(getDataSource().getConnection());
    databaseConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory());
    this.databaseConnection = databaseConnection;
  }

  @After
  public void shutdownConnections() throws SQLException {
    if (databaseConnection != null) {
      databaseConnection.close();
    }
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public IDatabaseConnection getDatabaseConnection() {
    return databaseConnection;
  }
  
  public FlatXmlDataSet getDataSet(String fileName) throws DataSetException {
    return new FlatXmlDataSet(new FlatXmlProducer(new InputSource(getClass().getResourceAsStream(fileName))));
  }
}
