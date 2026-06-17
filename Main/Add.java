//Author = Abhisek Singh
//Project = Birthday_Reminder

class Add {
    static javax.swing.JFrame f;
    private Add() {
    }

      public static void start() {
        if(f !=null) f.dispose();

        f = new javax.swing.JFrame("Add a person.");

        java.util.ArrayList<java.io.File> images = new java.util.ArrayList<java.io.File>();

        f.setSize(1000,500);
        f.setResizable(false);
        f.setLocation(100,100);
        f.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);
        f.getContentPane().setBackground(new java.awt.Color(255,201,14));
        f.setIconImage(Icon.icon);

        javax.swing.JLabel l1,l2,l3,e1,e2,e3;
        javax.swing.JTextField t1,t2,t3;
        javax.swing.JButton b,photos;
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

        l1 = new javax.swing.JLabel("Enter the name.");
        l2 = new javax.swing.JLabel("Enter the day. ");
        l3 = new javax.swing.JLabel("Enter the month.");

        e1 = new javax.swing.JLabel();
        e2 = new javax.swing.JLabel();
        e3 = new javax.swing.JLabel();

        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();

        b = new javax.swing.JButton("SAVE");
        photos = new javax.swing.JButton("Add Photos.");

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(e1);
        f.add(e2);
        f.add(e3);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(b);
        f.add(photos);

        l1.setBounds(50,100,400,50);
        l2.setBounds(50,200,400,50);
        l3.setBounds(50,300,400,50);

        e1.setBounds(850,100,100,50);
        e2.setBounds(850,200,100,50);
        e3.setBounds(850,300,100,50);

        t1.setBounds(500,100,300,50);
        t2.setBounds(500,200,300,50);
        t3.setBounds(500,300,300,50);

        b.setBounds(200,400,150,50);
        photos.setBounds(500,400,350,50);

        java.awt.Font font = new java.awt.Font("algerian",40,40);
        java.awt.Color c = new java.awt.Color(63,72,204);

        l1.setFont(font);
        l1.setForeground(c);
        l2.setFont(font);
        l2.setForeground(c);
        l3.setFont(font);
        l3.setForeground(c);

        c = new java.awt.Color(255,230,238);
        t1.setFont(font);
        t1.setBackground(c);
        t2.setFont(font);
        t2.setBackground(c);
        t3.setFont(font);
        t3.setBackground(c);

        c=new java.awt.Color(255,0,0);
        e1.setForeground(c);
        e2.setForeground(c);
        e3.setForeground(c);

        b.setFont(font);
        c = new java.awt.Color(153,217,234);
        b.setBackground(c);
        photos.setBackground(c);
        c = new java.awt.Color(100,100,100);
        b.setForeground(c);
        photos.setFont(font);
        photos.setForeground(c);

        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent k) {
                String name = t1.getText().trim();
                int day = 0,month = 0;

                e1.setText("");
                e2.setText("");
                e3.setText("");

                if(name.length() <= 0) {
                    e1.setText("Invalid name.");
                    return;
                }
                try {
                    if(!((month = Integer.parseInt(t3.getText().trim())) > 0 && month <= 12 )) {
                        e3.setText("Invalid month.");
                        return;
                    }
                }
                catch(Exception e){
                    e3.setText("Invalid month.");
                    return;
                }
                try {
                    if(!((day = Integer.parseInt(t2.getText().trim())) >0 && present(month,day))) {
                        e2.setText("Invalid day.");
                        return;
                    }
                }
                catch(Exception e){
                    e2.setText("Invalid day.");
                    return;
                }

                save(name.toUpperCase(),month,day,images);

                f.dispose();
            }
        });

        photos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent k) {
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
                }
            }
        });

        f.setVisible(true);
    }

    public static boolean present(int month , int day) {
        int no_of_days = 0;

        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            no_of_days = 31;
            break;
            case 2:
            no_of_days = 29;
            break;
            default:
            no_of_days = 30;
        }

        if(day > no_of_days) return false;
        return true;
    }

    public static void save(String name , int month , int day ,java.util.ArrayList<java.io.File> images) {
        java.io.File f = new java.io.File("Profiles.info");
        java.io.File f2 = new java.io.File("p.info");

        try {
            if(!f.exists())
            f.createNewFile();
            java.io.BufferedReader b = new java.io.BufferedReader(new java.io.FileReader(f));
            java.io.FileWriter fw = new java.io.FileWriter(f2);

            String s="";

            while((s = b.readLine())!=null) {
                fw.write(s + "\r\n");
            }

            b.close();

            fw.write(name+ "\r\n");
            long a = 0;

            do {
                a = (long)(Math.random()*100000000.0);
            }
            while(new java.io.File("Profiles\\" + a + ".ima").exists());

            if(images.size() > 0) fw.write(true + " " + a + "\r\n");
            else fw.write(false+ "\r\n");
            fw.write(month + "\r\n");
            fw.write(day + "\r\n");
            fw.close();
            if(images.size() > 0)
            ImageDecoder.writeImage(images , a + ".ima");
        }
        catch(Exception e) {
        }

        f.delete();
        f2.renameTo(new java.io.File("Profiles.info"));
        Main.change();
    }
}