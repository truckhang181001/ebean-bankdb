package services.CIDR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cidr {
    /**
     * This array contains bit value,
     * Array size: 4 rows (4 octet of IP), each row has 8 columns (1 octet has 8bit)
     * */
    private byte[][] subnetMask;

    /**
     * This array contains 4 octet of INPUT
     */
    private int[] arrOctet;
    private byte cidrPrefix;

    public Cidr(String cidrStr){
        if(isCidr(cidrStr)){
            String[] temp = cidrStr.split("/");
            String[] ip = temp[0].split("\\.");

            this.cidrPrefix = Byte.parseByte(temp[1]);
            this.arrOctet = new int[] {
                    Integer.parseInt(ip[0]), Integer.parseInt(ip[1]),Integer.parseInt(ip[2]), Integer.parseInt(ip[3])
            };
            this.subnetMask = calculateSubnetMask(this.cidrPrefix);
        }
        else throw new IllegalArgumentException("IP CIDR format is incorrect");
    }

    /**
     * This function validates format of CIDR INPUT
     * True format: octet01.octet02.octet03.octet04/prefix
     * Condition: 0 <= octet <= 225, 0 <= prefix <= 32
     * Example: 192.168.0.0/12
     * @param cidrStr
     * @return
     */
    private boolean isCidr(String cidrStr){
        String ipv4Pattern = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}" +
                "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\/([0-9]|[1-2][0-9]|3[0-2]))$";
        Pattern pattern = Pattern.compile(ipv4Pattern);
        Matcher matcher = pattern.matcher(cidrStr);

        return matcher.find();
    }

    /**
     * This encodes the CIDR PREFIX length associated with an IPv4 address or network to SUBNETMASK
     * @param prefix
     * @return
     */
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

    /**
     * This function validate CIDR Input.
     * CIDR Input checked by "IPv4 CIDR blocks Rule"
     * You can find more info of CIDR rule here https://st.quantrimang.com/photos/image/2019/07/25/cidr-la-gi-2.jpg
     * Example:
        Input: 192.168.0.1/12 -> False (It's correct format but it's value incorrect IPv4 CIDR block rule)
        Input: 192.168.0.0/12 -> True
     * @return
     */
    public Boolean validate(){
        boolean result = true;

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
                if(this.subnetMask[row][col] == 0){
                    /** Case Octet does not contain any elements with a value = 1 */
                    if (col == 0){
                        if(this.arrOctet[row] != 0) {
                            this.arrOctet[row] = 0;
                            result = false;
                        }
                    }
                    else {
                        /**
                         * Base on IPv4 CIDR block
                         */
                        if(this.arrOctet[row] % arrBitToInt[col-1] != 0 || this.arrOctet[row] > arrLimitValue[col-1]) {
                            this.arrOctet[row] = 0;
                            result = false;
                        }
                    }
                }
            }
        }

        return result;
    }

    public String getCorrectCidr(){
        validate();

        String cidr = this.arrOctet[0] + "." + this.arrOctet[1] + "."
                + this.arrOctet[2] + "." +this.arrOctet[3]
                + "/" + this.cidrPrefix;

        return cidr;
    }
}
