package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.ConfigBedType;
import com.jyyq.platformweb.dao.ConfigShowInfo;

import java.util.List;

public interface ConfigService {

    List<ConfigBedType> selectListConfigBedType();
    List<ConfigShowInfo> selectListConfigShowInfo();

}
