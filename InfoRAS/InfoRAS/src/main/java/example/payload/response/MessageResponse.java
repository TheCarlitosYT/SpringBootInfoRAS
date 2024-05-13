package example.payload.response;

import lombok.Builder;

public class MessageResponse {
    private String message;

    @Builder
    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
