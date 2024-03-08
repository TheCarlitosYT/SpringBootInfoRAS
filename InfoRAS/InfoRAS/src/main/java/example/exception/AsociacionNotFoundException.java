package example.exception;

public class AsociacionNotFoundException extends RuntimeException{


        public AsociacionNotFoundException() {}

        public AsociacionNotFoundException(String message) {
            super(message);
        }

        public AsociacionNotFoundException(long id) {
            super("Cliente not found: " + id);
        }
}


