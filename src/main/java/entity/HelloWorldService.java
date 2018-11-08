package entity;

public class HelloWorldService {
    private String text;
    public void helloWorld() {
        System.out.println(text + "hello World");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
