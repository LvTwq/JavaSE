package com.example.ip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/9/7 13:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespBaiduLocation {

	private String address;
	private Content content;

	/**
	 * 0 正常
	 */
	private Long status;
	private String message;



	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Content {

		@JsonProperty("address")
		private String address;

		@JsonProperty("address_detail")
		private AddressDetail addressDetail;
		private Point point;


		@Data
		@Builder
		@NoArgsConstructor
		@AllArgsConstructor
		public static class AddressDetail {
			private String adcode;
			private String city;

			@JsonProperty("city_code")
			private Long cityCode;
			private String district;
			private String province;
			private String street;

			@JsonProperty("street_number")
			private String streetNumber;

		}


		@Data
		@Builder
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Point {
			/**
			 * 经度
			 */
			@JsonProperty("x")
			private String x;
			/**
			 * 纬度
			 */
			@JsonProperty("y")
			private String y;
		}

	}


	/*示例：
		{
			"address": "CN|北京市|北京市|None|None|100|100",
			"content": {
				"address": "北京市",
				"address_detail": {
					"adcode": "110000",
					"city": "北京市",
					"city_code": 131,
					"district": "",
					"province": "北京市",
					"street": "",
					"street_number": ""
				},
				"point": {
					"x": "116.41338370",
					"y": "39.91092455"
				}
			},
			"status": 0
		}
	 */
}
