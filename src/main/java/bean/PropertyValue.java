package bean;

public class PropertyValue {
    private String field;
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PropertyValue(String field, String value) {
        this.field = field;
        this.value = value;
    }
}
