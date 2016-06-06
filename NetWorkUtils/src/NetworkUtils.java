import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.TreeMap;

/**
 * Created by ankur on 06/06/16.
 */


public class NetworkUtils {

    public static String getHashmac(TreeMap<String, String> param) {

        StringBuilder poi = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (String key: param.keySet()) {
            String value = param.get(key);
            poi.append(value);
        }

//        String mykey = "my_private_key";
//        String pubkey = "my_public_key";

        String mykey = "04b45f2b62e0d10bda5938cf2dfa743f";
        //  String pubkey = "035ac616e8df73f4cad99b8fa6447c99";
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(mykey.getBytes(),
                    "HmacSHA1");
            mac.init(secret);
            byte[] digest = mac.doFinal(poi.toString().getBytes());
//            s = new String(digest);
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result.toString();
    }

    public static void main(String... args) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("pnr", "1234567890");
        param.put("format", "json");
        param.put("pbapikey", "035ac616e8df73f4cad99b8fa6447c99");
        System.out.println(getHashmac(param));
    }

}
