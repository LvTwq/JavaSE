package com.example.ip;

import cn.hutool.core.net.Ipv4Util;
import com.googlecode.ipv6.IPv6Network;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/5/6 16:34
 */
@Slf4j
public class IpTest {

    @Test
    public void test01() {
        BigInteger begin = getBeginIpv6ToInt("[1001::0]/24");
        BigInteger end = getEndIpv6ToInt("[1001::0]/24");
        System.out.println(begin);
        System.out.println(end);
    }

    private BigInteger getBeginIpv6ToInt(String ipv6Str) {
        IPv6Network iPv6Addresses = IPv6Network.fromString(ipv6Str.replace("[", "").replace("]", ""));
        return iPv6Addresses.getFirst().toBigInteger();
    }

    private BigInteger getEndIpv6ToInt(String ipv6Str) {
        IPv6Network iPv6Addresses = IPv6Network.fromString(ipv6Str.replace("[", "").replace("]", ""));
        return iPv6Addresses.getLast().toBigInteger();
    }

    public Set<String> dealCsServiceIpPort(Set<String> inIpList, boolean ifWindows) {
        Set<String> resultIpList = new HashSet<>();
        for (String ip : inIpList) {
            String protocol = ip.split(";")[0];
            String realIp = ip.split(";")[1];
            String port = ip.split(";")[2];
            if (!ifWindows && realIp.contains("-")) {
                String[] ipSplit = realIp.split("-");
                String startIp = ipSplit[0];
                String endIp = ipSplit[1];
                List<String> strings = ipRange2CIDRs(startIp, endIp);
                resultIpList
                        .addAll(strings.stream().map(x -> protocol + ";" + x + ";" + port).collect(Collectors.toList()));
            }else {
                resultIpList.add(ip);
            }
        }
        return resultIpList;
    }


    @Test
    public void main() {
        Set<String> strings = Set.of("ANY;172.16.129.1-172.16.129.10;ANY", "ANY;172.16.128.21-172.168.128.23;ANY", "ANY;172.16.128.20;ANY", "ANY;172.16.128.25-172.16.128.27;ANY");
        System.out.println(dealCsServiceIpPort(strings, true));

    }

    public static String countIps(String startIp, String endIp) {
        if (startIp.equals(endIp)) {
            return startIp + "/32";
        }
        long lowestIpNum = Ipv4Util.ipv4ToLong(startIp);
        long ipNum = Ipv4Util.ipv4ToLong(endIp);
        int prefixLen = MAX_MASK_WIDTH;
        // 找到最大的掩码值
        while (prefixLen > 0 && ipNum > lowestIpNum) {
            prefixLen -=1;
            ipNum &= -(1L << MAX_MASK_WIDTH - prefixLen);
        }
        return Ipv4Util.longToIpv4(ipNum) + "/" + prefixLen;
    }

    public static List<String> ipRange2CIDRs(String start,String end){
        try {
            List<String> cidrList = new ArrayList<>();
            Long[] ipRange = new Long[]{Ipv4Util.ipv4ToLong(start), Ipv4Util.ipv4ToLong(end)};
            String cidrSpan = countIps(start, end);
            String beginIp = getBeginIpStr(cidrSpan.split("/")[0], Integer.parseInt(cidrSpan.split("/")[1]));
            long beginRange = Ipv4Util.ipv4ToLong(beginIp);
            if (beginRange < ipRange[0]) {
                long exclude = ipRange[0] - 1;
                cidrList.addAll(cidrPartition(cidrSpan, exclude)[1]);
                cidrSpan = cidrList.remove(cidrList.size() - 1);
            }
            String endIp = getEndIpStr(cidrSpan.split("/")[0], Integer.parseInt(cidrSpan.split("/")[1]));
            long endRange = Ipv4Util.ipv4ToLong(endIp);
            if (endRange > ipRange[1]) {
                long exclude = ipRange[1] + 1;
                cidrList.addAll(cidrPartition(cidrSpan, exclude)[0]);
            } else {
                cidrList.add(cidrSpan);
            }
            return cidrList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static String getEndIpStr(String ip, int mask) {
        if (mask == 0) {
            return "255.255.255.255";
        }
        return Ipv4Util.getEndIpStr(ip, mask);
    }

    private static String getBeginIpStr(String ip, int mask) {
        if (mask == 0) {
            return ip;
        }
        return Ipv4Util.getBeginIpStr(ip, mask);
    }

    private static List<String>[] cidrPartition(String target, long exclude) {
        String ip = target.split("/")[0];
        int mask = Integer.parseInt(target.split("/")[1]);
        long start = Ipv4Util.ipv4ToLong(getBeginIpStr(ip, mask));
        long end = Ipv4Util.ipv4ToLong(getEndIpStr(ip, mask));
        if (exclude < start) {
            return new List[]{new ArrayList(), Arrays.asList(ip + "/" + mask)};
        } else if (end < exclude) {
            return new List[]{Arrays.asList(ip + "/" + mask), new ArrayList()};
        }
        if (mask >= MAX_MASK_WIDTH) {
            return new List[]{new ArrayList(), new ArrayList()};
        }

        //
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        //
        int newMask = mask + 1;
        long lower = start;
        long upper = (long) (start + Math.pow(2, (MAX_MASK_WIDTH - newMask)));
        while (32 >= newMask) {
            long matched = 0;
            if (exclude >= upper) {
                left.add(Ipv4Util.longToIpv4(lower) + "/" + newMask);
                matched = upper;
            } else {
                right.add(Ipv4Util.longToIpv4(upper) + "/" + newMask);
                matched = lower;
            }
            newMask++;
            if (newMask > MAX_MASK_WIDTH) {
                break;
            }
            lower = matched;
            upper = (long) (matched + (Math.pow(2, (MAX_MASK_WIDTH - newMask))));
        }
        Collections.reverse(right);
        return new List[]{left, right};
    }

    private static String rexp = "^(((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9]).){1}((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d).){2})((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)/(?:[1-9]|[12][0-9]|3[012])){1}$";
    private static Pattern pat = Pattern.compile(rexp);
    private static final int MAX_MASK_WIDTH=32;

}
