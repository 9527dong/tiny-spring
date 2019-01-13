package bean;

import lombok.Data;

@Data
public class PropertyValue {
    private String field;
    private Object value;
    private String ref;

    public PropertyValue(String field, Object value, String ref) {
        this.field = field;
        this.value = value;
        this.ref = ref;
    }
}
