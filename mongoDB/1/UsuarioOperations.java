import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class UsuarioOperations {


    private final MongoCollection<Document> col;

    public UsuarioOperations(MongoDatabase db, String collection) {
        this.col = db.getCollection(collection);
    }

    public void create(Usuario usuario) {
        col.insertOne(usuario.toDocument());
    }

    public void upadteAgeByName(String name, int newAge) {
        col.updateOne(eq("name", name), set("idade", newAge));
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        col.find().forEach(i -> usuarios.add(Usuario.fromDocument(i)));
        return usuarios;
    }

    public void deleteByName(String name) {
        col.deleteOne(eq("nome", name));
    }

    public void dropCollection() {
        col.drop();
    }
}