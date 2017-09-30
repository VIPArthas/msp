package com.wh.util;

import java.math.BigInteger;
import java.util.Random;

/**
 * 生成随机ID
 *
 * @作者dong
 * @创建日期 2014-3-5 下午5:41:46
 * @版本 V 1.0
 */
public class UUIDGenerator
{

    private static final BigInteger countStart = new BigInteger("-12219292800000");
    private static final int clock_sequence = (new Random()).nextInt(16384);
    private static BigInteger lastCount = null;

    
    public static void main (String [] args){
    	System.out.println(UUIDGenerator.getUUID());
    }
    
    public UUIDGenerator()
    {
    }
    @SuppressWarnings("unused")
	private void destroy()
    {
    }
    public static String getUUID()
    {
        UUIDGenerator uuidGen = new UUIDGenerator();
        return uuidGen.nextUUID();
    }
    @SuppressWarnings("unused")
	private void init()
    {
    }
    private String nextUUID()
    {
        BigInteger current = BigInteger.valueOf(System.currentTimeMillis());
        synchronized(UUIDGenerator.class)
        {
            if(lastCount != null && lastCount.compareTo(current) == -1)
                current = lastCount.add(BigInteger.valueOf(1L));
            lastCount = current;
        }
        BigInteger countMillis = current.subtract(countStart);
        BigInteger count = countMillis.multiply(BigInteger.valueOf(10000L));
        String bitString = count.toString(2);
        if(bitString.length() < 60)
        {
            int nbExtraZeros = 60 - bitString.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            bitString = extraZeros.concat(bitString);
        }
        byte bits[] = bitString.getBytes();
        byte time_low[] = new byte[32];
        for(int i = 0; i < 32; i++)
            time_low[i] = bits[bits.length - i - 1];

        byte time_mid[] = new byte[16];
        for(int i = 0; i < 16; i++)
            time_mid[i] = bits[bits.length - 32 - i - 1];

        byte time_hi_and_version[] = new byte[16];
        for(int i = 0; i < 12; i++)
            time_hi_and_version[i] = bits[bits.length - 48 - i - 1];

        time_hi_and_version[12] = (new String("1")).getBytes()[0];
        time_hi_and_version[13] = (new String("0")).getBytes()[0];
        time_hi_and_version[14] = (new String("0")).getBytes()[0];
        time_hi_and_version[15] = (new String("0")).getBytes()[0];
        BigInteger clockSequence = BigInteger.valueOf(clock_sequence);
        String clockString = clockSequence.toString(2);
        if(clockString.length() < 14)
        {
            int nbExtraZeros = 14 - bitString.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            clockString = extraZeros.concat(bitString);
        }
        byte clock_bits[] = clockString.getBytes();
        byte clock_seq_low[] = new byte[8];
        for(int i = 0; i < 8; i++)
            clock_seq_low[i] = clock_bits[clock_bits.length - i - 1];

        byte clock_seq_hi_and_reserved[] = new byte[8];
        for(int i = 0; i < 6; i++)
            clock_seq_hi_and_reserved[i] = clock_bits[clock_bits.length - 8 - i - 1];

        clock_seq_hi_and_reserved[6] = (new String("0")).getBytes()[0];
        clock_seq_hi_and_reserved[7] = (new String("1")).getBytes()[0];
        String timeLow = Long.toHexString((new BigInteger(new String(reverseArray(time_low)), 2)).longValue());
        if(timeLow.length() < 8)
        {
            int nbExtraZeros = 8 - timeLow.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            timeLow = extraZeros.concat(timeLow);
        }
        String timeMid = Long.toHexString((new BigInteger(new String(reverseArray(time_mid)), 2)).longValue());
        if(timeMid.length() < 4)
        {
            int nbExtraZeros = 4 - timeMid.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            timeMid = extraZeros.concat(timeMid);
        }
        String timeHiAndVersion = Long.toHexString((new BigInteger(new String(reverseArray(time_hi_and_version)), 2)).longValue());
        if(timeHiAndVersion.length() < 4)
        {
            int nbExtraZeros = 4 - timeHiAndVersion.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            timeHiAndVersion = extraZeros.concat(timeHiAndVersion);
        }
        String clockSeqHiAndReserved = Long.toHexString((new BigInteger(new String(reverseArray(clock_seq_hi_and_reserved)), 2)).longValue());
        if(clockSeqHiAndReserved.length() < 2)
        {
            int nbExtraZeros = 2 - clockSeqHiAndReserved.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            clockSeqHiAndReserved = extraZeros.concat(clockSeqHiAndReserved);
        }
        String clockSeqLow = Long.toHexString((new BigInteger(new String(reverseArray(clock_seq_low)), 2)).longValue());
        if(clockSeqLow.length() < 2)
        {
            int nbExtraZeros = 2 - clockSeqLow.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            clockSeqLow = extraZeros.concat(clockSeqLow);
        }
        Random secureRandom = null;
        try
        {
            secureRandom = new Random();
        }
        catch(Exception e)
        {
            secureRandom = new Random();
        }
        long nodeValue = secureRandom.nextLong();
        for(nodeValue = Math.abs(nodeValue); nodeValue > 0x800000000000L; nodeValue = Math.abs(nodeValue))
            nodeValue = secureRandom.nextLong();

        BigInteger nodeInt = BigInteger.valueOf(nodeValue);
        String nodeString = nodeInt.toString(2);
        if(nodeString.length() < 47)
        {
            int nbExtraZeros = 47 - nodeString.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            nodeString = extraZeros.concat(nodeString);
        }
        byte node_bits[] = nodeString.getBytes();
        byte node[] = new byte[48];
        for(int i = 0; i < 47; i++)
            node[i] = node_bits[node_bits.length - i - 1];

        node[47] = (new String("1")).getBytes()[0];
        String theNode = Long.toHexString((new BigInteger(new String(reverseArray(node)), 2)).longValue());
        if(theNode.length() < 12)
        {
            int nbExtraZeros = 12 - theNode.length();
            String extraZeros = new String();
            for(int i = 0; i < nbExtraZeros; i++)
                extraZeros = extraZeros.concat("0");

            theNode = extraZeros.concat(theNode);
        }
        String result = timeLow + timeMid + timeHiAndVersion + clockSeqHiAndReserved + clockSeqLow + theNode;
        return result.toUpperCase();
    }
    private static byte[] reverseArray(byte bits[])
    {
        byte result[] = new byte[bits.length];
        for(int i = 0; i < result.length; i++)
            result[i] = bits[result.length - 1 - i];

        return result;
    }

}
