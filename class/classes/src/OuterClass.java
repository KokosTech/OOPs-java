public class OuterClass {
    private static String s;
    private static int p;
    private int i;

    public void test() {
        InnerClass ic = new InnerClass(5);
        ic.t = 4;
    }

    public static class StaticInnerClass {
        private int d;

        public void test() {
            d = p;
            OuterClass oc = new OuterClass();
            d = oc.i;
        }
    }

    public class InnerClass {
        private int t;

        public InnerClass(int t) {
            this.t = t;
            this.t = i;
        }

        public void test(int t) {
            i = 5;
            OuterClass.this.i = 6;
        }
    }
}
