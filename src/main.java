import java.util.*;/*Used in code to use Map HashMap Scanner and Random */
import java.sql.*;

class User{
    /*Blueprint for the real world entity User */
    String username;
    long hash;
    String salt;
    User(String username, long hash, String salt){
        /*Constructor for the User */
        this.username=username;
        this.hash=hash;
        this.salt=salt;
    }
}
public class main {
    

static Connection connectDB() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 🔥 important
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/auth_system",
            "root",
            ""
        );
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    static final String PEPPER="HOLA";
    /*static Map<String, User> database=new HashMap<>();Temprory DataBase for testing with Key Username and Value hash+salt */
    public static String generateSalt(){
        /*will generate the salt */
        String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder salt=new StringBuilder();
        Random rand = new Random();

        for(int i=0;i<8;i++){
            /*salt with length 8 generated */
            salt.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return salt.toString();/*convert StringBuilder to Normal String */
    }
    public static long customHash(String input){
        long hash=0;
        for(char c:input.toCharArray()){
            hash=(hash*31+c)%1000000007;/*Hash logic */
        }
        return hash;
    }
    /*public static void register(String username, String password){
        --for registering the User 
        String salt=generateSalt();--create salt for the user 
        long hash=customHash(password+salt+PEPPER);--combine the password,salt and pepper then hash it 
        database.put(username,new User(username,hash,salt));--Stores it in database 
        System.out.println("User registered!");
    }*/
    public static void register(String username, String password) {
    try {
        Connection con = connectDB();

        String salt = generateSalt();
        long hash = customHash(password + salt + PEPPER);

        String query = "INSERT INTO users (username, hash, salt) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);
        ps.setLong(2, hash);
        ps.setString(3, salt);

        ps.executeUpdate();

        System.out.println("User registered!");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    /*public static void login(String username, String password){
        User user=database.get(username);--fetch the details 
        if(user==null){
            System.out.println("User not found");
            return;
        }
        long inputHash=customHash(password+user.salt+PEPPER);--recreate the hash 
        if(inputHash==user.hash){
            System.out.println("login successful");
        }
        else{
            System.out.println("invalid password try again");
        }
    }*/
   public static void login(String username, String password) {
    try {
        Connection con = connectDB();

        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            long storedHash = rs.getLong("hash");
            String storedSalt = rs.getString("salt");

            long inputHash = customHash(password + storedSalt + PEPPER);

            if (inputHash == storedHash) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("User not found");
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    public static void main(String[] args){
           Connection con = connectDB();
if (con != null) {
    System.out.println("Connected to database!");
} else {
    System.out.println("Connection failed!");
}
        Scanner sc=new Scanner(System.in);
        while (true){/*infinite loop */
            System.out.println("\n1. Regiser\n2. Login\n3. Exit");
            int choice=sc.nextInt();
            sc.nextLine();/*fixes the input buffer issue */
            if(choice==1){
                System.out.print("Username: ");
                String u=sc.nextLine();
                System.out.print("password");
                String p=sc.nextLine();
                register(u,p);
            }
            else if(choice==2){
                System.out.print("username: ");
                String u= sc.nextLine();
                System.out.print("password: ");
                String p=sc.nextLine();
                login(u,p);
            }else{break;}
        }
    }
}
