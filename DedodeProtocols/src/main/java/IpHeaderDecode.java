public class IpHeaderDecode {
    public static void main(String[] args) {
        byte bytes[] = new byte[]
                {       0x45, 0x00, 0x01, (byte) 0x96,
                        (byte)0xb7, 0x70, 0x40, 0x00,
                        0x40, 0x06, 0x61, (byte)0x94,
                        (byte)0xc0, (byte)0xa8, 0x58, (byte)0xfd,
                        0x33, 0x0f, (byte)0xd3, (byte)0xa8
                };

        //0x45 => 0100 0101
        int version = (bytes[0]&0xff) >> 4;
        System.out.println("Version is : " + version);

        //OR
        int versiona = ((bytes[0]&0xff) & 0b11110000) >>4;
        System.out.println("Version is : " + versiona);

        int IhlNoOfWords = (bytes[0]&0xff) & 0b00001111;
        System.out.println("IHL is "+ IhlNoOfWords);
        System.out.println("IHL is bytes "+ IhlNoOfWords * 4);

        int totalLength = 0;
        //b1 = 0x01 => 00000001
        //b2 = 0x96 => 10010110
        totalLength = (totalLength | (bytes[2]&0xff)) << 8;
        totalLength = totalLength | (bytes[3]&0xff);
        System.out.println("Total Length is " + totalLength);

        boolean reserved = ((bytes[6]&0xff) & 0b10000000) > 0;
        boolean doNotFragment = ((bytes[6]&0xff)& 0b01000000) > 0;
        boolean moreFragment = ((bytes[6]&0xff)& 0b00100000) > 0;
        System.out.println("Flag reserved if false(0) not set =>  " + reserved);
        System.out.println("Flag doNotFragment if true(1) set =>  " + doNotFragment);
        System.out.println("Flag moreFragment if false(0) not set =>  " + moreFragment);

        int fragmentOffset = 0;
        //b1 = 0x40 => 01000000
        //in b1 we don't need the first 3 bits(flag bits)
        //b2 = 0x00 => 00000000

        fragmentOffset = (fragmentOffset | ((bytes[6]&0xff)&0b00011111)) << 8;
        fragmentOffset = fragmentOffset | (bytes[7]&0xff);

        System.out.println("Fragment offset is "+ fragmentOffset);

        int timeToLive =(bytes[8]&0xff)&0b11111111;
        System.out.println("Time to Live is "+ timeToLive);

        int protocol = (bytes[9]&0xff)&0b11111111;
        System.out.println("Protocol is "+ protocol);

        int headerChecksum=0;
        //b1 = 0x61 => 01100001
        //b2 = 0x94 => 10010100
        headerChecksum = (headerChecksum | (bytes[10]&0xff)) << 8;
        headerChecksum = (headerChecksum | (bytes[11]&0xff));
        System.out.println("Header Checksum is "+ headerChecksum);


        // part1 = 0xc0 => 11000000
        // part2 = 0xa8 => 10101000
        // part3 = 0x58 => 01011000
        // part4 = 0xfd => 11111101
        //it is bigger than 127 hence the negative number
        int part1 = bytes[12];
        int part2 = bytes[13];
        int part3 = bytes[14];
        int part4 = bytes[15];
        String sourceIp = part1 + "." + part2 + "." + part3 + "." + part4;
        System.out.println("Signed source IP address is " + sourceIp);

        int partA = (bytes[12]&0xff)&0b11111111;
        int partB = (bytes[13]&0xff)&0b11111111;
        int partC = (bytes[14]&0xff)&0b11111111;
        int partD = (bytes[15]&0xff)&0b11111111;
        String sourceIpA = partA + "." + partB + "." + partC + "." + partD;
        System.out.println("Source Ip address is : " +sourceIpA);

        // part1 = 0x33 => 00110011
        // part2 = 0x0f => 00001111
        // part3 = 0xd3 => 11010011
        // part4 = 0xa8 => 10101000
        //it is bigger than 127 hence the negative number
        int part1D = bytes[16];
        int part2D = bytes[17];
        int part3D = bytes[18];
        int part4D = bytes[19];
        String destinationIp = part1D + "." + part2D + "." + part3D + "." + part4D;
        System.out.println("Signed destination IP address is " + sourceIp);

        int partAD = (bytes[16]&0xff)&0b11111111;
        int partBD = (bytes[17]&0xff)&0b11111111;
        int partCD = (bytes[18]&0xff)&0b11111111;
        int partDD = (bytes[19]&0xff)&0b11111111;
        String destinationIpD = partAD + "." + partBD + "." + partCD + "." + partDD;
        System.out.println("Destination Ip Address is : " + destinationIpD);

        byte bytes2[] = new byte[]{
                0x45, 0x00, 0x00, 0x35,
                0x2e, 0x34, 0x40, 0x00,
                0x40, 0x06, 0x0e, (byte)0x8d,
                0x7f, 0x00, 0x00, 0x01,
                0x7f, 0x00, 0x00, 0x01
        };

        int version2 = ((bytes2[0]&0xff)&0b11110000)>>4;
        System.out.println("Version is : "+ version2);

        int headerLength = (bytes2[0]&0xff)&0b00001111;
        System.out.println("IHL in words is : " + headerLength);
        System.out.println("IHL in bytes is : "+headerLength*4);

        int typeOfService = (bytes2[1]&0xff);
        System.out.println("Type of service is : "+typeOfService);

        int length=0 ;
        length |= (bytes2[2]&0xff)<<8;
        length |= (bytes2[3]&0xff);
        System.out.println("Total Length is : "+length);

        int identification =0;
        identification |= (bytes2[4]&0xff)<<8;
        identification |= (bytes2[5]&0xff);
        System.out.println("Identification is : "+identification);
        System.out.println("Identification in Hex is : "+ Integer.toHexString(identification));

        boolean reserved2 = ((bytes2[6]&0xff)&0b10000000)>0;
        boolean doNotFragment2 = ((bytes2[6]&0xff)&0b01000000)>0;
        boolean moreFragment2 = ((bytes2[6]&0xff)&0b00100000)>0;
        System.out.println("Reserved bit is : "+ reserved2);
        System.out.println("Dont fragment bit is : "+ doNotFragment2);
        System.out.println("More fragment bit is : "+moreFragment2);

        int fragmentOffset2=0;
        fragmentOffset2 |= ((bytes2[6]&0xff)&0b00011111) <<8;
        fragmentOffset2 |= (bytes2[7]&0xff);
        System.out.println("FragmentOffset is : "+fragmentOffset2);

        int ttl = bytes2[8]&0xff;
        System.out.println("Time to live is "+ ttl);

        int protocol2 = bytes2[9]&0xff;
        System.out.println("Protocol is : "+protocol2);

        int checksum = 0;
        checksum |= (bytes2[10]&0xff) <<8;
        checksum |= (bytes2[11]&0xff);
        System.out.println("Header Checksum is : "+ checksum);

        int part1S = bytes2[12]&0xff;
        int part2S = bytes2[13]&0xff;
        int part3S = bytes2[14]&0xff;
        int part4S = bytes2[15]&0xff;
        String sourceAdd = part1S+ "."+part2S+"."+part3S+"."+part4S;
        System.out.println("Source Adddress is : "+sourceAdd);

        int part1DA = bytes2[16]&0xff;
        int part2DA = bytes2[17]&0xff;
        int part3DA = bytes2[18]&0xff;
        int part4DA = bytes2[19]&0xff;
        String destinationAdd = part1DA+ "."+part2DA+"."+part3DA+"."+part4DA;
        System.out.println("Destination Adddress is : "+destinationAdd);
    }
}
