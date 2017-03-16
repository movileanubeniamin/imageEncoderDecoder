package com.custom.site.name;

import java.util.*;

import com.custom.site.name.model.RGB;
import com.custom.site.name.model.YUV;

/**
 * @author bmovileanu
 *
 */
public class FirstLevelEncoderDecoder {
   private static final double Y_R = 0.299;
   private static final double Y_G = 0.587;
   private static final double Y_B = 0.114;
   
   private static final double U_R = 0.1687;
   private static final double U_G = 0.3312;
   private static final double U_B = 0.5;
   
   private static final double V_R = 0.5;
   private static final double V_G = 0.4186;
   private static final double V_B = 0.0813;
   
   public YUV convertRgbToYuv(RGB rgb) {
      YUV yuv = new YUV();
      yuv.setY(Math.round(rgb.getR() * Y_R + rgb.getG() * Y_G + rgb.getB() * Y_B));
      yuv.setU(Math.round(rgb.getR() * U_R + rgb.getG() * U_G + rgb.getB() * U_B));
      yuv.setV(Math.round(rgb.getR() * V_R + rgb.getG() * V_G + rgb.getB() * V_B));
      return yuv;
   }
   
   public List<YUV> convertRGBImageToYUV(List<RGB> rgbImage) {
      List<YUV> yuvImage = new ArrayList<YUV>();
      for (RGB rgb : rgbImage)
         yuvImage.add(convertRgbToYuv(rgb));
      return yuvImage;
   }
   
   public List<RGB> convertListToRgb(List<Integer> image) {
      System.out.println(new Date());
      List<RGB> rgbList = new ArrayList<RGB>();
      for (int index = 3; index < image.size();)
         rgbList.add(new RGB(image.get(index++), image.get(index++), image.get(index++)));
      System.out.println(new Date());
      return rgbList;
   }

   public Map<String, long[][]> convertYuvToMatrix(List<YUV> yuvList, int width, int height) {
      Map<String, long[][]> imageMatrix = new HashMap<String, long[][]>();
      long[][] yMatrix = new long[width][height];
      long[][] uMatrix = new long[width][height];
      long[][] vMatrix = new long[width][height];
      for(int i = 0; i < yuvList.size(); i++){
         YUV yuvPixel = yuvList.get(i);
         yMatrix[i/height][i%height] = yuvPixel.getY();
         uMatrix[i/height][i%height] = yuvPixel.getU();
         vMatrix[i/height][i%height] = yuvPixel.getV();
      }
      imageMatrix.put("y", yMatrix);
      imageMatrix.put("u", uMatrix);
      imageMatrix.put("v", vMatrix);
      return imageMatrix;
   }

}
