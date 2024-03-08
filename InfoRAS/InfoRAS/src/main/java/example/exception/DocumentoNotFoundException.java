package example.exception;

public class DocumentoNotFoundException extends RuntimeException{


        public DocumentoNotFoundException() {}

        public DocumentoNotFoundException(String message) {
            super(message);
        }

        public DocumentoNotFoundException(long id) {
            super("Cliente not found: " + id);
        }
}


