public class Point {
    private int x;
    private int y;

    // we force it to use the builder classs
    private Point() { }

    public static final class PointBuilder {
        private int x;
        private int y;

        private PointBuilder() {
        }

        public static PointBuilder aPoint() {
            return new PointBuilder();
        }

        public PointBuilder withX(int x) {
            this.x = x;
            return this;
        }

        public PointBuilder withY(int y) {
            this.y = y;
            return this;
        }

        public Point build() {
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            return point;
        }
    }
}
