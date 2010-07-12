// $Id$
// John Hurst (john.b.hurst@gmail.com)
// 2010-07-12

package org.dbunit.samples.birt.dao.springjdbc;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbunit.samples.birt.domain.Office;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SpringJdbcOfficeDao extends JdbcDaoSupport implements OfficeDao {

  public SpringJdbcOfficeDao(DataSource dataSource) {
    setDataSource(dataSource);
  }

  public Office find(final String id) {
    return getJdbcTemplate().queryForObject("SELECT * FROM offices WHERE officeCode = ?", new Object[] {id}, new RowMapper<Office>() {
      public Office mapRow(ResultSet rs, int i) throws SQLException {
        Office result = new Office();
        result.setOfficeCode(id);
        result.setCity(rs.getString("city"));
        result.setPhone(rs.getString("phone"));
        result.setAddressLine1(rs.getString("addressLine1"));
        result.setAddressLine2(rs.getString("addressLine2"));
        result.setState(rs.getString("state"));
        result.setCountry(rs.getString("country"));
        result.setPostalCode(rs.getString("postalCode"));
        result.setTerritory(rs.getString("territory"));
        return result;
      }
    });
  }

  public void save(final Office office) {
    getJdbcTemplate().update(
      "INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory)" +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
      new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, office.getOfficeCode());
          ps.setString(2, office.getCity());
          ps.setString(3, office.getPhone());
          ps.setString(4, office.getAddressLine1());
          ps.setString(5, office.getAddressLine2());
          ps.setString(6, office.getState());
          ps.setString(7, office.getCountry());
          ps.setString(8, office.getPostalCode());
          ps.setString(9, office.getTerritory());
        }
      }
    );
  }
}
