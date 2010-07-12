// $Id$
// John Hurst (john.b.hurst@gmail.com)
// 2010-07-12

package org.dbunit.samples.birt.dao.springjdbc;

import org.dbunit.samples.birt.domain.Office;

public interface OfficeDao {
  Office find(String id);
  void save(Office office);
}
