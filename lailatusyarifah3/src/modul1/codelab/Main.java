package modul1.codelab;

class Box<T> {
    private T value;
    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
}
public class Main {
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>() ;

        integerBox.setValue(42);
        int intValue = integerBox.getValue();
        System.out.println("Integer Value: " + intValue);

        Box<String> stringBox = new Box<>();

        stringBox.setValue("Hello, generic!");
        String strValue = stringBox.getValue();
        System.out.println("String value: " + strValue);
    }
}
