// $Id$
// John Hurst (john.b.hurst@gmail.com)
// 2010-07-12

package org.dbunit.samples.birt.dao.springjdbc;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.samples.AbstractDbUnitTestCase;
import org.dbunit.samples.birt.domain.Office;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SpringJdbcOfficeDaoTest extends AbstractDbUnitTestCase {

  @Test
  public void testFind() throws Exception {
    DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(), getDataSet("SpringJdbcOfficeDaoTest-setup.xml"));
    OfficeDao dao = new SpringJdbcOfficeDao(getDataSource());
    Office office = dao.find("AUCK");
    assertEquals("AUCK", office.getOfficeCode());
    assertEquals("Auckland", office.getCity());
    assertEquals("+64 9 555-4321", office.getPhone());
    assertEquals("1 Viaduct Way", office.getAddressLine1());
    assertEquals("Auck Central", office.getAddressLine2());
    assertEquals("AUCK", office.getState());
    assertEquals("New Zealand", office.getCountry());
    assertEquals("4321", office.getPostalCode());
    assertEquals("Oceana", office.getTerritory());
//    assertNull(dao.find("WGTN"));
  }

  @Test
  public void testSave() throws Exception {
    DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(), getDataSet("SpringJdbcOfficeDaoTest-empty.xml"));
    Office office = new Office();
    office.setOfficeCode("WGTN");
    office.setCity("Wellington");
    office.setPhone("+64 4 555-1234");
    office.setAddressLine1("4 The Terrace");
    office.setAddressLine2("CBD");
    office.setState("Wellington");
    office.setCountry("New Zealand");
    office.setPostalCode("1234");
    office.setTerritory("Oceana");
    OfficeDao dao = new SpringJdbcOfficeDao(getDataSource());
    dao.save(office);
    IDataSet expected = getDataSet("SpringJdbcOfficeDaoTest-result.xml");
    IDataSet actual = new DatabaseDataSet(getDatabaseConnection(), false);
    Assertion.assertEquals(expected.getTable("offices"), actual.getTable("offices"));
  }

}
