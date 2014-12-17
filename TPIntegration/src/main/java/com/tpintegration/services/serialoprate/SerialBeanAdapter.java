package com.tpintegration.services.serialoprate;

/**
 * The adapter for realize the SerialBean
 * @author rising
 *
 */
public interface SerialBeanAdapter {
	public void sendMessage(String msg);
	public String getMessage(int length);
}
