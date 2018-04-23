import java.awt.Color;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Random;

public class Transition {

    /**
     * this is the code for the rotation transition but it is very buggy so I never actually use it
     * @param canvas
     * @param pic1
     * @param steps
     */
    public static void rotation(Picture canvas, Picture pic1, int steps) {
        int H = pic1.height();
        int W = pic1.width();

        Picture orig = Filter.copy(canvas);

        double angle = Math.toRadians(360 / steps);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double x0 = 0.5 * (W - 1);     // point to rotate about
        double y0 = 0.5 * (H - 1);     // center of image

        // rotation
        for (int t = 0; t < steps; t++) { //time loop
            for (int c = 0; c < W; c++) { //cols
                for (int r = 0; r < H; r++) { //rows
                    double a = c - y0;
                    double b = r - x0;
                    int xx = (int) (+a * cos - b * sin + x0);
                    int yy = (int) (+a * sin + b * cos + y0);

                    // plot pixel (x, y) the same color as (xx, yy) if it's in bounds
                    if (xx >= 0 && xx < H && yy >= 0 && yy < W) {
                        canvas.set(c, r, pic1.get(xx, yy));
                        double percent = (steps - t) / (double) steps;
                        Color oc = orig.get(c, r);
                        Color npc = pic1.get(c, r);
                        Color temp = combine(oc, npc, percent);
                        canvas.set(c, r, temp);
                    }

                }

            }
            delay(15);
            canvas.show();
        }
    }

    /**
     * causes program to stop for a given number of milliseconds
     *
     * @param millsec
     */

    public static void delay(int millsec) {
        try {
            Thread.sleep(millsec);
        } catch (Exception e) {
            System.out.println("Error in delay function.");
        }
    }

    public static void swap(Picture canvas, Picture newPic) {

        int W = canvas.width();
        int H = canvas.height();

        // pre-condition for swapping images
        if ((W != newPic.width() || (H != newPic.height()))) {
            System.out.println("Sorry (in Transition.swap): pictures sizes do not match.");
            return;
        }

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                Color temp = newPic.get(c, r);
                canvas.set(c, r, temp);
            }
        }
        canvas.show();

    }

    /**
     * produces new Color object that is percent*c1 + (1-percent)*c2
     *
     * @param c1
     * @param c2
     * @param percent - a value between 0,1
     * @return
     */
    public static Color combine(Color c1, Color c2, double percent) {

        int red = (int) (c1.getRed() * percent + c2.getRed() * (1 - percent));
        int green = (int) (c1.getGreen() * percent + c2.getGreen() * (1 - percent));
        int blue = (int) (c1.getBlue() * percent + c2.getBlue() * (1 - percent));
        Color c3 = new Color(red, green, blue);
        return c3;
    }


    public static void fade(Picture canvas, Picture newPic, int steps) {

        int W = canvas.width();
        int H = canvas.height();

        // pre-condition for swapping images
        if ((W != newPic.width() || (H != newPic.height()))) {
            System.out.println("Sorry (in Transition.swap): pictures sizes do not match.");
            return;
        }

        Picture orig = Filter.copy(canvas);

        for (int t = 0; t < steps; t++) {
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    double percent = (steps - t) / (double) steps;
                    // System.out.println(percent);
                    Color oc = orig.get(c, r);
                    Color npc = newPic.get(c, r);
                    Color temp = combine(oc, npc, percent);
                    canvas.set(c, r, temp);
                }
            }
            delay(6);
            canvas.show();
        }
    }

    /**
     * this transition will quickly fill the screen with white or black and then reveal the next photo
     * @param canvas
     * @param pic
     */
    public static void WhiteOut(Picture canvas, Picture pic) {
        Random ran=new Random();
        int num=ran.nextInt(2);
        for (int row = 0; row < canvas.height(); row++) {
            for (int col = 0; col < canvas.width(); col++) {
                if(num==0)
                    canvas.set(col, row, Color.white);
                else if(num==1)
                    canvas.set(col,row,Color.black);
            }
            delay(2);
            canvas.show();
        }
    }

    /**
     * this transition sldies the image off of the screen downwards
     * @param canvas
     * @param pic
     */
    public static void swipeDown(Picture canvas, Picture pic) {
        for (int row = 0; row < canvas.height(); row++) {
            for (int col = 0; col < canvas.width(); col++) {
                Color temp = pic.get(col, row);
                canvas.set(col, row, temp);
            }
            delay(8);
            canvas.show();
        }
    }

    /**
     * this transition slides the image off of the screen upwards
     * @param canvas
     * @param pic
     */
    public static void swipeUp(Picture canvas, Picture pic) {
        int W = canvas.width();
        int H = canvas.height();
        for (int row = H-1; row >=0; row--) {
            for (int col = W-1; col >=0; col--) {
                Color temp=pic.get(col,row);
                canvas.set(col, row, temp);
            }
            Transition.delay(8);
            canvas.show();
        }
    }

    /**
     * this transition slides the image off of the screen to the left
     * @param canvas
     * @param pic
     */
    public static void swipeLeft(Picture canvas, Picture pic) {
        int W = canvas.width();
        int H = canvas.height();
        for (int col = W-1; col >=0; col--) {
            for (int row = H-1; row >=0; row--) {
                Color temp=pic.get(col,row);
                canvas.set(col, row, temp);
            }
            Transition.delay(8);
            canvas.show();
        }
    }

    /**
     * this transitions slides the image off screen to the right
     * @param canvas
     * @param pic
     */
    public static void swipeRight(Picture canvas, Picture pic) {
        for (int col = 0; col < canvas.width(); col++) {
            for (int row = 0; row < canvas.height(); row++) {
                Color temp = pic.get(col, row);
                canvas.set(col, row, temp);
            }
            delay(8);
            canvas.show();
        }
    }

}

