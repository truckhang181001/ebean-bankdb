import services.CIDR.CidrSingleton;

public class testCIDR {
    public static void main(String[] args){
        System.out.println(CidrSingleton.getInstance().correctCidr("192.10.0.0/8"));
    }
}
