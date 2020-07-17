package classes.data;

import java.util.LinkedList;

/**
 * @version 1.0
 * @author: lyq
 * @date: 2020/5/10 11:16
 * @modified by
 */
public class Login {
    Integer id;
    String logname = "",backNews = "未登录";
    LinkedList<String> car;
    public Login(){
        car = new LinkedList<>();
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getBackNews() {
        return backNews;
    }

    public void setBackNews(String backNews) {
        this.backNews = backNews;
    }

    public LinkedList<String> getCar() {
        return car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCar(LinkedList<String> car) {
        this.car = car;
    }
}
