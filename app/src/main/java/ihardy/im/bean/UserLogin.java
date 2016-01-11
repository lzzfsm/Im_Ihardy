package ihardy.im.bean;

/**
 * Created by Administrator on 2016/1/5.
 */
public class UserLogin {

    private String header_url;
    private int user_id;
    private String header_forphone;
    private String limit;
    private String all;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getHeader_url() {
        return header_url;
    }

    public void setHeader_url(String header_url) {
        this.header_url = header_url;
    }

    public String getHeader_forphone() {
        return header_forphone;
    }

    public void setHeader_forphone(String header_forphone) {
        this.header_forphone = header_forphone;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

}
