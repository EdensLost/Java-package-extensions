package lostThoughts.helpingHands;

import java.io.File;
import java.io.IOException;

public class UtilH {
//Distance
    /**
     * Used to find the euclidean distance between two points
     * d = √[(x2 – x1)2 + (y2 – y1)2]
     * 
     * @param x1 = The first x point
     * @param y1 = The first y point
     * @param x2 = The second x point
     * @param y2 = The second y point
     * 
     * @return {@code double} = The distance between both points
     */
    public static double uDist(double x1, double y1, double x2, double y2) {
        double preSquare = Math.pow((x2 - x1) * 2, 2) + Math.pow((y2 - y1) * 2, 2);
        return Math.sqrt(preSquare);
    }
//

// Waits
    //Wait Time
    /**
     * Used to make the code sleep for a specified time
     * 
     * @param timeToWait The time the code waits for
     */
    public static void uWait(double timeToWait) {
        long waitTime = 0;
        long startTime = System.currentTimeMillis();

        while (waitTime < timeToWait) {
            waitTime = System.currentTimeMillis() - startTime;
        }
    }
//

//Randoms
    /**
     * Used to generate a random value
     * 
     * @param max The maximum value
     * @return {@code int}
     */
    public static double newRand() {
        double percentage = Math.random()/Math.nextDown(1.0);
        double newDist = percentage;
        return newDist + 0;
    }
    /**
     * Used to generate a random value
     * 
     * @param max The maximum value
     * @return {@code int}
    */
    public static double newRand(double max, double min) {
        double dist = Math.abs(min) + Math.abs(max);
        double percentage = Math.random()/Math.nextDown(1.0);
        double newDist = percentage * dist;
        return newDist + min;
    }

    /**
     * Used to generate a random integer from 0 to max
     * 
     * @param max The maximum value
     * @return {@code int}
    */
    public static int randInt(int max) {
        return (int) Math.round(newRand(max, 0));
    }
    /**
     * Used to generate a random integer from min to max
     * <p> Max should be equal or greater than min
     * 
     * @param max The maximum value
     * @param min The minimum value
     * @return {@code int}
    
    */
    public static int randInt(int max, int min) {
        return (int) Math.round(newRand(max, min));
    }
//

// Random Arrays
    /**
     * Used to generate a randomized int array based on the given length and max and min values
     * 
     * @param length The length of the array
     * @param max The maximum value found in the array
     * @param min The minimum value found in the array
     * @return {@code int[]}
     
     */
    public static int[] rArray(int length, int max, int min) {
        int[] newArray = new int[length];

        for (int i = 0; i < length; i++) {
            newArray[i] = UtilH.randInt(max, min);
        }

        return newArray;
    }
    
    /**
     * Used to generate a randomized double array based on the given length and max and min values
     * 
     * @param length The length of the array
     * @param max The maximum value found in the array
     * @param min The minimum value found in the array
     * @return {@code double[]}
     
     */
    public static double[] rArray(int length, double max, double min) {
        double[] newArray = new double[length];

        for (int i = 0; i < length; i++) {
            newArray[i] = UtilH.newRand(max, min);
        }

        return newArray;
    }

//

// Console command
    /**
     * Runs a command through the console
     * 
     * @param directory = The directory to run the command in
     * @param cmdString = The command to run
     */
    public static void runCommand(File directory, String cmdString) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmdString);
            builder.directory(directory);
            builder.redirectErrorStream(true);  
            builder.start();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
//

// Get valued percent from range
    /** Gets a value based on the percentage given normalizing the 
     * difference between the low and high with a specified center
     * 
     * @param percent = The percent to get the value from
     * @param low = The lowest value possible
     * @param high = The highest value possible
     * @param center = The specified center of the value
     * 
     * @return {@code float} = The value based on the percent
     */
    public static float percentFromRange(float percent, float low, float high, float center) {
        float value = 0;

        if (percent > 0.5f) {
            percent = (percent - 0.5f) * 2;

            value = high * percent;
        }
        else if (percent < 0.5f) {
            percent = 1 - (percent * 2);

            value = low * percent;
        }
        else {
            value = center;
        }

        return value;
    }

    /** Gets a value based on the percentage given normalizing the 
     * difference between the low and high
     * 
     * @param percent = The percent to get the value from
     * @param low = The lowest value possible
     * @param high = The highest value possible
     * 
     * @return {@code float} = The value based on the percent
     */
    public static float percentFromRange(float percent, float low, float high) {
        float value = 0;
        float total = (Math.abs(low) + Math.abs(high));

        value = percent * total;

        if (low < 0) {
            value += low;
        }
        if (high < 0) {
            value += high;
        }

        return value;
    }

//


// Lerps
    /** Interpolates between the start and end value based on the given current percent
     * 
     * @param start = The value to interpolate from
     * @param end = The value to interpolate to
     * @param curPercent = The interpolating value
     * 
     * @return {@code double} = The value based on the percent
     */
    public static double doubLerp(double start, double end, double curPercent) {
        double distance = 0;

        boolean startNeg = false;
        boolean endNeg = false;

        curPercent  = Math.clamp(curPercent, 0.0, 1.0);

        if (start < 0) {
            startNeg = true;
        }
        if (end < 0) {
            endNeg = true;
        }

        if (start == end) {
            return end;
        }
        else if (startNeg == endNeg) {
            if (start > end) {
                distance = (Math.abs(start) - Math.abs(end));

                if (startNeg == true) {
                    return start + distance * curPercent;
                }
                else if (startNeg == false) {
                    return start - distance * curPercent;
                }
                
            }
            else if (start < end) {
                distance = (Math.abs(end) - Math.abs(start));

                if (startNeg == true) {
                    return start - distance * curPercent;
                }
                else if (startNeg == false) {
                    return start + distance * curPercent;
                }
            }
        }
        else if (startNeg != endNeg) {
            distance = (Math.abs(start) + Math.abs(end));
            

            if (startNeg == true) {
                double val = (start - (distance * (1.0 -curPercent)) + distance);
                if (val == -0.0) {
                    return 0;
                }
                return val;
            }
            else if (startNeg == false) {
                double val = (start + (distance * (1.0 -curPercent)) - distance);
                if (val == -0.0) {
                    return 0;
                }
                return val;
            }
        }

        return distance;
    }

    public static void interpByFract(double start, double end, double changeAmnt) {
        double interpFactor = findAbsDistance(start, end); 
    

        for (int i = 0; i < interpFactor * (1.0 / changeAmnt) + 1; i++) {
            PyJav.printl("" + doubLerp(start, end, 1.0 - ((interpFactor - (i * changeAmnt)) / interpFactor)) + 
                            " : " + (1.0 - ((interpFactor - (i * changeAmnt)) / interpFactor)));
        }
    }
//

// Distance
    /** Finds the absolute value distance between two doubles
     * 
     * @param start = The value the interpolator starts at
     * @param end = The value the interpolator ends at
     * 
     * @return {@code double} = The distance between the two numbers
     */
    public static double findAbsDistance(double start, double end) {
        boolean startNeg = false;
        boolean endNeg = false;

        double distance = 0;

        if (start < 0) {
            startNeg = true;
        }
        if (end < 0) {
            endNeg = true;
        }

        if (start == end) {
            return 0;
        }
        else if (startNeg == endNeg) {
            if (start > end) {
                distance = (Math.abs(start) - Math.abs(end));
            }
            else if (start < end) {
                distance = (Math.abs(end) - Math.abs(start));
            }
        }
        else if (startNeg != endNeg) {
            distance = (Math.abs(start) + Math.abs(end));
        }

        if (distance < 0) {
            return -distance;
        }
        else {
            return distance;
        }

        
    }

    /** Finds the vector distance between two doubles
     * 
     * @param start = The value the interpolator starts at
     * @param end = The value the interpolator ends at
     * 
     * @return {@code double} = The distance between the two numbers (negative or positive)
     */
    public static double findVectDistance(double start, double end) {
        boolean startNeg = false;
        boolean endNeg = false;

        double distance = 0;

        if (start < 0) {
            startNeg = true;
        }
        if (end < 0) {
            endNeg = true;
        }

        if (start == end) {
            return 0;
        }
        else if (startNeg == endNeg) {
            if (start > end) {
                distance = (Math.abs(start) - Math.abs(end));
            }
            else if (start < end) {
                distance = (Math.abs(end) - Math.abs(start));
            }
        }
        else if (startNeg != endNeg) {
            distance = (Math.abs(start) + Math.abs(end));
        }

        return distance;

        
    }
//


}


