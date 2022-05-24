public class DiameterHeaderDecode {
    public static void main(String[] args) {
        byte bytes = 0x40;

        boolean request = (((bytes)&0xff)&0b10000000) > 0;
        System.out.println("Request Flag : "+ request);

        boolean proxiyable = (((bytes)&0xff)&0b01000000) > 0;
        System.out.println("Proxiyable Flag : "+ proxiyable);

        boolean error = (((bytes)&0xff)&0b00100000) > 0;
        System.out.println("Error Flag : "+ error);

    }
}