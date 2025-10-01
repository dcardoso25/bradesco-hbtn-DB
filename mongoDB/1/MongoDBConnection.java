import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnection {

    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String CLUSTER_URL = "";
    private static final String DATABASE_NAME = "";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        try {
            String connectionString = String.format("mongodb+srv://" + USERNAME + ":" + PASSWORD + "@" + CLUSTER_URL + "/?retryWrites=true&w=majority&appName=" + DATABASE_NAME);

            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();

            mongoClient = MongoClients.create(settings);

            database = mongoClient.getDatabase(DATABASE_NAME);

            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão encerrada com sucesso.");
        }
    }

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        UsuarioOperations usuarioOperations = new UsuarioOperations(connection.getDatabase(), "Usuario");

        if (connection.getDatabase() != null) {
            System.out.println("Banco de dados: " + connection.getDatabase().getName());
        }

        performOperations(usuarioOperations);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        connection.closeConnection();
    }

    private static void performOperations(UsuarioOperations usuarioOperations) {

        var listUsuarios = List.of(
                new Usuario("Alice", 25),
                new Usuario("Bob", 30),
                new Usuario("Charlie", 35)
        );

        listUsuarios.forEach(usuarioOperations::create);

        System.out.println(usuarioOperations.findAll());

        usuarioOperations.upadteAgeByName("Bob", 32);

        System.out.println(usuarioOperations.findAll());

        usuarioOperations.deleteByName("Charlie");

        System.out.println(usuarioOperations.findAll());

        usuarioOperations.dropCollection();
    }
}