package aplicacion;


public class App {
    public static void main(String[] args) throws Exception {
        GestorBiblio gestor = new GestorBiblio();
        gestor.crearDB();
        Libros libro = new Libros();
        libro.crearTabla();
        
    }
}
