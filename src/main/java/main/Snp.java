package main;

import org.apache.commons.io.IOUtils;
import org.iq80.snappy.SnappyInputStream;
import org.iq80.snappy.SnappyOutputStream;

import java.io.*;
import java.nio.charset.Charset;

public class Snp {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println(
                    "Usage: java -jar spn.jar keys [enc] input\n" +
                            "Keys:\n" +
                            "  x - uncompress\n" +
                            "  c - compress\n" +
                            "  f - use file (instead of stdin)\n" +
                            "Input is one of:\n" +
                            "  path to file if 'f' key used\n" +
                            "  raw input string or pipe (one can specify 'enc' param for string encoding)"
            );
            System.exit(0);
        }

        boolean encodingPassed = args.length == 3;
        Charset ENCODING = encodingPassed ? Charset.forName(args[1]) : Charset.forName("UTF-8");

        String keys = args[0];
        int inpitIdx = encodingPassed ? 2 : 1;
        Object input = args.length > inpitIdx ? args[inpitIdx] : drainStdin();
        InputStream ins = keys.contains("f") ?
                new FileInputStream((String)input) :
                new ByteArrayInputStream(input instanceof String ? ((String)input).getBytes(ENCODING) : (byte[])input);

        if (keys.contains("x")) extract(ins);
        if (keys.contains("c")) compress(ins);
    }

    private static void extract(InputStream ins) throws Exception {
        InputStream sins = new SnappyInputStream(ins);
        System.out.write(IOUtils.toByteArray(sins));
        sins.close();
    }

    private static void compress(InputStream ins) throws Exception {
        OutputStream outs = new SnappyOutputStream(System.out);
        IOUtils.copy(ins, outs);
        outs.close();
    }

    private static byte[] drainStdin() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[32 * 1024];

        int bytesRead;
        while ((bytesRead = System.in.read(buffer)) > 0) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }
}
