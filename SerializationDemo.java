import java.io.*;

// Serializable class
class Persons implements Serializable {
    String name;
    int age;

    Persons(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class SerializationDemo {

    public static void main(String[] args) {

        // Create object to serialize
        Persons person = new Persons("Alice", 25);

        // Serialize object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            out.writeObject(person);
            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Persons deserializedPerson = (Persons) in.readObject();
            System.out.println("Object deserialized:");
            System.out.println("Name: " + deserializedPerson.name);
            System.out.println("Age: " + deserializedPerson.age);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}