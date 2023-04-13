public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        double square = Math.PI * Math.pow(Math.abs(radius), 2);
        return square;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        double volume = Math.PI * Math.pow(Math.abs(radius), 3) * 4./3;
        return volume;
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        if ( (a+b) <= c || (a+c) <= b || (b+c) <=a) return false;
        else return true;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if(!isTrianglePossible(a,b,c)) return -1.0;
        double p = (a + b + c) / 2;
        double square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return square;
    }
}
