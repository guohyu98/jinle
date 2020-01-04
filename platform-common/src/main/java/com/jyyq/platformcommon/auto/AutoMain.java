package com.jyyq.platformcommon.auto;

import com.jyyq.platformcommon.auto.utils.*;
import com.jyyq.platformcommon.common.ShowVarUtil;

import java.util.List;

public class AutoMain {
//    public static void main(String[] args) {
//        String[] classNames = new String[]{"HotelFacility", "HotelRestaurant", "HotelRestaurantCombo", "hotelRoomType", "hotelRoomTypeStatus", "HotelShowImg"};
//        String[] tableNames = new String[]{"hotel_facility", "hotel_restaurant", "hotel_restaurant_combo", "hotel_room_type", "hotel_room_type_status", "hotel_show_img"};
//        for(int i=0;i<classNames.length;i++){
//            String className = AutoUtils.upperFirst(classNames[i]);
//            List<AutoBean> beanList = AutoDbUtils.getListFiled(tableNames[i]);
////            ShowVarUtil.showJsonList("beanList", beanList);
//
//            exportDao(className, tableNames[i], beanList);
//            exportMapper(className, tableNames[i], beanList);
//            exportService(className, beanList);
////			exportPcController(className, beanList);
//        }
//    }

    public static void exportDao(String className, String tableName, List<AutoBean> beanList){
        AutoDaoUtils.exportDao(className, tableName, beanList);
    }

    public static void exportMapper(String className, String tableName, List<AutoBean> beanList){
        AutoMapperUtils.exportMapper(className, tableName, beanList);
    }
    public static void exportService(String className, List<AutoBean> beanList){
        AutoServiceUtils.exportService(className, beanList);
    }
    public static void exportPcController(String className, List<AutoBean> beanList){

        AutoControllerUtils.exportController(className, beanList);
    }
}
