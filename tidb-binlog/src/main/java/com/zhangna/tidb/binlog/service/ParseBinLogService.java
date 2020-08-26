package com.zhangna.tidb.binlog.service;

import com.zhangna.tidb.binlog.util.BaseException;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangna
 * @Date 2019-12-03 20:30
 * @Description
 */
public interface ParseBinLogService {

	List<Map< String, Object >> parseBinLog2Data(byte[] record) throws BaseException;

}
