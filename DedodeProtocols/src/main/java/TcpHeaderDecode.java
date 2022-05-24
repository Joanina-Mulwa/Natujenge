public class TcpHeaderDecode {
    public static void main(String[] args) {
        byte bytes[] = new byte[]{
                (byte)0x91, 0x18, 0x00, 0x50,
                (byte)0xb9, 0x50, (byte)0xfa, 0x28,
                (byte)0xbe, (byte)0x94, 0x07, 0x1e,
                (byte)0x80, 0x18, 0x01, (byte)0xf6,
                0x01, 0x0f, 0x00, 0x00,
                0x01, 0x01, 0x08, 0x0a,
                (byte)0xb4, 0x19, 0x7a, (byte)0xe9,
                (byte)0x91, 0x1d, 0x49, (byte)0x8d
        };

        int sourcePort= 0;
        sourcePort |= (bytes[0]&0xff)<<8;
        sourcePort |= (bytes[1]&0xff);
        System.out.println("Source port is : "+ sourcePort);

        int destinationPort = 0;
        destinationPort |= (bytes[2]&0xff)<<8;
        destinationPort |=  (bytes[3]&0xff);
        System.out.println("Destination port is : "+ destinationPort);

        int sequenceNumber = 0;
        sequenceNumber |=  (bytes[4]&0xff)<<24;
        sequenceNumber |=  (bytes[5]&0xff)<<16;
        sequenceNumber |=  (bytes[6]&0xff)<<8;
        sequenceNumber |=  (bytes[7]&0xff);
        long sequenceNo = Integer.toUnsignedLong(sequenceNumber);
        System.out.println("Sequence number is : "+ sequenceNo);

        int acknowledgementNumber = 0;
        acknowledgementNumber |=  (bytes[8]&0xff) << 24;
        acknowledgementNumber |=  (bytes[9]&0xff) << 16;
        acknowledgementNumber |=  (bytes[10]&0xff) << 8;
        acknowledgementNumber |=  (bytes[11]&0xff);
        long acknowledgementNo = Integer.toUnsignedLong(acknowledgementNumber);
        System.out.println("Acknowledgment number is : "+ acknowledgementNo);

        int dataOffset = ((bytes[12]&0xff)&0b11110000)>>4;
        System.out.println("Header Length/Data offset is : " + dataOffset);
        System.out.println("Header Length/Data offset in bytes is : " + dataOffset*4);

        boolean reserved = (((bytes[12]&0xff)&0b00001111) | ((bytes[13]&0xff)&0b11000000) )> 0;
        boolean urgentURG = ((bytes[13]&0xff)&0b00100000) > 0;
        boolean acknowledgementACk = ((bytes[13]&0xff)&0b00010000) > 0;
        boolean pushPSH = ((bytes[13]&0xff)&0b00001000) > 0;
        boolean resetRST = ((bytes[13]&0xff)&0b00000100) > 0;
        boolean synSYN = ((bytes[13]&0xff)&0b00000010) > 0;
        boolean finFIN = ((bytes[13]&0xff)&0b00000001) > 0;

        System.out.println("Flag reserved if false(0) not set => " + reserved);
        System.out.println("Flag urgentURG if false(0) not set => " + urgentURG);
        System.out.println("Flag acknowledgementACk if true(1) set => " + acknowledgementACk);
        System.out.println("Flag pushPSH if true(1) set => " + pushPSH);
        System.out.println("Flag resetRST if false(0) not set => " + resetRST);
        System.out.println("Flag synSYN if false(0) not set => " + synSYN);
        System.out.println("Flag finFIN if false(0) not set => " + finFIN);

        int window = 0;
        window |=  (bytes[14]&0xff) << 8;
        window |= (bytes[15]&0xff);
        System.out.println("Window size is : "+ window);

        int checksum = 0;
        checksum |= (bytes[16]&0xff) << 8;
        checksum |= (bytes[17]&0xff);
        System.out.println("Checksum is : "+ checksum);
    }
}
