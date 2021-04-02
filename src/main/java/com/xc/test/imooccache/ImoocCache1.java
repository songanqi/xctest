package com.xc.test.imooccache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 最简单的缓存形式: HashMap
 */
public class ImoocCache1 {
    // 加final让指向的引用不变
    private final HashMap<String, Integer> cache = new HashMap();

    public Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get("userId");
        if (result == null) {
            result = doComputer(userId);
            cache.put("userId", result);
        }
        return result;
    }

    private Integer doComputer(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args)throws InterruptedException {
        ImoocCache1 imoocCache1 = new ImoocCache1();
        System.out.println("开始计算啦");
        Integer result = imoocCache1.computer("13");
        System.out.println("第一次计算结果" + result);
        result = imoocCache1.computer("13");
        System.out.println("第二次计算结果" + result);
    }
}
