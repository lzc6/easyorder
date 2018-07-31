package zqeasyorder.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.*;

import zqeasyorder.domain.ResultMap;
import zqeasyorder.util.BuildXml;
import zqeasyorder.util.HttpClient;
import zqeasyorder.util.IDCard;

import javax.servlet.http.HttpSession;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Component
@RestController
@RequestMapping(value = "/webservice")
public class PolicynoController {
    @Autowired
    @Qualifier("easyorder")
    protected JdbcTemplate easyorder;

    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
    @PostMapping(value = "/insure")
    public ResultMap insure(HttpSession session,
                            @RequestParam(required = true) String pipeCode,
                            @RequestParam(required = true) String md5EncryptedValue,
                            @RequestParam(required = true) String businessUnitCode,
                            @RequestParam(required = true) String riskCode,
                            @RequestParam(required = true) String rationType,
                            @RequestParam(required = true) String premium,
                            @RequestParam(required = true) String amount,
                            @RequestParam(required = true) String operateTime,
                            @RequestParam(required = true) String startDate,
                            @RequestParam(required = true) String endDate,
                            @RequestParam(required = true) String startHour,
                            @RequestParam(required = true) String endHour,
                            @RequestParam(required = true) String unit,
                            @RequestParam(required = true) String insureCount,
                            @RequestParam(required = true) String sumPremium,
                            @RequestParam(required = true) String sumAmount,
                            @RequestParam(required = true) String insurename,
                            @RequestParam(required = true) String insureidType,
                            @RequestParam(required = true) String insureidNumber,
                            @RequestParam(required = true) String insuregender,
                            @RequestParam(required = true) String insurebirthDay,
                            @RequestParam(required = true) String insuremobile,
                            @RequestParam(required = true) String insureemail,
                            @RequestParam(required = true) String insureaddress,
                            @RequestParam(required = true) String insuredname,
                            @RequestParam(required = true) String insuredidType,
                            @RequestParam(required = true) String insuredidNumber,
                            @RequestParam(required = true) String insuredgender,
                            @RequestParam(required = true) String insuredbirthDay,
                            @RequestParam(required = true) String insuredmobile,
                            @RequestParam(required = true) String insuredemail,
                            @RequestParam(required = true) String insuredaddress
) throws IOException, DocumentException {


        String requestUUID = getUUID32();

        String xml = BuildXml.buildXML(requestUUID,pipeCode,md5EncryptedValue,businessUnitCode,riskCode,rationType,premium,amount,operateTime,startDate,endDate,startHour,
                endHour,unit,insureCount,sumPremium,sumAmount,insurename,insureidType,insureidNumber,insuregender,insurebirthDay,insuremobile,insureemail,insureaddress,insuredname,
                insuredidType,insuredidNumber,insuredgender,insuredbirthDay,insuredmobile,insuredemail,insuredaddress);

        HttpClient client = new HttpClient();

        String reposexml = client.sendXMLDataByPost("http://56.145.32.32:8080/EasyOrder/webservice/insure",xml);
//        log.info("repose:" + reposemessage);

        Document doc = null;
        doc = DocumentHelper.parseText(reposexml); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
        Iterator responseHead = rootElt.elementIterator("responseHead"); // 获取根节点下的子节点head
//        while (responseHead.hasNext()) {
            Element recordEle = (Element) responseHead.next();
            String rmd5EncryptedValue = recordEle.elementTextTrim("md5EncryptedValue"); // 拿到head节点下的子节点title值
            System.out.println("md5EncryptedValue:" + rmd5EncryptedValue);
            String rpipeCode = recordEle.elementTextTrim("rpipeCode");
            System.out.println("pipeCode:" + pipeCode);
            String rrequestUUID = recordEle.elementTextTrim("rrequestUUID");
//        }
        Iterator responseBody = rootElt.elementIterator("responseBody");
//        while (responseBody.hasNext()) {
            Element recordEle2 = (Element) responseBody.next();
            String policyNo = recordEle2.elementTextTrim("policyNo");
            System.out.println("policyNo:" + policyNo);
            String proposalNo = recordEle2.elementTextTrim("proposalNo");
            System.out.println("proposalNo:" + proposalNo);
            String responseTime = recordEle2.elementTextTrim("responseTime");
            System.out.println("responseTime:" + responseTime);
            String resultCode = recordEle2.elementTextTrim("resultCode");
            System.out.println("resultCode:" + resultCode);
            String resultFlag = recordEle2.elementTextTrim("resultFlag");
            System.out.println("resultFlag:" + resultFlag);
            String resultMessage = recordEle2.elementTextTrim("resultMessage");
            System.out.println("resultMessage:" + resultMessage);
//        }
        if(resultFlag.equals("true")){
            return new ResultMap(1, resultMessage, true, policyNo,proposalNo );
        }else{
            return new ResultMap(0, resultMessage, false, "","" );
        }


    }
    @PostMapping(value = "/surrend")
    public ResultMap surrend(HttpSession session,
//                             @RequestParam(required = true) String requestUUID,
//                             @RequestParam(required = true) String pipeCode,
//                             @RequestParam(required = true) String md5EncryptedValue,
                             @RequestParam(required = true) String policyno
    )throws IOException, DocumentException {
        String requestUUID = new String();
        String pipeCode = new String();
        String md5EncryptedValue =  new String();

        if(policyno.length() != 22){
            return new ResultMap(0, "保单号错误", false, policyno,null );
        }
        List<Map<String,Object>> queryReturnData =  easyorder.queryForList("select requestUUID,pipeCode,md5EncryptedValue from PrpInsureRequest\n" +
                "where policyno = ? ",policyno);
        if(queryReturnData.isEmpty()){
            return new ResultMap(1, "查不到保单信息", false, "",null );
        }else {
            requestUUID =  queryReturnData.get(0).get("requestUUID").toString();
            pipeCode =  queryReturnData.get(0).get("pipeCode").toString();
            md5EncryptedValue =  queryReturnData.get(0).get("md5EncryptedValue").toString();
        }



        String xml = BuildXml.buildXML2(requestUUID,pipeCode,md5EncryptedValue,policyno);
//        log.info(xml);
        HttpClient client = new HttpClient();

        String reposexml = client.sendXMLDataByPost("http://56.145.32.32:8080/EasyOrder/webservice/surrend",xml);

        Document doc = null;
//        log.info(reposexml);

        doc = DocumentHelper.parseText(reposexml); // 将字符串转为XML



        org.dom4j.Element rootElt = doc.getRootElement(); // 获取根节点
//        System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
        Iterator responseHead = rootElt.elementIterator("responseHead"); // 获取根节点下的子节点head

        Element recordEle = (Element) responseHead.next();
        String rmd5EncryptedValue = recordEle.elementTextTrim("md5EncryptedValue"); // 拿到head节点下的子节点title值
//        System.out.println("md5EncryptedValue:" + rmd5EncryptedValue);

        String rpipeCode = recordEle.elementTextTrim("rpipeCode");
//        System.out.println("pipeCode:" + pipeCode);

        String rrequestUUID = recordEle.elementTextTrim("rrequestUUID");
        Iterator responseBody = rootElt.elementIterator("responseBody");
        Element recordEle2 = (Element) responseBody.next();

        String endorseno = recordEle2.elementTextTrim("endorseno");
//        System.out.println("endorseno:" + endorseno);

        String responseTime = recordEle2.elementTextTrim("responseTime");
//        System.out.println("responseTime:" + responseTime);

        String resultCode = recordEle2.elementTextTrim("resultCode");
//        System.out.println("resultCode:" + resultCode);

        String resultFlag = recordEle2.elementTextTrim("resultFlag");
//        System.out.println("resultFlag:" + resultFlag);

        String resultMessage = recordEle2.elementTextTrim("resultMessage");
//        System.out.println("resultMessage:" + resultMessage);

        return new ResultMap(1, resultMessage, true, endorseno,null );
    }




    @PostMapping(value = "query")
    public ResultMap query(HttpSession session,
                           @RequestParam(required = true) String queryType,
                           @RequestParam(required = true) String queryCondition
    )throws IOException, DocumentException {
        String queryAnd = new String("参数为空");  //sql查询的条件
        if(queryType.equals("0")){                  // 保单号查询
            if(queryCondition.length() != 22){      //保单长度校验
                return new ResultMap(0, "出错", false, queryCondition,"保单号错误" );
            }
            queryAnd = "policyno";
        }else if(queryType.equals("1")){            // 投保人身份证查询
            IDCard idCard  =  new IDCard();         //身份证校验
            boolean a = false;
            try{
                a =  idCard.IDCardValidate(queryCondition);
            }catch (Exception e){
                e.printStackTrace();
            }

            if(a){
                queryAnd = "applicantIdentifyNumber";

            }else {
                return new ResultMap(0, "身份证校验失败", false, queryCondition,"" );
            }
        }else if(queryType.equals("2")){            // 投保人查询
            if(queryCondition.length() < 2){
                return new ResultMap(0, "投保人姓名不能小于2个字", false, queryCondition,"" );
            }
            queryAnd = "applicantName";
        }else{
            return new ResultMap(1, "出错", false, queryAnd,"" );
        }


        List<Map<String, Object>> queryReturnData = easyorder.queryForList("select policyno,proposalno,startdate,enddate,unit,premium,amount,\n" +
                "applicantName,applicantIdentifyType,applicantIdentifyNumber,applicantPhoneNumber,applicantEmail,applicantAddress,\n" +
                " insuredname,   identifytype,identifynumber,phonenumber,email,insuredAddress\n" +
                "from prporder\n" +
                "where " + queryAnd + " = ?\n",queryCondition);

        if(queryReturnData.isEmpty()){
            return new ResultMap(0, "查询失败", false, "","" );
        }else{
            return new ResultMap(1, "查询成功", true, queryReturnData,"" );
        }

    }

    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}

 