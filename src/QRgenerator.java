import com.google.zxing.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRgenerator{
    public static String generateQRCode(String text,String filename){
        try{
            int width = 300;
            int height = 300 ;
            BitMatrix matrix = new MultiFormatWriter().encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    width,
                    height
            );
            Path path = FileSystems.getDefault().getPath(filename);
            MatrixToImageWriter.writeToPath(matrix,"PNG",path);
            return filename;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
