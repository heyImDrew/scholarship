package Connection;

import java.io.IOException;
import java.sql.SQLException;

abstract class AbstractAction {
    public abstract void execute(Handler handler, String action) throws IOException, ClassNotFoundException, SQLException;
}
