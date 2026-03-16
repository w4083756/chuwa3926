package HW3;

public class DatabaseConnection {
    private DatabaseConnection() {
        System.out.println("Database connection created");
    }

    private static class Holder {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }

    public void executeQuery(String sql) {
        System.out.println("Executing " + sql);
    }

    public static DatabaseConnection getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        DatabaseConnection d1 = DatabaseConnection.getInstance();
        DatabaseConnection d2 = DatabaseConnection.getInstance();
        System.out.println(d1 == d2);

        d1.executeQuery("SELECT * FROM users");
    }
    
}
