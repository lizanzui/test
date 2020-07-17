package classes.data;

/**
 * @version 1.0
 * @author: lyq
 * @date: 2020/5/6 11:04
 * @modified by
 */
public class Register {
    String logname = "";
    String phone = "";
    String address = "";
    String realname = "";
    String backNews = "请输入信息";

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getBackNews() {
        return backNews;
    }

    public void setBackNews(String backNews) {
        this.backNews = backNews;
    }
}
