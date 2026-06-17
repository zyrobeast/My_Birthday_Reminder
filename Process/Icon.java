//Author = Abhisek Singh
//Project = Birthday_Reminder

class Icon {
  static java.awt.Image icon;
  private Icon() {
  }

  public static void load() {
    icon = ImageDecoder.getImage("icon.ima");
   }
}