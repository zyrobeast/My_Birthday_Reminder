//Author = Abhisek Singh
//Project = Birthday_Reminder

class Remove {
      static javax.swing.JFrame f;
      private Remove() {
      }

      public static void start() {
          if(f !=null) f.dispose();

          f = new javax.swing.JFrame("Add a person.");

          javax.swing.JFrame f = new javax.swing.JFrame("Remove a person.");

          f.setSize(700,300);
          f.setResizable(false);
          f.setLocation(100,100);
          f.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
          f.setIconImage(Icon.icon);
          f.setLayout(null);
          f.getContentPane().setBackground(new java.awt.Color(255,201,14));

          javax.swing.JTextField tf = new javax.swing.JTextField();
          javax.swing.JLabel l = new javax.swing.JLabel("Enter the saved name.");
          javax.swing.JLabel e = new javax.swing.JLabel();
          javax.swing.JButton b = new javax.swing.JButton("Delete.");

          f.add(l);
          f.add(tf);
          f.add(b);
          f.add(e);

          l.setBounds(20,50,500,50);
          tf.setBounds(50,150,350,50);
          b.setBounds(450,150,200,50);
          e.setBounds(530,50,200,50);

          java.awt.Font font = new java.awt.Font("algerian",40,40);
          java.awt.Color c = new java.awt.Color(63,72,204);

          l.setFont(font);
          l.setBackground(c);

          c = new java.awt.Color(255,230,238);


          tf.setFont(font);
          tf.setBackground(c);

          b.setFont(font);
          b.setBackground(new java.awt.Color(153,217,234));
          b.setForeground(new java.awt.Color(100,100,100));

          c=new java.awt.Color(255,0,0);
          e.setForeground(c);

          b.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent k) {
                 String name = tf.getText().trim();

                 int len = Main.persons.size();
                 int num = 0;

                 for(int i = 0;i < len ;i++) {
                     if(Main.persons.get(i).getName().trim().equalsIgnoreCase(name)) {
                         num = i;
                         break;
                     }
                     if(i == len-1) {
                         e.setText("Invalid name");
                         return;
                     }
                 }

                 delete(Main.persons.get(num));

                 f.dispose();
            }
          });

          f.setVisible(true);
      }

      public static void delete(Person  person) {
          java.io.File f = new java.io.File("Profiles.info");
          java.io.File f2 = new java.io.File("p.info");

          try {
              if(!f.exists())
              f.createNewFile();
              java.io.BufferedReader b = new java.io.BufferedReader(new java.io.FileReader(f));
              java.io.FileWriter fw = new java.io.FileWriter(f2);

              String s="";
              while((s = b.readLine())!=null) {
                  if(s.equalsIgnoreCase(person.name)) {
                      String bb = b.readLine();
                      if(person.hasImage) {
                          new java.io.File("Profiles\\"+person.loc+".ima").delete();
                      }
                      b.readLine();
                      b.readLine();
                      continue;
                  }
                  fw.write(s + "\r\n");
              }
              b.close();
              fw.close();
          }
          catch(Exception e) {
          }

          f.delete();
          f2.renameTo(new java.io.File("Profiles.info"));
          Main.change();
      }
}