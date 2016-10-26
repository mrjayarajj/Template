package com.baseframework.biz.util;

import static com.baseframework.biz.util.TemplateConstants.HTTP_CLIENT_IP;
import static com.baseframework.biz.util.TemplateConstants.HTTP_X_FORWARDED_FOR;
import static com.baseframework.biz.util.TemplateConstants.PROXY_CLIENT_IP;
import static com.baseframework.biz.util.TemplateConstants.UNKNOWN;
import static com.baseframework.biz.util.TemplateConstants.WL_PROXY_CLIENT_PL;
import static com.baseframework.biz.util.TemplateConstants.X_USER_ADDR;
import static com.baseframework.biz.util.TemplateConstants.X_FORWARDED_FOR;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class TemplateUtil {

	public static String getClientIpAddr(HttpServletRequest request) {

		String ip = request.getHeader(X_FORWARDED_FOR);
		if (ip == null || ip.trim().length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(PROXY_CLIENT_IP);

		}
		if (ip == null || ip.trim().length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(WL_PROXY_CLIENT_PL);

		}
		if (ip == null || ip.trim().length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(HTTP_CLIENT_IP);

		}
		if (ip == null || ip.trim().length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(HTTP_X_FORWARDED_FOR);

		}
		if (ip == null || ip.trim().length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(X_USER_ADDR);
		}
		if (ip == null || ip.trim().length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {

			try {
				/**
				 * For the Development,IP address will always be local, We will
				 * not be able to authenticate with DS URL.
				 * 
				 * Hence Small code snippet is added so that IP address will be
				 * fetched from Network interface and injected in the URL. If
				 * the request comes from Different machine,IP address will be
				 * picked from the request.
				 * 
				 */
				if (request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")) {
					ip = getIpAddressByDoingLoopUpFromNetworkInterface();
				} else {
					ip = request.getRemoteAddr();
				}

			} catch (SocketException e) {
				throw new RuntimeException(
						"Exception occured while getting the IP Address from Network Interface Look up", e);
			}
		}
		return ip;
	}

	/**
	 * Gets the ip address by doing loop up from network interface.
	 *
	 * @return the ip address by doing loop up from network interface
	 * @throws SocketException
	 *             the socket exception
	 */
	public static String getIpAddressByDoingLoopUpFromNetworkInterface() throws SocketException {

		/**
		 * There can be multiple IP Addresses associated with the Client
		 * Machine.
		 * 
		 * We are doing a Loop up through the Network interface and getting the
		 * IP address of VPN network.
		 * 
		 */
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		String networkInterfaceName = "";
		String ipAddress = "";
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface iface = networkInterfaces.nextElement();
			networkInterfaceName = iface.getName();
			Enumeration<InetAddress> addresses = iface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress addr = addresses.nextElement();
				if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
					ipAddress = addr.getHostAddress();
					if (ipAddress.startsWith("17"))
						return ipAddress;
				}
			}
		}
		return ipAddress;
	}

}
