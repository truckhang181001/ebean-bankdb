package services.CIDR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CidrSingleton {
    private static CidrSingleton instance;

    public static synchronized CidrSingleton getInstance(){
        if(instance == null){
            instance = new CidrSingleton();
        }
        return instance;
    }

    private int[] splitOctet(String cidrStr){
        String[] temp = cidrStr.split("/");
        String[] ip = temp[0].split("\\.");
        return new int[] {
                Integer.parseInt(ip[0]), Integer.parseInt(ip[1]),Integer.parseInt(ip[2]), Integer.parseInt(ip[3])
        };
    }

    private Byte splitCidrPrefix(String cidrStr){
        String[] temp = cidrStr.split("/");
        return Byte.parseByte(temp[1]);
    }

    private boolean checkFormatCidr(String cidrStr){
        String ipv4Pattern = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}" +
                "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\/([0-9]|[1-2][0-9]|3[0-2]))$";
        Pattern pattern = Pattern.compile(ipv4Pattern);
        Matcher matcher = pattern.matcher(cidrStr);

        return matcher.find();
    }

    private byte[][] calculateSubnetMask(byte prefix){
        /**
         * This array contains bit value,
         * Array size: 4 rows (4 octet of IP), each row has 8 columns (1 octet has 8bit)
         * */
        byte[][] subnetMaskResult = new byte[4][8];

        int subnetMaskRows = 4;
        int subnetMaskColumns = 8;

        for (int row = 0; row < subnetMaskRows; row++){
            for (int col = 0; col < subnetMaskColumns; col++){
                if(prefix > 0){
                    subnetMaskResult[row][col] = 1;
                    prefix--;
                }
                else subnetMaskResult[row][col] = 0;
            }
        }
        return subnetMaskResult;
    }


    public Boolean validateCidr(String cidrStr){
        return cidrStr.equals(correctCidr(cidrStr));
    }

    public String correctCidr(String cidrStr){
        if(checkFormatCidr(cidrStr)){
            int[] arrOctet = splitOctet(cidrStr);
            byte cidrPrefix = splitCidrPrefix(cidrStr);
            byte[][] subnetMask = calculateSubnetMask(cidrPrefix);
            int[] arrBitToInt = {128,64,32,16,8,4,2,1};
            int[] arrLimitValue = {128,192,224,240,248,252,254,255};

            /**
             * SubnetMask array's size: 4 rows (4 octet of IP), each row has 8 columns (1 octet has 8bit)
             * */
            int subnetMaskRows = 4;
            int subnetMaskColumns = 8;

            for(int row = 0; row < subnetMaskRows; row++){
                for(int col = 0; col < subnetMaskColumns; col++){
                    /** When bit[row][col] = 0 we check previous item.
                     * CIDR block constraints octet that contain last element with value = 1
                     * If Octet does not contain any elements with a value = 1, it's decimal value must be = 0
                     * */
                    if(subnetMask[row][col] == 0){
                        /** Case Octet does not contain any elements with a value = 1 */
                        if (col == 0){
                            if(arrOctet[row] != 0) {
                                arrOctet[row] = 0;
                            }
                        }
                        else {
                            /**
                             * Base on IPv4 CIDR block
                             */
                            if(arrOctet[row] % arrBitToInt[col-1] != 0 || arrOctet[row] > arrLimitValue[col-1] || arrOctet[row] < 0) {
                                arrOctet[row] = 0;
                            }
                        }
                    }
                }
            }

            String result = arrOctet[0] + "." + arrOctet[1] + "."
                    + arrOctet[2] + "." + arrOctet[3]
                    + "/" + cidrPrefix;

            return result;
        }
        else {
            return null;
        }
    }
}
