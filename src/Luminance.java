import java.awt.Color;

public class Luminance {

    // return the monochrome luminance of given color
    public static double lum(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return 0.299*r + 0.587*g + 0.114*b;

    }

    public static Color toGray(Color c1){

        int gray = (int)lum(c1);
        Color ggg = new Color(gray, gray, gray);
        return ggg;

    }

    public static boolean compatible(Color c1, Color c2){
        return Math.abs(lum(c1) - lum(c2)) >= 128.0;
    }

    // test client
    public static void main(String[] args) {

        Color c1 = new Color(0, 0, 0);
        Color c2 = new Color(245, 220, 255);

        double b1 = lum(c1);
        double b2 = lum(c2);

        System.out.println(c1 +" has "+b1+" brightness");
        System.out.println(c2 +" has "+b2+" brightness");

        System.out.println(compatible(c1,c2));



    }
}