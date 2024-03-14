package example.payload.request;

import jakarta.validation.constraints.NotBlank;

public class VerifyCodeRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
