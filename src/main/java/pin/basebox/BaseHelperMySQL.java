package pin.basebox;

import java.sql.Connection;
import pin.jarbox.dat.Table;

public class BaseHelperMySQL extends BaseHelper {

  @Override
  public boolean isErrorPrimaryKey(Exception error) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void createTable(Connection connection, Table table, boolean onlyIfNotExists) throws Exception {
    throw new UnsupportedOperationException();
  }

}
