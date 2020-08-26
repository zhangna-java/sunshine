package com.springboot.cloud.entity;

import lombok.Data;

/**
 * @Author zhangna
 * @Date 2020-03-11 10:38
 * @Description
 */
@Data
public class Message {
	private long userId;
	private long orderId;
	private long subOrderId;
	private long siteId;
	private String siteName;
	private long cityId;
	private String cityName;
	private long warehouseId;
	private long merchandiseId;
	private long price;
	private long quantity;
	private int orderStatus;
	private int isNewOrder;
	private long timestamp;

}
