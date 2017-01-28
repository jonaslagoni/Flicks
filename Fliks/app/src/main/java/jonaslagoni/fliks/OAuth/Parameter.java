package jonaslagoni.fliks.OAuth;

/**
 * Created by jonas on 28-01-2017.
 */

public class Parameter {
    private String key;
    private String value;

    public Parameter(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
