/**
 * 
 */
package com.ideamoment.wx;


/**
 * @author Chinakite
 *
 */
public class IdeaWxException extends RuntimeException {
    /**
     * 异常编码
     */
    protected String code;
    
    /**
     * 异常消息
     */
    protected String msg;
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    
    public IdeaWxException(String msg) {
        super(msg);
    }

    public IdeaWxException(String code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public IdeaWxException(String code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }
    
}
