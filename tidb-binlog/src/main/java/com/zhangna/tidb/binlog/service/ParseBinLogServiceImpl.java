package com.zhangna.tidb.binlog.service;

import com.google.common.collect.Maps;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zhangna.tidb.binlog.enums.TableEnums;
import com.zhangna.tidb.binlog.proto.BinLogInfo;
import com.zhangna.tidb.binlog.util.BaseException;
import com.zhangna.tidb.binlog.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author zhangna
 * @Date 2019-12-03 20:30
 * @Description
 */
@Service
@Slf4j
public class ParseBinLogServiceImpl implements ParseBinLogService {

	@Override
	public List<Map< String, Object >> parseBinLog2Data(byte[] record) throws BaseException {

		return ServiceUtil.execute(() -> parseTable(record),
				throwable -> log.error("parse binlog is failed.;t:{}",throwable));

	}


	private List<Map< String, Object >> parseTable(byte[] record){

		if(Objects.isNull(record)){
			return null;
		}


		BinLogInfo.Binlog binlog = null;

		try {
			binlog = BinLogInfo.Binlog.parseFrom(record);
		} catch (InvalidProtocolBufferException e) {
			throw new BaseException("parse binlog is failed");
		}
		if(Objects.isNull(binlog)){
			return null;
		}
		BinLogInfo.DMLData dmlData = binlog.getDmlData();
		if(Objects.isNull(dmlData)){
			return null;
		}

		List< BinLogInfo.Table > tablesList = dmlData.getTablesList();

		List< String > tableNames = TableEnums.getTableNames();

		List<Map< String, Object >> rs= new ArrayList<>();
		for( BinLogInfo.Table table : tablesList){
			String tableName = table.getTableName();
			log.info("tableName:{}",tableName);
			if(!tableNames.contains(tableName)){
				continue;
			}

			List< Map< String, Object > > result = this.getResult(table);
			rs.addAll(result);
		}

		return rs;

	}

	private List<String> parseColumn(BinLogInfo.Table table){

		List< BinLogInfo.ColumnInfo > columnInfoList = table.getColumnInfoList();
		if(CollectionUtils.isEmpty(columnInfoList)){
			return Collections.EMPTY_LIST;
		}
		List<String> columnKeys = new ArrayList<>();
		columnInfoList.forEach(columnInfo -> {
			columnKeys.add(columnInfo.getName());
		});

		return columnKeys;

	}

	private List< BinLogInfo.TableMutation > getTableMutation(BinLogInfo.Table table){

		if(Objects.isNull(table)){
			return null;
		}
		return table.getMutationsList();
	}

	private List<List<Object>> parseColumnValue(BinLogInfo.Table table) {

		List<List<Object>> rs = new ArrayList<>();

		List< BinLogInfo.TableMutation > tableMutations = getTableMutation(table);
		if (CollectionUtils.isEmpty(tableMutations)) {
			return Collections.EMPTY_LIST;
		}

		for (BinLogInfo.TableMutation tableMutation : tableMutations) {

			BinLogInfo.Row rowList = tableMutation.getRow();
			if (Objects.isNull(rowList)) {
				continue;
			}


			List< BinLogInfo.Column > columnsList = rowList.getColumnsList();
			if (CollectionUtils.isEmpty(columnsList)) {
				continue;
			}

			List< Object > columnValues = new ArrayList<>();
			columnsList.forEach(column -> {
				String stringValue = column.getStringValue();
				long int64Value = column.getInt64Value();
				if (int64Value != 0) {
					columnValues.add(int64Value);
				}
				if (null != stringValue && int64Value == 0) {
					columnValues.add(stringValue);
				}
			});

			rs.add(columnValues);

		}
		return rs;
	}


	private String getOperatorType(BinLogInfo.Table table){
		List< BinLogInfo.TableMutation > tableMutations = getTableMutation(table);

		if (CollectionUtils.isEmpty(tableMutations)){
			return null;
		}

		BinLogInfo.TableMutation tableMutation = tableMutations.get(0);
		BinLogInfo.MutationType type = tableMutation.getType();
		if (Objects.isNull(type)){
			return null;
		}

		return type.toString();
	}

	private List<Map< String, Object >> getResult(BinLogInfo.Table table) {

		List<Map< String, Object >> rs = new ArrayList<>();

		List< String > columnKeys = parseColumn(table);
		List< List< Object > > columnValues = parseColumnValue(table);


		if (CollectionUtils.isEmpty(columnKeys) || CollectionUtils.isEmpty(columnValues) ) {
			return null;
		}

		Map< Integer, String > tempFeildMap = Maps.newHashMapWithExpectedSize(columnKeys.size());

		String operatorType = getOperatorType(table);

		for (String columnKey : columnKeys) {
			tempFeildMap.put(columnKeys.indexOf(columnKey), columnKey);
		}

		Map< String, Object > resultMap = new HashMap<>();

		for(List< Object > objects : columnValues){
			for(int i= 0; i < objects.size(); i++ ){
				resultMap.put(tempFeildMap.get(i), objects.get(i));
			}
			resultMap.put("operatorType",operatorType);
			rs.add(resultMap);

		}

		return rs;
	}



}
