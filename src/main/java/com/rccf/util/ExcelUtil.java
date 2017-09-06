package com.rccf.util;

import com.alibaba.fastjson.JSONArray;
import com.rccf.model.Accepted;
import org.apache.poi.hssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtil {


    private static List<String> getRibaoHeaderYewuyuan() {
        List<String> headers = new ArrayList<String>();
//        headers.add("员工编号");
        headers.add("序号");
        headers.add("部门");
        headers.add("姓名");
        headers.add("直属上级");
        headers.add("入职时间");
        headers.add("职务");
        headers.add("任务");
        headers.add("本月业绩");
        headers.add("本月受理");
        headers.add("本月办结");
        headers.add("本月被拒");
        headers.add("今日业绩");
        headers.add("今日受理");
        headers.add("今日办结");
        headers.add("今日被拒");
        return headers;
    }


    private static List<String> getRibaoHeaderFuzongjian() {
        List<String> headers = new ArrayList<String>();
        headers.add("部门");
        headers.add("姓名");
        headers.add("本月受理");
        headers.add("本月办结");
        headers.add("本月拒单");
        headers.add("本月业绩");
        headers.add("今日受理");
        headers.add("今日办结");
        headers.add("今日被拒");
        headers.add("今日业绩");
        headers.add("开单人数");
        return headers;
    }

    public static byte[] getRibaoBrand(JSONArray list, JSONArray deputyDirectorList, JSONArray directorList) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("业务员");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置表头
        List<String> excelHead = getRibaoHeaderYewuyuan();

        HSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            JSONArray array = (JSONArray) list.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCell(row, j++, i + 1);
            insertCell(row, j++, array.get(13));
            insertCell(row, j++, array.get(4));
            insertCell(row, j++, array.get(34));
            if (null != array.get(7)) {
                insertCell(row, j++, DateUtil.date2String(new Date((Long) array.get(7))));
            } else {
                insertCell(row, j++, "");
            }
//            insertCell(row, j++, array.get(7));
            insertCell(row, j++, array.get(8));

            insertCell(row, j++, "2000");
            insertCell(row, j++, array.get(31));
            insertCell(row, j++, array.get(23));
            insertCell(row, j++, array.get(25));
            insertCell(row, j++, array.get(27));
            insertCell(row, j++, array.get(29));

            insertCell(row, j++, array.get(17));
            insertCell(row, j++, array.get(19));
            insertCell(row, j++, array.get(21));


        }
        /***********************副总监表********************/
        HSSFSheet sheet1 = wb.createSheet("副总监");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row1 = sheet1.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
//        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置表头
        List<String> excelHead1 = getRibaoHeaderFuzongjian();

        HSSFCell cell1 = null;
// excel头
        for (int i = 0; i < excelHead1.size(); i++) {
            cell1 = row1.createCell(i);
            cell1.setCellValue(excelHead1.get(i));
            cell1.setCellStyle(style);
        }

        for (int i = 0; i < deputyDirectorList.size(); i++) {
            row1 = sheet1.createRow((int) i + 1);
            JSONArray array = (JSONArray) deputyDirectorList.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCell(row1, j++, array.get(13));
            insertCell(row1, j++, array.get(4));
            insertCell(row1, j++, array.get(27));
            insertCell(row1, j++, array.get(29));
            insertCell(row1, j++, array.get(31));
            insertCell(row1, j++, array.get(19));
            insertCell(row1, j++, array.get(21));
            insertCell(row1, j++, array.get(23));
            insertCell(row1, j++, array.get(25));
//            insertCell(row1, j++, array.get(28));
            insertCell(row1, j++, array.get(17));
            insertCell(row1, j++, array.get(33));
        }


        /***********************总监表********************/
        HSSFSheet sheet2 = wb.createSheet("总监");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row2 = sheet2.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
//        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置表头
        List<String> excelHead2 = getRibaoHeaderFuzongjian();

        HSSFCell cell2 = null;
// excel头
        for (int i = 0; i < excelHead2.size(); i++) {
            cell2 = row2.createCell(i);
            cell2.setCellValue(excelHead2.get(i));
            cell2.setCellStyle(style);
        }

        for (int i = 0; i < directorList.size(); i++) {
            row2 = sheet2.createRow((int) i + 1);
            JSONArray array = (JSONArray) directorList.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCell(row2, j++, array.get(13));
            insertCell(row2, j++, array.get(4));
            insertCell(row2, j++, array.get(27));
            insertCell(row2, j++, array.get(29));
            insertCell(row2, j++, array.get(31));
            insertCell(row2, j++, array.get(19));
            insertCell(row2, j++, array.get(21));
            insertCell(row2, j++, array.get(23));
            insertCell(row2, j++, array.get(25));
//            insertCell(row1, j++, array.get(28));
            insertCell(row2, j++, array.get(17));
            insertCell(row2, j++, array.get(33));
        }

        wb.write(out);
        return out.toByteArray();
    }


    /**
     * 导出受理单总表
     *
     * @param list
     * @return
     * @throws Exception
     */
    public static byte[] exportBrandPeriodSort(List<Accepted> list) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("受理单表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置表头
        List<String> excelHead = getAcceptedExcelHead();

        HSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        //List<BrandPeriodSortEntity> list = getBrandPeriodSortDynamicOrder(entity, orderType);
        Accepted accepted = null; // 拼装excel内容
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            accepted = list.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCell(row, j++, accepted.getId());
            insertCell(row, j++, accepted.getClerkName());
            insertCell(row, j++, accepted.getAcceptedNumber());
            insertCell(row, j++, accepted.getAcceptTime());
            insertCell(row, j++, accepted.getLetterNumber());
            insertCell(row, j++, accepted.getCustomerName());
            insertCell(row, j++, accepted.getCustomerPhone());
            insertCell(row, j++, accepted.getWantMoney() == null ? "" : accepted.getWantMoney());
            insertCell(row, j++, accepted.getAgency());
            insertCell(row, j++, getBusniessType(accepted.getBusinessType()));
            insertCell(row, j++, accepted.getBusinessNature());
            insertCell(row, j++, accepted.getServiceFee() != null ? accepted.getServiceFee() + "%" : "");
            insertCell(row, j++, accepted.getServiceFeeActual());
            insertCell(row, j++, accepted.getLoanMoney() == null ? "" : accepted.getLoanMoney());
            insertCell(row, j++, accepted.getEndDate());
//            insertCell(row, j++, channelInfoMapper.loadChannelNameById(accepted.getChannelId()));
            insertCell(row, j++, accepted.getServiceAgreement() == 1 ? "是" : "否");
            insertCell(row, j++, accepted.getHouqi());
            insertCell(row, j++, accepted.getBeizhu());
            insertCell(row, j++, getState(accepted.getState()));
        }
        wb.write(out);
        return out.toByteArray();
    }


    /**
     * 导出日报
     *
     * @return
     */
    public static byte[] exportRibao(List list) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("业务员报表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置表头
        List<String> excelHead = getRibaoHeaderYewuyuan();
        HSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        //List<BrandPeriodSortEntity> list = getBrandPeriodSortDynamicOrder(entity, orderType);
        // 拼装excel内容
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
//            accepted = list.get(i);
            // 创建单元格，并设置值
            int j = 0;
//            insertCell(row, j++, accepted.getId());

        }
        wb.write(out);
        return out.toByteArray();


    }


    /**
     * 业务类型
     *
     * @param type
     * @return
     */
    private static String getBusniessType(int type) {
        String t = "";
        switch (type) {
            case 0:
                t = "信贷";
                break;
            case 1:
                t = "抵押";
                break;
            case 2:
                t = "质押";
                break;
            default:
                break;
        }
        return t;
    }

    private static String getState(int state) {
        String t = "";
        switch (state) {
            case 1:
                t = "受理";
                break;
            case 2:
                t = "办结";
                break;
            case 3:
                t = "被拒";
                break;
            default:
                break;
        }
        return t;
    }

    /**
     * 获取excel表头
     *
     * @return
     */
    private static List<String> getAcceptedExcelHead() {
        List<String> result = new ArrayList<String>(17);
        result.add("受理单编号");
        result.add("员工姓名");
        result.add("受理单号");
        result.add("受理时间");
        result.add("字母编号");
        result.add("客户姓名");
        result.add("客户电话");
        result.add("欲贷金额");
        result.add("机构");
        result.add("业务类型");
        result.add("业务性质");
        result.add("服务费比例");
        result.add("实际收服务费");
        result.add("贷款金额");
        result.add("办结时间");
        result.add("是否有服务费");
        result.add("后期");
        result.add("备注");
        result.add("办理状态");


        //。。。。
        return result;
    }


    /**
     * @param row
     * @param i
     * @param object
     */
    private static void insertCell(HSSFRow row, int i, Object object) {
        if (object == null) {
            row.createCell(i).setCellValue("");
        } else {
            row.createCell(i).setCellValue(object.toString());
        }
    }

}


