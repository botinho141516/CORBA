import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public String helloWorldString() {
        return "Hello World!!!!!!!";
    }

    @Override
    public int Fibonacci(int n) {
        if (n <= 1)
            return n;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
