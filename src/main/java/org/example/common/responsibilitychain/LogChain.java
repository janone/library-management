package org.example.common.responsibilitychain;

import java.util.Arrays;

public class LogChain implements Chain{

    @Override
    public Object doFilter(Chain chain, Object ... args) throws Exception {
//        System.out.println("log chain");
        System.out.println(" ->  controller invoke param log "+Arrays.toString(args));
        chain.doFilter(chain,args);
//        System.out.println("after");
        return null;
    }
}
