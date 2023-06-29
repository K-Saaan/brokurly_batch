package brokurly.batch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CipherUtil {
    public static Logger logger = LoggerFactory.getLogger(CipherUtil.class);

    /*
       List<> 복호화
       @param : List, Class(Entity), key
     */
    @SuppressWarnings("unchecked")
    public static Page changeDecodeObjectList(Page objList, Class clz, String key){
        List contentList = new ArrayList<>(objList.getContent());
        if(contentList!=null){
            for(int i=0; i<contentList.size(); i++){
                Object obj = (Object)contentList.get(i);
                changeDecodeObject(obj,clz, key);
                contentList.remove(i);
                contentList.add(i, obj);
            }
        }
        Page newPage = new PageImpl<>(contentList, objList.getPageable(), objList.getTotalElements());
        return newPage;
    }

    /*
       List<> 암호화
       @param : List, Class(Entity), key
     */
    @SuppressWarnings("unchecked")
    public static void changeEncodeObjectList(List objList, Class clz, String key){
        if(objList!=null){
            for(int i=0; i<objList.size(); i++){
                Object obj = (Object)objList.get(i);
                changeEncodeObject(obj,clz, key);
                objList.remove(i);
                objList.add(i, obj);
            }
        }
    }


    @SuppressWarnings("unchecked")
    public static void changeDecodeObject(Object obj,Class clz, String key){
        List<Object> methods = new ArrayList<Object>();

        methods.addAll(Arrays.asList(clz.getDeclaredMethods()));
        Method method = null;

        for(int j=0; j<methods.size(); j++){
            method = (Method)methods.get(j);
            String orgValue = "";
            String chgValue = "";
            Object invokeObj = null;

            try {
                if (method.getName().equals("getCustNm")) { // 고객명
                    method = clz.getMethod("getCustNm", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setCustNm", new Class[]{String.class});
                        try {
                            chgValue = CommonUtil.maskName(AES256Util.deCode(orgValue, key));
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                } else if (method.getName().equals("getUserNm")) {  // 사용자명
                    method = clz.getMethod("getUserNm", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setUserNm", new Class[]{String.class});
                        try {
                            chgValue = CommonUtil.maskName(AES256Util.deCode(orgValue, key));
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getCustEmail")) {    // 고객이메일
                    method = clz.getMethod("getCustEmail", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setCustEmail", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.deCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getCustAddrDtl")) {  // 고객상세주소
                    method = clz.getMethod("getCustAddrDtl", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setCustAddrDtl", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.deCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getPymAcntNm")) {  // 납부자명
                    method = clz.getMethod("getPymAcntNm", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setPymAcntNm", new Class[]{String.class});
                        try {
                            chgValue = CommonUtil.maskName(AES256Util.deCode(orgValue, key));
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getPymAcntTel")) {  // 납부자전화번호
                    method = clz.getMethod("getPymAcntTel", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setPymAcntTel", new Class[]{String.class});
                        try {
                            chgValue = CommonUtil.maskTelNo(AES256Util.deCode(orgValue, key));
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAcntHolder")) {  // 예금주명
                    method = clz.getMethod("getAcntHolder", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAcntHolder", new Class[]{String.class});
                        try {
                            chgValue = CommonUtil.maskName(AES256Util.deCode(orgValue, key));
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAcntNo")) {  // 계좌/카드 번호
                    method = clz.getMethod("getAcntNo", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAcntNo", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.deCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getVirtualAcntNo")) {  // 가상계좌번호
                    method = clz.getMethod("getVirtualAcntNo", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setVirtualAcntNo", new Class[]{String.class});
                        try {
                            chgValue = CommonUtil.maskAccountNo(AES256Util.deCode(orgValue, key));
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAddr")) {  // 주소
                    method = clz.getMethod("getAddr", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAddr", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.deCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAddrDtl")) {  // 상세주소
                    method = clz.getMethod("getAddrDtl", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAddrDtl", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.deCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("CipherUtil IllegalAccessException " + e);
            } catch (IllegalArgumentException e) {
                logger.error("CipherUtil IllegalArgumentException " + e);
            } catch (InvocationTargetException e) {
                logger.error("CipherUtil InvocationTargetException " + e);
            } catch (NoSuchMethodException e) {
                logger.error("CipherUtil NoSuchMethodException " + e);
            } catch (SecurityException e) {
                logger.error("CipherUtil SecurityException " + e);
            }

        }
    }

    @SuppressWarnings("unchecked")
    public static void changeEncodeObject(Object obj,Class clz, String key){
        List<Object> methods = new ArrayList<Object>();

        methods.addAll(Arrays.asList(clz.getDeclaredMethods()));
        Method method = null;

        for(int j=0; j<methods.size(); j++){
            method = (Method)methods.get(j);
            String orgValue = "";
            String chgValue = "";
            Object invokeObj = null;

            try {
                if (method.getName().equals("getCustNm")) { // 고객명
                    method = clz.getMethod("getCustNm", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setCustNm", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                } else if (method.getName().equals("getUserNm")) {  // 사용자명
                    method = clz.getMethod("getUserNm", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setUserNm", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getCustEmail")) {    // 고객이메일
                    method = clz.getMethod("getCustEmail", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setCustEmail", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getCustAddrDtl")) {  // 고객상세주소
                    method = clz.getMethod("getCustAddrDtl", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setCustAddrDtl", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getPymAcntNm")) {  // 납부자명
                    method = clz.getMethod("getPymAcntNm", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setPymAcntNm", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getPymAcntTel")) {  // 납부자전화번호
                    method = clz.getMethod("getPymAcntTel", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setPymAcntTel", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAcntHolder")) {  // 예금주명
                    method = clz.getMethod("getAcntHolder", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAcntHolder", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAcntNo")) {  // 계좌/카드 번호
                    method = clz.getMethod("getAcntNo", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAcntNo", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getVirtualAcntNo")) {  // 가상계좌번호
                    method = clz.getMethod("getVirtualAcntNo", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setVirtualAcntNo", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAddr")) {  // 주소
                    method = clz.getMethod("getAddr", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAddr", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }else if (method.getName().equals("getAddrDtl")) {  // 상세주소
                    method = clz.getMethod("getAddrDtl", (Class<?>[]) null);

                    invokeObj = method.invoke(obj);
                    if (invokeObj != null) {
                        orgValue = String.valueOf(method.invoke(obj));
                        method = clz.getMethod("setAddrDtl", new Class[]{String.class});
                        try {
                            chgValue = AES256Util.enCode(orgValue, key);
                        } catch (Exception e) {
                            chgValue = "";
                        }
                        method.invoke(obj, chgValue);
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("CipherUtil IllegalAccessException " + e);
            } catch (IllegalArgumentException e) {
                logger.error("CipherUtil IllegalArgumentException " + e);
            } catch (InvocationTargetException e) {
                logger.error("CipherUtil InvocationTargetException " + e);
            } catch (NoSuchMethodException e) {
                logger.error("CipherUtil NoSuchMethodException " + e);
            } catch (SecurityException e) {
                logger.error("CipherUtil SecurityException " + e);
            }

        }
    }
}
