public class DhcpHeaderDecode {
    public static void main(String[] args) {
        byte bytes[] = new byte[]{
                0x01, 0x01, 0x06, 0x00,
                (byte)0xb7, 0x6d, 0x16, (byte)0x9c,
                0x00, 0x01, 0x00, 0x00,
                (byte)0xc0, (byte)0xa8, 0x58, (byte)0xfd,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                (byte)0xe4, 0x70, (byte)0xb8, (byte)0xca,
                0x41, 0x35, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x63, (byte)0x82, 0x53, 0x63,
                0x35, 0x01, 0x03, 0x3d,
                0x07, 0x01, (byte)0xe4, 0x70,
                (byte)0xb8, (byte)0xca, 0x41, 0x35,
                0x37, 0x11, 0x01, 0x02,
                0x06, 0x0c, 0x0f, 0x1a,
                0x1c, 0x79, 0x03, 0x21,
                0x28, 0x29, 0x2a, 0x77,
                (byte)0xf9, (byte)0xfc, 0x11, 0x39,
                0x02, (byte)0xff, (byte)0xff, 0x0c,
                0x05, 0x43, 0x6f, 0x64,
                0x65, 0x65, (byte)0xff,
        };

        boolean messageType = ((bytes[0]&0xff)&0b11111111) > 1;
        System.out.println("Message type if false is request : "+ messageType);

        int hardwareType = (bytes[1]&0xff)&0b11111111;
        System.out.println("Hardware type is : "+ hardwareType);
        System.out.println("Hardware type in Hex is : "+ Integer.toHexString(hardwareType));

        int hardwareAddress = (bytes[2]&0xff)&0b11111111;
        System.out.println("Hardware address is : "+ hardwareAddress);

        int hops = (bytes[3]&0xff)&0b11111111;
        System.out.println("Hops are : "+ hops);

        int transactionId = 0;
        transactionId |= (bytes[4]&0xff)<<24;
        transactionId |= (bytes[5]&0xff)<<16;
        transactionId |= (bytes[6]&0xff)<<8;
        transactionId |= (bytes[7]&0xff);
        long transId = Integer.toUnsignedLong(transactionId);
        System.out.println("Transaction id is : "+transId);
        System.out.println("Transaction id in Hex is : "+ Long.toHexString(transId));

        int secondsElapsed = 0;
        secondsElapsed |= (bytes[8]&0xff)<<8;
        secondsElapsed |= (bytes[9]&0xff);
        System.out.println("Seconds elapsed are : "+ secondsElapsed);

        int flags = 0;
        flags |= (bytes[10]&0xff)<<8;
        flags |= (bytes[11]&0xff);
        System.out.println("Flags are : "+ flags);

        int part1Client = (bytes[12]&0xff)&0b11111111;
        int part2Client = (bytes[13]&0xff)&0b11111111;
        int part3Client = (bytes[14]&0xff)&0b11111111;
        int part4Client = (bytes[15]&0xff)&0b11111111;
        String clientIpAddress = part1Client+"."+part2Client+"."+part3Client+"."+part4Client;
        System.out.println("Client Ip Address is : "+clientIpAddress);

        int part1YourClient = (bytes[16]&0xff)&0b11111111;
        int part2YourClient = (bytes[17]&0xff)&0b11111111;
        int part3YourClient = (bytes[18]&0xff)&0b11111111;
        int part4YourClient = (bytes[19]&0xff)&0b11111111;
        String yourClientIpAddress = part1YourClient+"."+part2YourClient+"."+part3YourClient+"."+part4YourClient;
        System.out.println("Your Client Ip Address is : "+yourClientIpAddress);

        int part1NextServer = (bytes[20]&0xff)&0b11111111;
        int part2NextServer = (bytes[21]&0xff)&0b11111111;
        int part3NextServer = (bytes[22]&0xff)&0b11111111;
        int part4NextServer = (bytes[23]&0xff)&0b11111111;
        String nextServerIpAddress = part1NextServer+"."+part2NextServer+"."+part3NextServer+"."+part4NextServer;
        System.out.println("Next Server Ip Address is : "+nextServerIpAddress);

        int part1RelayAgent = (bytes[24]&0xff)&0b11111111;
        int part2RelayAgent = (bytes[25]&0xff)&0b11111111;
        int part3RelayAgent = (bytes[26]&0xff)&0b11111111;
        int part4RelayAgent = (bytes[27]&0xff)&0b11111111;
        String relayAgentIpAddress = part1RelayAgent+"."+part2RelayAgent+"."+part3RelayAgent+"."+part4RelayAgent;
        System.out.println("Relay Agent Ip Address is : "+relayAgentIpAddress);

        int part1clientHardwareAddress = (bytes[28]&0xff)&0b11111111;
        int part2clientHardwareAddress = (bytes[29]&0xff)&0b11111111;
        String clientHardwareAddress = part1clientHardwareAddress+"."+part2clientHardwareAddress;
        System.out.println("Client Hardware Address is : "+ clientHardwareAddress);
        String clientHardwareAddressInHex = Integer.toHexString(part1clientHardwareAddress)+"."+Integer.toHexString(part2clientHardwareAddress);
        System.out.println("Client Hardware Address in Hex is : "+ clientHardwareAddressInHex);
    }
}
