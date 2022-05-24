public class UdpHeaderDecode {
    public static void main(String[] args) {
        byte bytes[]=new byte[]{
                (byte)0xea, (byte)0xaf, 0x01, (byte)0xbb,
                0x05, 0x55, 0x58, (byte)0xbe
        };
        int sourcePort = 0;
        sourcePort |= (bytes[0]&0xff) << 8;
        sourcePort |= (bytes[1]&0xff);
        System.out.println("Source Port is : "+ sourcePort);

        int destinationPort = 0;
        destinationPort |= (bytes[2]&0xff) << 8;
        destinationPort |= (bytes[3]&0xff);
        System.out.println("Destination Port is : "+ destinationPort);

        int length = 0;
        length |= (bytes[4]&0xff) << 8;
        length |= (bytes[5]&0xff);
        System.out.println("Length is : " +length);

        int checksum = 0;
        checksum |= (bytes[6]&0xff) << 8;
        checksum |= (bytes[7]&0xff);
        System.out.println("Checksum is : "+checksum);
        System.out.println("Checksum in Hex is : "+Integer.toHexString(checksum));

    }
}
