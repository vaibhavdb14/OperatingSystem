import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Pass2 {
   static Obj[] symb_table = new Obj[10];
   static Obj[] literal_table = new Obj[10];

   public Pass2() {
   }

   public static void main(String[] var0) throws IOException {
      Scanner var1 = new Scanner(System.in);
      System.out.print("ENTER TOTAL NUMBER OF SYMBOLS: ");
      int var2 = var1.nextInt();

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         System.out.print("ENTER SYMBOL NAME: ");
         String var4 = var1.next();
         System.out.print("ENTER SYMBOL ADDRESS: ");
         int var5 = var1.nextInt();
         symb_table[var3] = new Obj(var4, var5);
      }

      System.out.print("\nENTER TOTAL NUMBER OF LITERALS: ");
      var3 = var1.nextInt();

      String var13;
      for(int var11 = 0; var11 < var3; ++var11) {
         System.out.print("ENTER LITERAL NAME: ");
         var13 = var1.next();
         System.out.print("ENTER LITERAL ADDRESS: ");
         int var6 = var1.nextInt();
         literal_table[var11] = new Obj(var13, var6);
      }

      BufferedReader var12 = new BufferedReader(new FileReader("output.txt"));
      System.out.println("\n*** MACHINE CODE OUTPUT ***\n");

      while((var13 = var12.readLine()) != null) {
         String[] var14 = var13.trim().split("\\s+");
         boolean var7 = false;

         for(int var8 = 0; var8 < var14.length; ++var8) {
            String var9 = var14[var8];
            if (var9.matches("[0-9]+")) {
               System.out.print("\n" + var9 + "\t");
            } else {
               String var10;
               if (var9.matches("\\(IS,\\d+\\)")) {
                  var10 = var9.replaceAll("[^0-9]", "");
                  System.out.print(var10 + "\t");
               } else if (var9.matches("\\(RG,\\d+\\)")) {
                  var10 = var9.replaceAll("[^0-9]", "");
                  System.out.print(var10 + "\t");
               } else if (var9.matches("\\(C,\\d+\\)")) {
                  var10 = var9.replaceAll("[^0-9]", "");
                  System.out.print(var10 + "\t");
               } else {
                  int var15;
                  Obj var10001;
                  if (var9.matches("\\(S,\\d+\\)")) {
                     var15 = Integer.parseInt(var9.replaceAll("[^0-9]", ""));
                     if (var15 - 1 < symb_table.length && symb_table[var15 - 1] != null) {
                        var10001 = symb_table[var15 - 1];
                        System.out.print(var10001.addr + "\t");
                     }
                  } else if (var9.matches("\\(L,\\d+\\)")) {
                     var15 = Integer.parseInt(var9.replaceAll("[^0-9]", ""));
                     if (var15 - 1 < literal_table.length && literal_table[var15 - 1] != null) {
                        var10001 = literal_table[var15 - 1];
                        System.out.print(var10001.addr + "\t");
                     }
                  } else if (var9.matches("\\(DL,1\\)")) {
                     if (var8 + 1 < var14.length && var14[var8 + 1].matches("\\(C,\\d+\\)")) {
                        ++var8;
                        var10 = var14[var8].replaceAll("[^0-9]", "");
                        System.out.print("00\t00\t" + var10 + "\t");
                     }
                  } else if (var9.matches("\\(DL,2\\)") || var9.matches("\\(AD,\\d+\\)")) {
                     var7 = true;
                     break;
                  }
               }
            }
         }

         if (var7) {
            System.out.print("----\t");
         }
      }

      var12.close();
      var1.close();
   }
}