package com.chohee.common;

public class CommonValue {

    /**
     * Google Map Key
     */
    public static final String GoogleMapKey = "AIzaSyCtw9pTJ1fblFr6H98Dlzqf-yZGlZXbn4A";
    /**
     * Google Message Key
     */
	public static final String GoogleMessageKey = "821251495382";
    /**
     * Web 서버 도메인
     */
	public static final String HomeURL = "http://www.adflyer.co.kr";


	/* 이미지 정보 */
	public static final int FILEUPLOAD_MAX_MEM_SIZE				= 1 * 1024 * 1024;
	public static final int FILEUPLOAD_MAX_REQ_SIZE 			= 20 * 1024 * 1024;
	public static final String FILEUPLOAD_VALID_FILE_EXT[]		= {"jpg", "jpeg", "gif", "png", "mp4"};


    /**
     * 이미지 서버 도메인
     */
	public static final String IMAGE_SERVER						= "http://img.adflyer.co.kr";
    /**
     * 임시 이미지 서버 도메인
     */
	public static final String IMAGE_TEMP_SERVER				= "http://img.adflyer.co.kr/temp";
    /**
     * Web 서버 도메인
     */
	public static final String HOME_SERVER      ="http://www.adflyer.co.kr";
    /**
     * 관리자 Web 서버 도메인
     */
	public static final String ADMIN_SERVER		="http://admin.adflyer.co.kr";
    /**
     * 가맹점 Web 서버 도메인
     */
	public static final String PARTNER_SERVER	="http://partner.adflyer.co.kr";
    /**
     * API 상용 서버
     */
	public static final String API_SERVER		="http://api2.adflyer.co.kr";


    /**
     * Cafe24 smtp 서버 도메인
     */
	public static final String SMTP_SERVER		= "smtp.cafe24.com";
    /**
     * Cafe24 smtp Port
     */
    public static final String SMTP_PORT	    = "587";
    /**
     * Cafe24 메일 아이디
     */
	public static final String MAIL_ID			= "ceo@dasuree.com";
    /**
     * Cafe24 메일 Password
     */
    public static final String MAIL_PWD			= "6wkd@5wjf";
    /**
     * Google smtp mail ID
     */
	public final String GMAIL_ID				= "amrs73@gmail.com";
    /**
     * Google smtp mail password
     */
	public final String GMAIL_PWD				= "ntkklegqpocupzoa";
    /**
     * 보내는 사람 Mail 주소
     */
	public final String FROM_ADDRESS			= "master@adflyer.co.kr";


    /**
     * API 개발 서버
     **/
    public static final String API_DEV = "http://165.132.140.251:80/";
    /**
     * API 상용 서버
     **/
    public static final String API_LIVE = "http://api2.adflyer.co.kr/";
    /**
     * API 서버 도메인
     **/
    public static final String API_HOST = API_LIVE;
    /**
     * Web Page 서버 도메인
     **/
    public static final String PAGE_HOST = "http://www.adflyer.co.kr/";

    /********************
     * API Result Code
     ********************/
    public static final String RESULT_OK = "100"; /* 성공 */

    /********************
     * API Json Header
     ********************/
    public static final String JSON_RESULT = "RESULT"; /* 결과 헤더 */
    public static final String JSON_CD = "CD"; /* 결과 코드 */
    public static final String JSON_MSG = "MSG"; /* 결과 메시지 */
    public static final String JSON_DATA = "DATA"; /* 데이터 헤더 */

    /********************
     * Server API Page
     ********************/
    public static final String PAGE_FLYER_LIST = "flyer_default_json.do"; /* 전단지 목록 */
    public static final String PAGE_FLYER_SAVE = "flyer_check_json.do"; /* 전단지 저장 */


	public String getMapKey(){
		return GoogleMapKey;
	}

	public String getMessageKey(){
		return GoogleMessageKey;
	}

	public String getHomeURL(){
		return HomeURL;
	}

}
