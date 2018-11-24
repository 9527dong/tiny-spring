package bean;

public class ConstructorValue {
    private String index;
    private String ref;

    public ConstructorValue(String index, String ref) {
        this.index = index;
        this.ref = ref;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}