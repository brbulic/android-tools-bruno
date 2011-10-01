package hr.brbulic.tools;

import hr.brbulic.asserts.AssertUtils;
import org.apache.commons.logging.Log;

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

        BufferedReader reader = new BufferedReader(new InputStreamReader(bufferedInputStream));

        try {
            String string = reader.readLine();

            while (string != null) {
                builder.append(string);
                string = reader.readLine();
            }
        } catch (IOException e) {
            //TODO : MUST PUT THIS TO STRINGS.XML!
            android.util.Log.e(READ_FROM_STRING_TAG, "Cannot read from string");
        }
        return builder.toString();
    }


}
