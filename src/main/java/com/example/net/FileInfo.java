package com.example.net;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 吕茂陈
 * @date 2022-08-30 11:48
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {


	/**
	 * tb_pkg_version_info 的id
	 */
	private String id;

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
	 * todo 先遍历os，再遍历bit，拼接url，他们的url都是一样的
	 */
	private String downloadUrl;


	/**
	 * 类型：enclient/entools/endoc
	 */
	private String centerType;
	/**
	 * 版本
	 */
	private String version;

	private String name;

}
