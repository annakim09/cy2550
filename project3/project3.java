import java.util.*;
import java.io.*;


public class project3{
  public static void main(String[] args) throws NumberFormatException, IOException{
    int words = 4;
    int capitals = 0;
    int numbers = 0;
    int symbols = 0;
    ArrayList<String> pass = new ArrayList<String>();
   
    int lines = 0;
    try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
      while (br.readLine() != null) {
        lines++;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
   
    BufferedReader br = new BufferedReader(new FileReader("words.txt"));
    ArrayList<String> wordList = new ArrayList<String>();
   
    for (int i = 0; i < lines; i++) {
      wordList.add(br.readLine());
    }
    br.close();

    ArrayList<String> listOfNumbers = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4",
    "5", "6", "7", "8", "9"));
   
   
    ArrayList<String> listOfSymbols = new ArrayList<String>(Arrays.asList("~", "!", "@",
        "#", "$", "%", "^", "&", "*", ".", ":", ";"));
       
 

    Iterator<String> input = Arrays.asList(args).iterator();
    while (input.hasNext()) {
      switch (input.next()) {
        case "-h":
        case "--help":
          System.out.println("usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]\r\n"
              + "\r\n Generate a secure, memorable password using the XKCD method\r\n"
              + "\r\n optional arguments:\r\n"
              + "    -h, --help            show this help message and exit\r\n"
              + "    -w WORDS, --words WORDS\r\n"
              + "                          include WORDS words in the password (default=4)\r\n"
              + "    -c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\r\n"
              + "                          (default=0)\r\n"
              + "    -n NUMBERS, --numbers NUMBERS\r\n"
              + "                          insert NUMBERS random numbers in the password\r\n"
              + "                          (default=0)\r\n"
              + "    -s SYMBOLS, --symbols SYMBOLS\r\n"
              + "                          insert SYMBOLS random symbols in the password\r\n"
              + "                          (default=0)");
          break;

        case "-n":
        case "--numbers":
          numbers += Integer.parseInt(input.next());
          break;
        case "-s":
        case "--symbols":
          symbols += Integer.parseInt(input.next());
        case "-w":
        case "--words":
            words = Integer.parseInt(input.next());
            break;
          case "-c":
          case "--caps":
            capitals += Integer.parseInt(input.next());
            break;
      }  
    }
   
    for (int i = 0; i < numbers; i++) {
      int rand1 = (int)(2 * Math.random());
      int rand2 = (int)(pass.size() * Math.random());
      int rand3 = (int)(listOfNumbers.size() * Math.random());
      String str = pass.get(rand2);
      String strNum = listOfNumbers.get(rand3);
      if (rand1 == 0) {
        str += strNum;
        pass.set(rand2, str);
      }
      else {
        str = strNum + str;
        pass.set(rand2, str);
      }
    }

    for (int i = 0; i < symbols; i++) {
      int rand1 = (int)(Math.random()*2);
      int rand2 = (int)(Math.random()*pass.size());
      int rand3 = (int)(Math.random()*listOfSymbols.size());
      String str = pass.get(rand2);
      String strSym = listOfSymbols.get(rand3);
      if (rand1 == 0) {
        str += strSym;
        pass.set(rand2, str);
      }
      else {
        str = strSym + str;
        pass.set(rand2, str);
      }
    }

    for (int i = 0; i < capitals; i++) {
        int rand = (int)(pass.size() * Math.random());
        String str = pass.get(rand);
        str = str.substring(0,1).toUpperCase() + str.substring(1);
        pass.set(rand, str);
    }

    for (int i = 0; i < words; i++) {
        int rand = (int) (lines * Math.random());
        pass.add(wordList.get(rand));
    }

    for (int i = 0; i < pass.size(); i++) {
      System.out.print(pass.get(i));
    }
    System.out.println("");

  }
}
