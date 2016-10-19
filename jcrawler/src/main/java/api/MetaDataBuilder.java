package api;

import java.util.HashMap;

/**
 * Created by YANLL on 2016/10/19.
 */
public class MetaDataBuilder {

    private HashMap<String, String> meta = null;

    public MetaDataBuilder() {
        meta = new HashMap<String, String>();
    }

    public MetaDataBuilder setMeta(String key, String value) {
        this.meta.put(key, value);
        return this;
    }

    public HashMap<String, String> getMeta() {
        return this.meta;
    }
}
