package example.exception;

public class UsuarioNotFoundException extends RuntimeException{


        public UsuarioNotFoundException() {}

        public UsuarioNotFoundException(String message) {
            super(message);
        }

        public UsuarioNotFoundException(long id) {
            super("Cliente not found: " + id);
        }
}


