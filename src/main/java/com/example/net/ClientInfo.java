package com.example.net;

import lombok.Builder;
import lombok.Data;

/**
 * @author 吕茂陈
 * @date 2022-08-30 20:07
 */
@Data
@Builder
public class ClientInfo {


	/**
	 * 该客户端适用的系统
	 */
	private String os;

	/**
	 * 系统支持 32/64 位
	 */
	private String bit;

	/**
	 * 下载链接
	 */
	private String downloadUrl;
}
