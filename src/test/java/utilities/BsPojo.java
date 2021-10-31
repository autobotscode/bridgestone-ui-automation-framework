package utilities;

import java.util.Properties;

public class BsPojo {

    private String browser;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }



    private static final BsPojo pojo = new BsPojo();

    public static BsPojo getInstance() {
        return pojo;
    }

    public Object clone()
            throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
