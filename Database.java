import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
   private final String username="Pac";
   private final String password="pass";
   private final String url="jdbc:mysql://localhost:3306/db1";

    private Connection getConnection() {
        Connection connection;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            connection= DriverManager.getConnection(url,username,password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewPerson(Person person) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO person (name,surn,dob,rodnc) values(?,?,?,?)");
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSurename());
            stmt.setDate(3, new Date(person.getDob().getTime()));
            stmt.setString(4, person.getRodc());
            int result=stmt.executeUpdate();
            closeConnection(conn);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public Person selectPerson(String surn) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person WHERE surn like ?");
            stmt.setString(1, surn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String surname = rs.getString("surn");
                String firstName = rs.getString("name");
                String pid = rs.getString("rodnc");
                Date dob = rs.getDate("dob");

            Person p = new Person(surname,firstName,pid,dob);
            closeConnection(conn);
            return p;
            }
            else {
                closeConnection(conn);
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Person selectPersonByPid(String rodnc) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person WHERE rodnc like ?");
            stmt.setString(1, rodnc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String pid = rs.getString("rodnc");
                String surname = rs.getString("surn");
                String firstName = rs.getString("name");
                Date dob = rs.getDate("dob");

                Person p2 = new Person(surname,firstName,pid,dob);
                closeConnection(conn);
                return p2;
            }
            else {
                closeConnection(conn);
                return null;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> getAllMen(){
        Connection conn = getConnection();
        String query="SELECT * FROM person WHERE rodnc LIKE '__0%' OR rodnc LIKE '__1%' ";
        List<Person> persons = new ArrayList<>();
        ResultSet rs;
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String surname = rs.getString("surn");
                String firstName = rs.getString("name");
                String pid = rs.getString("rodnc");
                Date dob = rs.getDate("dob");
                Person p3=new Person(surname,firstName,pid,dob);
                persons.add(p3);
            }
            closeConnection(conn);
        }
        catch (SQLException e) {
                e.printStackTrace();
            }
        return persons;
   }

    public List <Person> selectAllAdult(){
        Connection conn = getConnection();
        String query = "SELECT * FROM person WHERE dob < Current_date() - 18 ";
        List <Person> persons = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String surname = rs.getString("surn");
                String firstName = rs.getString("name");
                String pid = rs.getString("rodnc");
                Date dob = rs.getDate("dob");
                Person p4=new Person(surname,firstName,pid,dob);
                persons.add(p4);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return persons;
    }

    private void closeConnection(Connection conn) {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
