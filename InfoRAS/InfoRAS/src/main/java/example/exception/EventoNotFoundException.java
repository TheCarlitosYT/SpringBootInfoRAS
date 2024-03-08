package example.exception;

public class EventoNotFoundException extends RuntimeException{


        public EventoNotFoundException() {}

        public EventoNotFoundException(String message) {
            super(message);
        }

        public EventoNotFoundException(long id) {
            super("Cliente not found: " + id);
        }
}


