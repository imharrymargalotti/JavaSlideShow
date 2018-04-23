/**
 * Created by harrymargalotti on 3/21/17.
 */

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Slideshow {
    public static void main(String[] args) {

        /**
        The code below is grabbing a Width and a Height
         both as integers from the user. These dimensions are used for
         every displayed photo
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Width");
        int width=input.nextInt(); //HEIGHT AND WIDTH TO SCALE ALL PHOTOTS TO
        System.out.println("Enter a Height");
        int height=input.nextInt();

        /**
         * I created a file object pointing to the directory "img/"
         * This allows the whoever runs the program to drop in any additional .jpg photos
         * and have the program still run properly
         * Then I read through all of the files in the directory using the for loop below,
         * if the file contains .jpg in its path (its a photo) it is placed in an array of picture objects
         * the counting variable 'numPics' allows for the number of photos to be tracked
         * in addition, it works as an index for adding new photos to the array 'pictures'
         */
        File folder = new File("img/");
        File[] listOfFiles = folder.listFiles();
        int x=listOfFiles.length;

        Picture [] pictures=new Picture[x];
        int numPics=0;

        for (int i = 0; i < x; i++) {
            if (listOfFiles[i].getPath().contains(".jpg")){
                Picture temp=new Picture(listOfFiles[i].getPath());
                pictures[numPics]=temp;
                numPics++;
            }
        }

        /**
         * Creates the initial canvas with the user inputted dimensions
         * creates random object to use for 3 random integers
         * the random photo variable is dynamic
         *
         * 1 random integer for photo selection, 1 for filter selection, and 1 for transition selection
         *
         * I LEFT THE DELAY STATEMENT BECAUSE IT ALLOWS THE PHOTO TO PAUSE AND BE ADORED BEFORE STARTING THE NEXT TRANSITION
         *
         * Picture pic1 is whatever random photo is selected from the picture array pictures[]
         * any selected filter is applied to pic1 and every transition is from the canvas to pic1
         * the 3 random numbers are then generated each iteration. Using if/else if statements a filter is applied to the scaled
         * photo and it is then transitioned to.
         */
        Picture canvas = new Picture(width, height);
        Random rand = new Random();//use for random numbers for photo filter and transition


        StdAudio.play("photoGiraffe.wav");//you're welcome


        //infinite loop to run slideshow
        while(true){
            Transition.delay(1200);

            //RANDOM NUMBERS: Manipulate as you add filters/transitions
            int randPic=rand.nextInt(numPics);
            int randFilter=rand.nextInt(6);
            int randTrans=rand.nextInt(8); //8

            Picture pic1=pictures[randPic];

            pic1=Filter.scale(pic1,width,height);

            //RANDOM FILTERS
            if (randFilter==0)
                pic1=Filter.mirror(pic1);

            else if(randFilter==1)
                pic1=Filter.glass(pic1);

            else if(randFilter==2)
                pic1=Filter.grayscale(pic1);

            else if(randFilter==3)
                pic1=Filter.Invert(pic1);

            else if(randFilter==4)
                pic1=Filter.pop(pic1);

            else if(randFilter==5)
                pic1=Filter.quad(pic1);

            //RANDOM TRANSITIONS
            if (randTrans==0)
                Transition.swap(canvas,pic1);

            else if (randTrans==1)
                Transition.swipeDown(canvas,pic1);

            else if(randTrans==2){
                Transition.swipeUp(canvas,pic1);
            }

            else if (randTrans==3)
                Transition.fade(canvas,pic1,60);

            else if(randTrans==4)
                Transition.swipeLeft(canvas,pic1);

            else if (randTrans==5)
                Transition.swipeRight(canvas,pic1);

            else if(randTrans==6)
                Transition.WhiteOut(canvas,pic1);


        }

    }
}