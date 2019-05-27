package me.buck.fileio;

/**
 * Created by gwf on 2019/5/27
 */
public class FileIoBean {


    /**
     * success : true
     * key : vX3c4h
     * link : https://file.io/vX3c4h
     * expiry : 14 days
     */

    private boolean success;
    private String key;
    private String link;
    private String expiry;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
