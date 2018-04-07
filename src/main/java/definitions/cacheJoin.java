package definitions;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

public class cacheJoin implements Serializable{
    @QuerySqlField
    public String application;
    @QuerySqlField
    public String payload;
    @QuerySqlField
    public String imsi;

    public String getApplication() {
        return application;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @Override
    public String toString() {
        return "cacheJoin{" +
                "application='" + application + '\'' +
                ", payload='" + payload + '\'' +
                ", imsi='" + imsi + '\'' +
                '}';
    }

    public void setApplication(String application) {
        this.application = application;

    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public cacheJoin() {
    }



    public cacheJoin(String application, String payload, String imsi) {
        this.application = application;
        this.payload = payload;
        this.imsi = imsi;
    }
}
