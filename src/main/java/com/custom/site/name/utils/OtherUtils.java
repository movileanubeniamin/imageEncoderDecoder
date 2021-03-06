package com.custom.site.name.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OtherUtils {
   
   public static float clamp(float val, float min, float max) {
      return Math.max(min, Math.min(max, val));
   }
   
   public static long calcualteSize(Long amplitude) {
      amplitude = Math.abs(amplitude);
      if (amplitude <= 0)
         return 0;
      if (amplitude <= 1)
         return 1;
      if (amplitude <= 3)
         return 2;
      if (amplitude <= 7)
         return 3;
      if (amplitude <= 15)
         return 4;
      if (amplitude <= 31)
         return 5;
      if (amplitude <= 63)
         return 6;
      if (amplitude <= 127)
         return 7;
      if (amplitude <= 255)
         return 8;
      if (amplitude <= 511)
         return 9;
      if (amplitude <= 1023)
         return 10;
      return -1;
   }
   
   public static List reverseList(List myList) {
      List invertedList = new ArrayList();
      for (int i = myList.size() - 1; i >= 0; i--) {
         invertedList.add(myList.get(i));
      }
      return invertedList;
   }
   
   public static List<String[]> checkForDifferences(long[][] tableOld, long[][] tableNew) {
      List<String[]> differences = new ArrayList<String[]>();
      if (!Arrays.equals(tableNew, tableOld)) {
         for (int hour = 0; hour < tableOld.length; hour++) {
            try {
               boolean removed = true;
               for (int hour2 = 0; hour2 < tableNew.length; hour2++)
                  if (Arrays.equals(tableOld[hour], tableNew[hour2]))
                     removed = false;
               if (removed)
                  differences
                     .add(new String[] {"-", String.valueOf(tableOld[hour][0]), String.valueOf(tableOld[hour][1]),
                                        String.valueOf(tableOld[hour][2]), String.valueOf(tableOld[hour][3]),
                                        String.valueOf(tableOld[hour][4]), String.valueOf(tableOld[hour][5]),
                                        String.valueOf(tableOld[hour][6])});
            } catch (ArrayIndexOutOfBoundsException e) {
               e.printStackTrace();
               break;
            }
         }
         for (int hour = 0; hour < tableNew.length; hour++) {
            try {
               boolean added = true;
               for (int hour2 = 0; hour2 < tableOld.length; hour2++)
                  if (Arrays.equals(tableNew[hour], tableOld[hour2]))
                     added = false;
               if (added)
                  differences
                     .add(new String[] {"+", String.valueOf(tableNew[hour][0]), String.valueOf(tableNew[hour][1]),
                                        String.valueOf(tableNew[hour][2]), String.valueOf(tableNew[hour][3]),
                                        String.valueOf(tableNew[hour][4]),
                                        String.valueOf(tableNew[hour][5]), String.valueOf(tableNew[hour][6])});
            } catch (ArrayIndexOutOfBoundsException e) {
               e.printStackTrace();
               break;
            }
         }
         
         return differences;
      } else {
         return null;
      }
   }
}
