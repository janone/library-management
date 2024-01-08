package org.example.common;

import org.example.common.responsibilitychain.Chain;

import java.util.Arrays;

public class PermissionChain implements Chain {

    @Override
    public Object doFilter(Chain chain, Object ... args) throws Exception {
//        System.out.println("log chain");
        chain.doFilter(chain,args);
//        System.out.println("after");
        return null;
    }
}
