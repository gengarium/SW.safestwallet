package com.safestwallet.sw.model;

import java.lang.System;

/**
 * Stati possibili per una transazione
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/safestwallet/sw/model/TransactionStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "CONFIRMED", "FAILED", "REJECTED", "app_release"})
public enum TransactionStatus {
    /*public static final*/ PENDING /* = new PENDING() */,
    /*public static final*/ CONFIRMED /* = new CONFIRMED() */,
    /*public static final*/ FAILED /* = new FAILED() */,
    /*public static final*/ REJECTED /* = new REJECTED() */;
    
    TransactionStatus() {
    }
}