//Author = Abhisek Singh
//Project = Birthday_Reminder

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

class ImageDecoder {
    private ImageDecoder() {
    }

    public static Image getImage(String loc) {
        BufferedImage image = null;
        try {
            FileReader file = new FileReader(loc);
            BufferedReader b = new BufferedReader(file);
            int width = Integer.parseInt(b.readLine());
            int height = Integer.parseInt(b.readLine());
            image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    int p = Integer.parseInt(b.readLine());
                    image.setRGB(j, i, p);
                }
            }
            b.close();
        }
        catch (Exception e) {
            System.exit(0);
        }
        return image;
    }

    public static void getImages(String loc , java.util.ArrayList<java.awt.image.BufferedImage> images) {
        BufferedReader b = null;
        FileWriter fw = null;
        images.clear();
        try {
            b = new BufferedReader(new FileReader(new File(loc)));
            java.util.ArrayList<String> locations = new java.util.ArrayList<String>();

            String s = "";
            while((s = b.readLine()) != null) {
                File temp = new File(s);
                if(temp.exists()) {
                    try {
                        java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(temp);
                        images.add(image);
                        locations.add(s);
                    }
                    catch(Exception ghj) {
                    }
                }
            }
            b.close();

            try {
                fw = new FileWriter(new File(loc));
            }
            catch(Exception e) {
            }

            int size = locations.size();
            for(int i = 0;i < size ;i++) {
                try {
                    fw.write(locations.get(i)+"\r\n");
                }
                catch(Exception e) {
                }
            }

            try {
                fw.close();
            }
            catch(Exception e) {
            }
        }
        catch(Exception e) {
        }
    }

    public static void writeImage(java.util.ArrayList<java.io.File> images , String loc) {
        int len = images.size();
        java.io.FileWriter fw = null;

        try {
             fw= new java.io.FileWriter(new java.io.File("Profiles\\" + loc));
        }
        catch(Exception e) {
        }

        for(int i = 0;i < len;i++) {
            try {
                fw.write(images.get(i).getAbsolutePath() + "\r\n");
            }
            catch(Exception e) {
            }
        }

        try {
        fw.close();
        }
        catch(Exception e) {
        }
    }
}