//Author = Abhisek Singh
//Project = Birthday_Reminder

import java.io.File;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
class Person {
   static java.util.ArrayList<java.awt.image.BufferedImage> images = new java.util.ArrayList<java.awt.image.BufferedImage>();
   static java.util.ArrayList<javax.swing.ImageIcon> image = new java.util.ArrayList<javax.swing.ImageIcon>();
   String name;
   int date,month;
   JLabel info;
   JPanel panel;
   JButton b;
   JButton addimage;
   int in = 0;
   static javax.swing.JFrame f;
   boolean hasImage = false;
   String loc;
   static java.awt.Font font1 = new java.awt.Font("algerian",20,20);
   static java.awt.Font font2 = new java.awt.Font("algerian",30,30);
   static java.awt.Color color1 = new java.awt.Color(153,217,234);
   static java.awt.Color color2 = new java.awt.Color(255,0,0);
   static java.awt.Color color3 = new java.awt.Color(255,201,14);
   protected Person(String a ,int mo , int da ,String loc , boolean hasImage) {
       name = a;
       date = da;
       month = mo;

       panel = new JPanel();
       info = new JLabel(name.toUpperCase());
       b = new JButton("Show Profile");
       addimage = new JButton("Add Image");

       panel.setLayout(null);
       panel.add(info);
       panel.add(b);
       panel.add(addimage);

       panel.setBackground(color1);
       info.setBounds(10,10,600,30);
       info.setForeground(color2);
       info.setFont(font2);
       b.setBounds(620,5,170,40);
       addimage.setBounds(800,5,170,40);
       b.setFont(font1);
       addimage.setFont(font1);

       b.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent e) {
               start();
           }
       });

       addimage.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent e) {
               addimage();
           }
       });

       this.hasImage = hasImage;
       this.loc = loc;
   }

   public void loadImage(String loc) {
       ImageDecoder.getImages(loc,images);

       int size = images.size();

       for(int i = 0;i < size ; i++) {
           image.add(getImage(i));
       }
   }

   public  void start() {
       if(f != null) f.dispose();

       if(hasImage)  loadImage("Profiles\\"+loc+".ima");

       in = 0;
       f = new javax.swing.JFrame(name.toUpperCase());

       f.addWindowListener(new java.awt.event.WindowAdapter() {
              public void windowClosing(java.awt.event.WindowEvent e) {
                  images.clear();
                  image.clear();
                  f.dispose();
              }
       });

       f.setSize(1110,550);
       f.setResizable(false);
       f.setLocation(100,100);
       f.setIconImage(Icon.icon);
       f.getContentPane().setBackground(color3);
       f.setLayout(null);

       JButton prev,next;

       JLabel photo,name_info,dob;

       name_info = new JLabel("Name = " + name);
       dob = new JLabel("Birthday = "+date +" "+getMonth(month));
       photo = new JLabel();

       prev = new JButton("Previous Image");
       next = new JButton("Next Image");

       f.add(name_info);
       f.add(photo);
       f.add(dob);
       f.add(prev);
       f.add(next);

       photo.setBounds(20,30,700,500);
       name_info.setBounds(750,20,450,50);
       dob.setBounds(750,80,450,50);
       prev.setBounds(800,300,200,50);
       next.setBounds(800,400,200,50);

       java.awt.Font font = font1;

       photo.setFont(font);
       name_info.setFont(font);
       dob.setFont(font);
       prev.setFont(font);
       next.setFont(font);

       java.awt.Color color = java.awt.Color.red;

       photo.setForeground(color);
       name_info.setForeground(color);
       dob.setForeground(color);
       prev.setForeground(color);
       next.setForeground(color);

       color = color1;

       prev.setBackground(color);
       next.setBackground(color);

       int size = images.size();

       if(size > 0) {
           photo.setIcon(getImage(0));
           if(size == 1) {
               prev.setVisible(false);
               next.setVisible(false);
           }
           else {
               prev.setVisible(false);
           }
       }
       else {
           photo.setText("                                     No Images");
           prev.setVisible(false);
           next.setVisible(false);
       }

       next.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent e) {
               int size = image.size();
               if(in < size-1) {
                   in++;
                   photo.setIcon(image.get(in));
                   if(in == size-1)
                      next.setVisible(false);
                   if(in > 0)
                      prev.setVisible(true);
               }
           }
       });

       prev.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent e) {
               int size = image.size();
               if(in > 0) {
                   in--;
                   photo.setIcon(image.get(in));
                   if(in == 0)
                     prev.setVisible(false);
                   if(in < size-1)
                      next.setVisible(true);
               }
           }
       });

       f.setVisible(true);
   }

   public String getName() {
       return name;
   }

   public static String getMonth(int a) {
       switch(a) {
           case 1:
              return "January";
           case 2:
              return "February";
           case 3:
              return "March";
           case 4:
              return "April";
           case 5:
              return "May";
           case 6:
              return "June";
           case 7:
              return "July";
           case 8:
              return "August";
           case 9:
              return "September";
           case 10:
              return "October";
           case 11:
              return "November";
           case 12:
              return "December";
       }
       return null;
   }

   public javax.swing.ImageIcon getImage(int a) {
       java.awt.image.BufferedImage i = images.get(a);
       java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(700,450,java.awt.image.BufferedImage.TYPE_INT_ARGB);
       java.awt.Graphics g = image.getGraphics();

       double width = i.getWidth();
       double height = i.getHeight();
       double ratio = (width + 1.0) / height;

       double w = 0.0,h = 0.0,h2 = 0.0,w2 = 0.0;
           w = 700.0;
           h = height / width *700.0;

           h2 = 450.0;
           w2 = w / h *450.0;

       if(w2 > w) {
           w2 = w;
           h2 = h;
       }

       g.drawImage(i,(int)((700-w2)/2),(int)((450-h2)/2),(int)w2,(int)h2,null);

       return new javax.swing.ImageIcon(image);
   }

   public void addimage() {
        java.util.ArrayList<java.io.File> images = new java.util.ArrayList<java.io.File>();
        javax.swing.JFrame f = new javax.swing.JFrame();
        f.setIconImage(Icon.icon);
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();

        String[] filetype = {".png",".jpeg",".bmp",".jpg"};
        String des = "\".png\",\".jpeg\",\".bmp\",\".jpg\"";

        jfc.setDialogTitle("Choose the photos.");
        jfc.setMultiSelectionEnabled(true);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        jfc.setFileHidingEnabled(true);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
        public boolean accept(java.io.File f) {
          if(f.isDirectory())
           return true;
          for(int i = 0;i < filetype.length; i++)
          if(f.getName().toLowerCase().endsWith(filetype[i]))
           return true;
          return false;
        }

        public String getDescription() {
         return des;
        }
       });
        jfc.setFileView(new javax.swing.filechooser.FileView() {
        public javax.swing.Icon getIcon(java.io.File f) {
        return javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(f);
        }
       });
       int r = jfc.showSaveDialog(f);

       java.io.File []loc = null;
       java.io.File []loc2 = null;

       if(r == javax.swing.JFileChooser.APPROVE_OPTION)
          loc = jfc.getSelectedFiles();

       if(loc != null) {
          int len = loc.length;
           for(int i = 0;i < len ;i++) {
               if(loc[i].isDirectory()) {
                  java.io.File[] files = loc[i].listFiles();

                  int l = files.length;

                  for(int m = 0;m < l;m++) {
                      String ex = files[m].getName().toLowerCase();
                      if(!new java.io.File(ex).isDirectory())
                      if(ex.endsWith(".png")||ex.endsWith(".bmp")||ex.endsWith(".jpeg")||ex.endsWith(".jpg")) {
                         images.add(files[m]);
                      }
                  }
               }
               else {
                   images.add(loc[i]);
               }
          }
          java.io.BufferedReader b = null;
          java.io.FileWriter fw = null;

          if(this.loc.length() == 0 && images.size() > 0) {
            long a = 0;

            do {
                a = (long)(Math.random()*100000000.0);
            }
            while(new java.io.File("Profiles\\" + a + ".ima").exists());
            this.loc = ""+a;
            hasImage = true;
          }

          try {
              if(!new java.io.File("Profiles\\"+this.loc+".ima").exists()) {
                  new java.io.File("Profiles\\"+this.loc+".ima").createNewFile();
              }
              b = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("Profiles\\"+this.loc+".ima")));

              try {
                  fw = new java.io.FileWriter(new java.io.File("jk.ima"));
              }
              catch(Exception e) {
              }

              String s = "";
              while((s = b.readLine()) != null) {
                  File temp = new File(s);
                  if(temp.exists()) {
                      try {
                          fw.write(s+"\r\n");
                      }
                      catch(Exception ghj) {
                      }
                  }
              }
              b.close();

              int size = images.size();

              for(int i = 0;i < size ;i++) {
                  try {
                      fw.write(images.get(i).getAbsolutePath()+"\r\n");
                  }
                  catch(Exception e) {
                  }
              }

              try {
                 fw.close();
              }
              catch(Exception e) {
              }
              new File("Profiles\\"+this.loc+".ima").delete();
              new File("jk.ima").renameTo(new File("Profiles\\"+this.loc+".ima"));
          }
          catch(Exception e) {
              System.out.println(e);
          }
       }
   }
}