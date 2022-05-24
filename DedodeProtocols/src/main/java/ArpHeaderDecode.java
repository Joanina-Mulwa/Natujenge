public class ArpHeaderDecode {
    public static void main(String[] args) {
        byte bytes[]=new byte[]{
                0x00, 0x01, 0x08, 0x00,
                0x06, 0x04, 0x00, 0x01,
                (byte)0xdc, 0x2c, 0x6e, 0x06,
                0x0b, (byte)0xda, (byte)0xc0, (byte)0xa8,
                0x58, 0x01, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                (byte)0xc0, (byte)0xa8, 0x58, (byte)0xfd
        };
        int hardwareType =0;
        hardwareType |= (bytes[0]&0xff)<<8;
        hardwareType |= (bytes[1]&0xff);
        System.out.println("Hardware Type is : "+ hardwareType);

        int protocolType =0;
        protocolType |= (bytes[2]&0xff)<<8;
        protocolType |= (bytes[3]&0xff);
        System.out.println("Protocol Type is : "+ protocolType);
        System.out.println("Protocol Type in Hex is : "+ Integer.toHexString(protocolType));

        int hardwareSize = (bytes[4]&0xff)&0b11111111;
        System.out.println("Hardware Size is : "+ hardwareSize);

        int protocolSize = (bytes[5]&0xff)&0b11111111;
        System.out.println("Protocol Size is : "+ protocolSize);

        int operationCode =0;
        operationCode |= (bytes[6]&0xff)<<8;
        operationCode |= (bytes[7]&0xff);
        System.out.println("Operation Code is : "+ operationCode);

        int part1SenderMacAddress = (bytes[8]&0xff)&0b11111111;
        int part2SenderMacAddress = (bytes[9]&0xff)&0b11111111;
        int part3SenderMacAddress = (bytes[10]&0xff)&0b11111111;
        int part4SenderMacAddress = (bytes[11]&0xff)&0b11111111;
        int part5SenderMacAddress = (bytes[12]&0xff)&0b11111111;
        int part6SenderMacAddress = (bytes[13]&0xff)&0b11111111;
        String senderMacAddress = part1SenderMacAddress+"."+part2SenderMacAddress+"."+part3SenderMacAddress+"."+part4SenderMacAddress+"."+part5SenderMacAddress+"."+part6SenderMacAddress;
        System.out.println("Sender Mac Address is : "+senderMacAddress);

        int part1SenderIpAddress = (bytes[14]&0xff)&0b11111111;
        int part2SenderIpAddress = (bytes[15]&0xff)&0b11111111;
        int part3SenderIpAddress = (bytes[16]&0xff)&0b11111111;
        int part4SenderIpAddress = (bytes[17]&0xff)&0b11111111;
        String senderIpAddress = part1SenderIpAddress+"."+part2SenderIpAddress+"."+part3SenderIpAddress+"."+part4SenderIpAddress;
        System.out.println("Sender Ip Address is : "+senderIpAddress);

        int part1TargetMacAddress = (bytes[18]&0xff)&0b11111111;
        int part2TargetMacAddress = (bytes[19]&0xff)&0b11111111;
        int part3TargetMacAddress = (bytes[20]&0xff)&0b11111111;
        int part4TargetMacAddress = (bytes[21]&0xff)&0b11111111;
        int part5TargetMacAddress = (bytes[22]&0xff)&0b11111111;
        int part6TargetMacAddress = (bytes[23]&0xff)&0b11111111;
        String targetMacAddress = part1TargetMacAddress+"."+part2TargetMacAddress+"."+part3TargetMacAddress+"."+part4TargetMacAddress+"."+part5TargetMacAddress+"."+part6TargetMacAddress;
        System.out.println("Target Mac Address is : "+targetMacAddress);

        int part1TargetIpAddress = (bytes[24]&0xff)&0b11111111;
        int part2TargetIpAddress = (bytes[25]&0xff)&0b11111111;
        int part3TargetIpAddress = (bytes[26]&0xff)&0b11111111;
        int part4TargetIpAddress = (bytes[27]&0xff)&0b11111111;
        String targetIpAddress = part1TargetIpAddress+"."+part2TargetIpAddress+"."+part3TargetIpAddress+"."+part4TargetIpAddress;
        System.out.println("Target Ip Address is : "+targetIpAddress);

    }
}
