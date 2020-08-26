package com.zhangna.tidb.binlog.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangna
 * @Date 2019-12-06 16:14
 * @Description
 */
@Slf4j
public enum TableEnums {

	SUBSCRIBE_SOURCE("t_user_info","订阅来源表"),

	;

	@Getter
	private String tableName;

	@Getter
	private String tableDescription;

	TableEnums(String tableName, String tableDescription){
		this.tableName = tableName;
		this.tableDescription = tableDescription;
	}

	public static  List<String> getTableNames(){
		List<String> tableNames = new ArrayList<>();
		for(TableEnums tableEnums : TableEnums.values()){
			tableNames.add(tableEnums.getTableName());
		}

		return tableNames;
	}



//
//
//	public static List<String> getTableNames(String tableName){
//		List<String> tables = new ArrayList<>();
//
//		try{
//			Class< ? > clazz = Class.forName(tableName);
//			Object[] objects = clazz.getEnumConstants();
//
//			Method getTableName = clazz.getMethod("tableName");
//
//			for(Object object:objects){
//				tables.add(String.valueOf(getTableName.invoke(object)));
//			}
//
//		}catch (Exception e){
//
//            log.error("获取枚举属性失败");
//		}
//
//		return tables;
//
//	}
//

}
