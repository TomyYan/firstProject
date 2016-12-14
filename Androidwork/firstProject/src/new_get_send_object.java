import java.io.Serializable;

/**
 * Created by dell on 2016/10/23.
 */
public class new_get_send_object implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user;
    private String option;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String username) {
        this.option = username;
    }

    public new_get_send_object(String password, String option) {
        super();
        this.user = password;
        this.option = option;
    }

    public new_get_send_object() {
    }
}
