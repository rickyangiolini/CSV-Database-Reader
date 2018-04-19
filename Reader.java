import java.io.IOException;
import org.apache.commons.csv.*;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Reader{

        static Connection con;
        public void reader(String _x) throws SQLException {
            String x=_x;

            try {
                System.out.println("Inserting your data...");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3?useSSL=false", "angio102", "tribuna");
                FileReader in = new FileReader(x);

                Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
                for (CSVRecord record : records) {
                    String FirstName = record.get(0);
                    String LastName = record.get(1);
                    String Email = record.get(2);
                    String City = record.get(3);
                    String BusinessName = record.get(4);
                    String Address = record.get(5);
                    String RandomWord = record.get(6);

                    PreparedStatement st = con.prepareStatement("INSERT INTO Person(FirstName, LastName) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

                    st.setString(1, FirstName); //setting first name to index 1
                    st.setString(2, LastName); //setting last name name to index 2
                    st.executeUpdate();

                    ResultSet rs_key = st.getGeneratedKeys(); //generates result set for the keys
                    int c_id = 0;
                    if (rs_key.next()) {
                        c_id = rs_key.getInt(1);
                    }

                        PreparedStatement pst = con.prepareStatement("INSERT INTO Information(personID, Email) VALUES(?, ?)");
                        pst.setInt(1, c_id);
                        pst.setString(2, Email); //setting email to index 2
                        pst.executeUpdate();

                        PreparedStatement pt = con.prepareStatement("INSERT INTO Residence(personID,Address) VALUES (?,?) ");
                        pt.setInt(1, c_id);
                        pt.setString(2, Address); //setting address name to index 2
                        pt.executeUpdate();

                        PreparedStatement sta = con.prepareStatement("INSERT INTO Work(personID,City,BusinessName) VALUES (?,?,?) ");
                        sta.setInt(1, c_id);
                        sta.setString(2, City); //setting city to index 2
                        sta.setString(3, BusinessName); //setting business name to index 3
                        sta.executeUpdate();

                        PreparedStatement pre = con.prepareStatement("INSERT INTO FavoriteWord(personID,Word) VALUES (?,?) ");
                        pre.setInt(1, c_id);
                        pre.setString(2, RandomWord); //setting random word to index 2
                        pre.executeUpdate();

                }
                System.out.println("Data successfully inserted.");
            } catch (SQLException e) {
                System.out.println(e.getMessage()); //return any exceptions

            } catch (IOException e) {
                e.printStackTrace(); //return any exceptions

            } finally {
                con.close(); //close connection
            }
        }
}
