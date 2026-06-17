//Author = Abhisek Singh
//Project = Birthday_Reminder

class Main {
    static java.util.ArrayList<Person> persons = new java.util.ArrayList<Person>(10);
    static javax.swing.JPanel panel;
    static javax.swing.JFrame f;
    public static void main(String []args) {
        Icon.load();
        load();
        f = new javax.swing.JFrame("My_Birthday_Reminder.");

       f.addWindowListener(new java.awt.event.WindowAdapter() {
              public void windowClosing(java.awt.event.WindowEvent e) {
                  f.dispose();
                  saveall();
                  System.exit(0);
              }
       });

        f.setSize(1150,600);
        f.setResizable(false);
        f.setLocation(100,100);
        f.setIconImage(Icon.icon);
        f.getContentPane().setBackground(new java.awt.Color(255,201,14));
        f.setLayout(null);

        panel = new javax.swing.JPanel();
        javax.swing.JLabel label = new javax.swing.JLabel("My Friends...");
        javax.swing.JButton add = new javax.swing.JButton("Add a friend.");
        javax.swing.JButton remove = new javax.swing.JButton("Remove a friend.");
        javax.swing.JScrollPane pane = new javax.swing.JScrollPane(panel,javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        f.add(pane);
        f.add(label);
        f.add(add);
        f.add(remove);

        java.awt.Font font = new java.awt.Font("algerian",20,20);

        pane.setBounds(20,75,1050,480);
        label.setBounds(20,20,400,50);
        add.setBounds(420,20,200,40);
        remove.setBounds(700,20,220,40);

        panel.setBackground(new java.awt.Color(231,247,183));
        label.setFont(font);
        add.setFont(font);
        remove.setFont(font);
        label.setForeground(java.awt.Color.red);
        panel.setLayout(null);

        int size = persons.size();
        for(int i = 0;i < size;i++) {
            javax.swing.JPanel p = persons.get(i).panel;
            panel.add(p);
            p.setBounds(10,i*50+i*5+10,1000,50);
        }

        panel.setPreferredSize(new java.awt.Dimension(1050,size*50+size*5+10));
        pane.setPreferredSize(new java.awt.Dimension(1050,480));

        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Add.start();
            }
        });

        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Remove.start();
            }
        });

        f.setVisible(true);
    }

    public static void load() {
        persons.clear();
        try {
            java.io.BufferedReader b = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("Profiles.info")));

            String s = "";
            boolean finished = false;
            while(!finished) {
                String name = b.readLine();
                if(name == null)break;
                String bb = b.readLine();
                int month = Integer.parseInt(b.readLine());
                int day = Integer.parseInt(b.readLine());

                if(bb.charAt(0) == 't') persons.add(new Person(name,month,day,bb.substring(bb.lastIndexOf(' ')+1,bb.length()),true));
                else persons.add(new Person(name,month,day,"",false));
            }
            b.close();
        }
        catch(Exception e) {
        }

        arrange();
    }

    public static void change() {
        java.awt.Component[] list = panel.getComponents();
        for(java.awt.Component c : list) panel.remove(c);
        load();
        int size = persons.size();
        for(int i = 0;i < size;i++) {
            javax.swing.JPanel p = persons.get(i).panel;
            panel.add(p);
            p.setBounds(10,i*50+i*5+10,1000,50);
        }
        panel.revalidate();
        panel.repaint();
        panel.setPreferredSize(new java.awt.Dimension(1050,size*50+size*5+10));
        f.revalidate();
        f.repaint();
    }

    public static void arrange() {
        int size = persons.size();

        for(int i = 0;i < size-1 ;i++) {
            int min = i;
            int diff = 0;
            Person k = persons.get(i);
            for(int j = i+1;j < size;j++) {
                if(k.name.charAt(0) != persons.get(j).name.charAt(0)) {
                    int d = k.name.compareTo(persons.get(j).name);
                    if(d > diff) {
                        min = j;
                        diff = d;
                    }
                }
            }

            if(min != i) {
                persons.set(i,persons.get(min));
                persons.set(min,k);
            }
        }

        for(int i = 0;i < size-1 ;i++) {
            int min = i;
            int diff = 0;
            Person k = persons.get(i);
            for(int j = i+1;j < size;j++) {
                if(k.name.charAt(0) == persons.get(j).name.charAt(0)) {
                    int d = k.name.compareTo(persons.get(j).name);
                    if(d > diff) {
                        min = j;
                        diff = d;
                    }
                }
            }

            if(min != i) {
                persons.set(i,persons.get(min));
                persons.set(min,k);
            }
        }
    }

    public static void saveall() {
        int size = persons.size();

        java.io.FileWriter fw = null;

        try {
           fw = new java.io.FileWriter(new java.io.File("Profiles.info"));
        }
        catch(Exception e) {
        }

        for(int i = 0;i < size;i++) {
            try {
                fw.write(persons.get(i).name.toUpperCase()+"\r\n");
                fw.write(persons.get(i).hasImage+ " " +persons.get(i).loc+"\r\n");
                fw.write(persons.get(i).month+"\r\n");
                fw.write(persons.get(i).date+"\r\n");
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