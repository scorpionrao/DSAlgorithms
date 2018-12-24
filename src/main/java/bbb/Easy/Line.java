package bbb.Easy;

public class Line {

    double epsilon = 0.0001;
    double slope;
    double yIntercept;

    Line(double slope, double yIntercept) {
        this.slope = slope;
        this.yIntercept = yIntercept;
    }

    public boolean doesIntersect(Line line) {
        return Math.abs(this.slope - line.slope) > epsilon
                && Math.abs(this.yIntercept - line.yIntercept) > epsilon;
    }
}
