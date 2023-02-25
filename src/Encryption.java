 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Scanner; import java.util.stream.IntStream;
 public class Encryption {
     static String alfa = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ !?.,";
     public void code() {
         String ret;
         Scanner sc = new Scanner(System.in);
         System.out.print("Введите строку:");
         String str = sc.nextLine();
         System.out.print("Введите ключ");
         String key = sc.nextLine();
         System.out.println("");
         System.out.println(str);
         System.out.println(key);
         ret = cryptStr(str, key);
         System.out.println("Зашифрованная строка");
         System.out.println(ret);
         try (FileWriter fileWriter = new FileWriter("codedFile.txt")){
             String text = ret;
             fileWriter.write(text);

             fileWriter.flush();
         }
         catch (IOException exception){

             System.out.println(exception.getMessage());
         }
         ret = cryptStr(ret, key);
         System.out.println("Дишифрованная строка");
         System.out.println(ret);
         try (FileWriter fileWriter = new FileWriter("file.txt")){
             String codedText = ret;
             fileWriter.write(codedText);

             fileWriter.flush();
         }
         catch (IOException exception){

             System.out.println(exception.getMessage());
         }
     }
     private static String cryptStr(String str, String key) {
         int[] codeStr = str.chars().map(i -> alfa.indexOf(i)).toArray();
         int[] codeKey = IntStream.range(0, codeStr.length / key.length() + 2).flatMap(i -> key.chars()).map(i -> alfa.indexOf(i)).limit(codeStr.length).toArray();
         StringBuilder res = new StringBuilder();
         IntStream.range(0, codeStr.length).map(i -> (codeStr[i] ^ codeKey[i])).map(i -> alfa.charAt(i)).forEach(c -> res.append((char)c));
         return res.toString();
     }
 }


