package integrationtests.vo;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@XmlRootElement(name = "TokenVO")
public class TokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String accessToken;

    private String tokenType;

    public TokenVO() {
    }

    public TokenVO(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenVO tokenVO)) return false;
        return Objects.equals(getAccessToken(), tokenVO.getAccessToken()) && Objects.equals(getTokenType(), tokenVO.getTokenType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getTokenType());
    }
}
