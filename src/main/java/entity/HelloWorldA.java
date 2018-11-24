package entity;

public class HelloWorldA {
    HelloWorldB helloWorldB;
    String hello;
    public HelloWorldA(HelloWorldB helloWorldB,String hello,HelloWorldB helloWorldB1) {
        helloWorldB = this.helloWorldB;

    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public HelloWorldA(HelloWorldB helloWorldB) {
    }

    public HelloWorldA() {
    }
}
