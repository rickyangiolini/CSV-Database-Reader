import java.sql.SQLException;

public class MainReader { //MainReader class reads the Reader class and executes the statements
    public static void main(String args[]) throws SQLException {
        String x = args[0];
        Reader r = new Reader();
        r.reader(x);
    }
}
