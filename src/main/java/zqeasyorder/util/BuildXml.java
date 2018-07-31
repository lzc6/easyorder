package zqeasyorder.util;

public class BuildXml {

    public static String buildXML(String requestUUID, String pipeCode, String md5EncryptedValue, String businessUnitCode, String riskCode, String rationType, String premium, String amount,String operateTime, String startDate, String endDate, String startHour, String endHour,String unit, String insureCount, String sumPremium, String sumAmount, String insurename, String insureidType, String insureidNumber, String insuregender, String insurebirthDay, String insuremobile, String insureemail, String insureaddress, String insuredname, String insuredidType, String insuredidNumber, String insuredgender, String insuredbirthDay, String insuredmobile, String insuredemail, String insuredaddress) {
        StringBuilder builder = new StringBuilder();

        builder.append("<insureRequest>\n");

        builder.append("<requestHead>\n");
        builder.append("<requestUUID>").append(requestUUID).append("</requestUUID>\n");
        builder.append("<pipeCode>").append(pipeCode).append("</pipeCode>\n");
        builder.append("<md5EncryptedValue>").append(md5EncryptedValue).append("</md5EncryptedValue>\n");
        builder.append("</requestHead>\n");

        builder.append("<requestBody>\n");

        builder.append("<businessUnitInfo>\n");
        builder.append("<businessUnitCode>").append(businessUnitCode).append("</businessUnitCode>\n");
        builder.append("</businessUnitInfo>\n");


        builder.append("<productInfo>\n");
        builder.append("<riskCode>").append(riskCode).append("</riskCode>\n");
        builder.append("<rationType>").append(rationType).append("</rationType>\n");
        builder.append("<premium>").append(premium).append("</premium>\n");
        builder.append("<amount>").append(amount).append("</amount>\n");
        builder.append("</productInfo>");

        builder.append("<policyInfo>\n");

        builder.append("<operateTime>").append(operateTime).append("</operateTime>\n");
        builder.append("<startDate>").append(startDate).append("</startDate>\n");
        builder.append("<endDate>").append(endDate).append("</endDate>\n");
        builder.append("<startHour>").append(startHour).append("</startHour>\n");
        builder.append("<endHour>").append(endHour).append("</endHour>\n");
        builder.append("<unit>").append(unit).append("</unit>\n");
        builder.append("<insureCount>").append(insureCount).append("</insureCount>\n");
        builder.append("<sumPremium>").append(sumPremium).append("</sumPremium>\n");
        builder.append("<sumAmount>").append(sumAmount).append("</sumAmount>\n");
        builder.append("</policyInfo>\n");

        builder.append("<applicantInfo>\n");
        builder.append("<name>").append(insurename).append("</name>\n");
        builder.append("<applicantename>").append("qqq").append("</applicantename>\n");
        builder.append("<idType>").append(insureidType).append("</idType>\n");
        builder.append("<idNumber>").append(insureidNumber).append("</idNumber>\n");
        builder.append("<gender>").append(insuregender).append("</gender>\n");
        builder.append("<birthDay>").append(insurebirthDay).append("</birthDay>\n");
        builder.append("<mobile>").append(insuremobile).append("</mobile>\n");
        builder.append("<email>").append(insureemail).append("</email>\n");
        builder.append("<address>").append(insureaddress).append("</address>\n");
        builder.append("</applicantInfo>\n");

        builder.append("<insuredInfo>\n");
        builder.append("<name>").append(insuredname).append("</name>\n");
        builder.append("<applicantename>").append("qqq").append("</applicantename>\n");
        builder.append("<idType>").append(insuredidType).append("</idType>\n");
        builder.append("<idNumber>").append(insuredidNumber).append("</idNumber>\n");
        builder.append("<gender>").append(insuredgender).append("</gender>\n");
        builder.append("<birthDay>").append(insuredbirthDay).append("</birthDay>\n");
        builder.append("<mobile>").append(insuredmobile).append("</mobile>\n");
        builder.append("<email>").append(insuredemail).append("</email>\n");
        builder.append("<address>").append(insuredaddress).append("</address>\n");
        builder.append("</insuredInfo>\n");

        builder.append("</requestBody>\n");
        builder.append("</insureRequest>\n");

        return builder.toString();
    }
    public static String buildXML2(String requestUUID, String pipeCode, String md5EncryptedValue, String policyno) {
        StringBuilder builder = new StringBuilder();

        builder.append("<surrendRequest>\n");
        builder.append("<requestHead>\n");
        builder.append("<md5EncryptedValue>").append(md5EncryptedValue).append("</md5EncryptedValue>\n");
        builder.append("<pipeCode>").append(pipeCode).append("</pipeCode>\n");
        builder.append("<requestUUID>").append(requestUUID).append("</requestUUID>\n");
        builder.append("</requestHead>\n");

        builder.append("<requestBody>\n");
        builder.append("<policyno>").append(policyno).append("</policyno>\n");
        builder.append("</requestBody>\n");

        builder.append("</surrendRequest>\n");

        return builder.toString();
    }
}

 