import java.awt.Color;
import java.util.Random;


public class Filter {

    public static int random(int a, int b) {
        Random rand=new Random();
        int random=rand.nextInt(a + (b-a+1));
        return random;
    }

    /**
     * mixes pixels to replicate looking at the photo through glass
     * @param pic1
     * @return
     */
    public static Picture glass(Picture pic1){
       //System.out.println("THIS IS GLASSSSSSSSS");
        int W=pic1.width();
        int H=pic1.height();

        Picture pic2 = new Picture(W,H);

            for (int x = 0; x < W; x++) {
                for (int y = 0; y < H; y++) {
                    int xx = (W  + x + random(-5,5)) % W;
                    int yy = (H + y + random(-5,5)) % H;
                    Color color = pic1.get(xx, yy);
                    pic2.set(x, y, color);
                }
        }
            return pic2;
    }

    /***
     * returns a grayscale copy of the original picture
     * @param origPic - color image
     * @return grayscale copy
     */
    public static Picture grayscale(Picture origPic) {
        // create a empty Picture of an appropriate size
        int W = origPic.width();
        int H = origPic.height();
        Picture newPic = new Picture(W, H);

        // nested loop over all pixels in the Picture
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {

                //   get pixel from original image
                Color col = origPic.get(c, r);
                //   manipulate the pixel value
                Color gray = Luminance.toGray(col);
                //   set the pixel in the new Image
                newPic.set(c, r, gray);
            }
        }

        // return the new image
        return newPic;
    }

    public static Picture tint(Picture origPic) {
        int W = origPic.width();
        int H = origPic.height();

        Picture newPic = new Picture(W, H);
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                //   get pixel from original image
                Color col = origPic.get(c, r);
                int R = col.getRed();
                int B = col.getGreen();
                int G = col.getBlue();

                //   manipulate the pixel value
                if (R>=80)
                    R = R-50;

                if(G>=80)
                    G = G-50;

                if(B>=80)
                    B = B-50;
                Color newCol = new Color(R, G, B);
                //   set the pixel in the new Image
                newPic.set(c, r, newCol);
            }
        }
        //return the new image
        return newPic;
    }

    /**
     * * this method will take a photo and break it into 4 smaller duplicates in a 4x4 grid
     * @param origPic
     * @return
     */
    public static Picture quad(Picture origPic) {
        int W = origPic.width();
        int H = origPic.height();

        Picture newPic = new Picture(W, H);
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                //   get pixel from original image
                Color col = origPic.get(c, r);
                int R = col.getRed();
                int B = col.getGreen();
                int G = col.getBlue();
                Color newCol = new Color(R, G, B);
                //   set the pixel in the new Image
                newPic.set(c/2, r/2, newCol); //upper left
                newPic.set((c+W)/2, r/2, newCol); //upper right
                newPic.set(c/2, (r+H)/2, newCol); //lower left
                newPic.set((c+W)/2, (r+H)/2, newCol); //lower right
            }
        }
        //return the new image
        return newPic;
    }

    /**
     * * This method takes a photo and inverts the RGB color channels
     * @param origPic
     * @return
     */
    public static Picture Invert(Picture origPic){
        int W=origPic.width();
        int H=origPic.height();

        Picture newPic=new Picture(W,H);
        for (int r = 0; r < H; r++){
            for (int c = 0; c < W; c++){
                //   get pixel from original image
                Color col = origPic.get(c, r);
                int R=col.getRed();
                int B=col.getGreen();
                int G=col.getBlue();

                //   manipulate the pixel value
                R=255-R;
                G=255-G;
                B=255-B;
                Color newCol = new Color(R,G,B);
                //   set the pixel in the new Image
                newPic.set(c, r,newCol);
            }
        }

        //return the new image
        return newPic;
    }

    public static Picture mirror(Picture origPic){
        System.out.println("MIRROR");
        int W=origPic.width();
        int H=origPic.height();
        Picture newPic=new Picture(W,H);
        /*
        for (int c = H-1; c >0 ; c--) {
            for (int r = W-1; r >0 ; r--) {
                Color temp=origPic.get(r,c);
                newPic.set(W-c,H-r,temp);

            }

        }
        */
        for (int r = 0; r < H; r++){
            for (int c = 0; c < W; c++){
                Color temp = origPic.get(H-1-r, W-1-c);
                newPic.set(c,r,temp);
            }
        }

    return newPic;
    }

    /**
     * * This method takes a photo and inverts the RGB color channels
     * @param origPic
     * @return
     */
    public static Picture pop(Picture origPic){
        int W=origPic.width();
        int H=origPic.height();

        Picture newPic=new Picture(W,H);
        for (int r = 0; r < H; r++){
            for (int c = 0; c < W; c++){
                //   get pixel from original image
                Color col = origPic.get(c, r);
                int R=col.getRed();
                int B=col.getGreen();
                int G=col.getBlue();

                //   manipulate the pixel value
                if (R>G+30 && R>B+30){
                    G-=G;
                    B-=B;
                }

                if (G>B+30 && G>R+30){
                    R-=R;
                    B-=B;
                }
                if(B>R+30 && B>G+30){
                    R-=R;
                    G-=G;
                }


                Color newCol = new Color(R,G,B);
                //   set the pixel in the new Image
                newPic.set(c, r,newCol);
            }
        }

        //return the new image
        return newPic;
    }

    /**
     * creates a scaled copy of the original picture
     * @param origPic
     * @param newW
     * @param newH
     * @return
     */
    public static Picture scale(Picture origPic, int newW, int newH){

        int origW = origPic.width();
        int origH = origPic.height();
        Picture newPic = new Picture(newW, newH);

        for (int r = 0; r < newH; r++){
            for (int c = 0; c < newW; c++){

                int x  = (int)(((double)c / newW)*origW);
                int y  = (int)(((double)r / newH)*origH);

                Color pixel = origPic.get(x, y);
                newPic.set(c, r, pixel);


            }
        }

        // return the new image
        return newPic;

    }

    /**
     * returns a copy of the given original image
     * @param orig
     * @return
     */
    public static Picture copy(Picture orig){
        return scale(orig, orig.width(), orig.height());
    }



}
