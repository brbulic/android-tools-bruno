package hr.brbulic.tools;

import hr.brbulic.asserts.AssertUtils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 10/1/11
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class StreamUtils {

    private static final String READ_FROM_STRING_TAG = "ReadStringFromStream";


    public static String readStringFromStream(InputStream stream) {
        AssertUtils.notNull(stream, "Cannot use a empty stream to read string");

        StringBuilder builder = new StringBuilder();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            String string = reader.readLine();

            while (string != null) {
                builder.append(string);
                string = reader.readLine();
            }

            stream.close();
            inputStreamReader.close();
            reader.close();
        } catch (IOException e) {
            //TODO : MUST PUT THIS TO STRINGS.XML!
            android.util.Log.e(READ_FROM_STRING_TAG, "Cannot read from string");
        }
        finally {

        }
        return builder.toString();
    }


}
