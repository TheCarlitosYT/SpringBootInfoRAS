package example.payload.request;

import jakarta.validation.constraints.NotBlank;

public class PasswRecoveryRequest {
    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
