import java.util.List;

import static java.util.Objects.isNull;
import static Point.PointBuilder.aPoint;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        OuterClass oc = new OuterClawss();
        OuterClass.InnerClass in = oc.new InnerClass(5);
        OuterClass.StaticInnerClass sin = new OuterClass.StaticInnerClass();

        aPoint().withX(5).withY(6).build();

        if (isNull(in)) {

        }
    }

    public static void test() {
        final int c = 5;
        if(true) {
            class Test { // local class has access to all final variables
                public void print() {
                    System.out.println(c);
                }
            }

            Test t = new Test();
        }
    }
}