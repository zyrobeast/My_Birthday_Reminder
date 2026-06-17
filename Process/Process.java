//Author = Abhisek Singh
//Project = Birthday_Reminder

class Process {
    static java.util.ArrayList<Person> persons = new java.util.ArrayList<Person>(10);
    static boolean isleap = false;
    public static void main(String []args) {
        Icon.load();
        load();

        java.time.LocalDate today = java.time.LocalDate.now();
        int month = today.getMonthValue();
        int date = today.getDayOfMonth();
        isleap = today.isLeapYear();
        javax.swing.JFrame f = new javax.swing.JFrame("My_Birthday_Reminder.");


        f.setSize(710,400);
        f.setResizable(false);
        f.setLocation(100,100);
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        f.setIconImage(Icon.icon);
        f.getContentPane().setBackground(new java.awt.Color(255,201,14));
        f.setLayout(null);

        javax.swing.JPanel panel = new javax.swing.JPanel();
        javax.swing.JLabel label = new javax.swing.JLabel("Upcoming Birthdays...");
        javax.swing.JButton start = new javax.swing.JButton("Show in app.");
        javax.swing.JScrollPane pane = new javax.swing.JScrollPane(panel,javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        f.add(pane);
        f.add(start);
        f.add(label);

        java.awt.Font font = new java.awt.Font("algerian",20,20);

        pane.setBounds(20,75,670,290);
        label.setBounds(20,20,400,50);
        start.setBounds(420,20,200,40);

        panel.setBackground(new java.awt.Color(231,247,183));
        label.setFont(font);
        start.setFont(font);
        label.setForeground(java.awt.Color.red);
        panel.setLayout(null);

        int size = persons.size();
        int s = 0;
        for(int i = 0;i < size;i++) {
          int diff = 0;
          if((diff = get_diff(persons.get(i).date,persons.get(i).month,date,month))<=10 && diff >= 0) {
            String inf = persons.get(i).name.toUpperCase()+ " -" +persons.get(i).date+" "+Person.getMonth(persons.get(i).month);
            javax.swing.JLabel jk = new javax.swing.JLabel(inf);
            jk.setBackground(new java.awt.Color(153,217,234));
            panel.add(jk);
            jk.setFont(font);
            jk.setForeground(java.awt.Color.red);
            jk.setBounds(10,s*50+s*5+10,670,50);
            s++;
          }
        }

        panel.setPreferredSize(new java.awt.Dimension(670,s*50+s*5+10));
        pane.setPreferredSize(new java.awt.Dimension(670,290));

        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd.exe /c start My_Birthday_Reminder.exe").waitFor();
                }
                catch(Exception fgf) {
                }
                System.exit(0);
            }
        });

        if(s > 0)
        f.setVisible(true);
        else
        System.exit(0);
    }

    public static void load() {
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

                persons.add(new Person(name,month,day));
            }
        }
        catch(Exception e) {
        }
    }

    public static int get_diff(int a ,int b,int c,int d) {
        if(b == d) {
            return a-c;
        }
        if(d == 12 && b == 1) {
            return a+get_days(12)-c;
        }
        if(d+1 == b) {
            return a+get_days(d)-c;
        }
        return 123;
    }

    public static int get_days(int a ) {
          switch(a) {
           case 1:
              return 31;
           case 2:
              if(isleap)
              return 29;
              else
              return 28;
           case 3:
              return 31;
           case 4:
              return 30;
           case 5:
              return 31;
           case 6:
              return 30;
           case 7:
              return 31;
           case 8:
              return 31;
           case 9:
              return 30;
           case 10:
              return 31;
           case 11:
              return 30;
           case 12:
              return 31;
        }
        return 123;
    }
}