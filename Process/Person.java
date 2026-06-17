//Author = Abhisek Singh
//Project = Birthday_Reminder

import java.io.File;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
class Person {
   String name;
   int date,month;
   protected Person(String a ,int mo , int da) {
       name = a;
       date = da;
       month = mo;
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
}