package bean;

import java.util.ArrayList;
import java.util.List;

public class ConstructorValues {
    private List<ConstructorValue> constructorValueList = new ArrayList<>();

    public ConstructorValues() {
    }

    public List<ConstructorValue> getConstructorValueList() {
        return constructorValueList;
    }

    public void setConstructorValueList(List<ConstructorValue> constructorValueList) {
        this.constructorValueList = constructorValueList;
    }
    public void addConstructorValue(ConstructorValue constructorValue) {
        constructorValueList.add(constructorValue);
    }

}
