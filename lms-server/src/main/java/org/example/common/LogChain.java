package org.example.common;

import org.example.common.responsibilitychain.Chain;

import java.util.Arrays;

public class LogChain implements Chain {

    @Override
    public Object doFilter(Chain chain, Object ... args) throws Exception {
//        System.out.println("log chain");
        System.out.println(" ->  controller invoke param log "+Arrays.toString(args));
        Object result =  chain.doFilter(chain,args);
//        System.out.println("after");
        return result;
    }
}
