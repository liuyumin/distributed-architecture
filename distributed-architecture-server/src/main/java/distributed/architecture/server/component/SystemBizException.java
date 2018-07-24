package distributed.architecture.server.component;

public class SystemBizException extends RuntimeException {
    private static final long serialVersionUID = -4304022071416913225L;

    private String errorCode;

    private String errorMessage;

    public SystemBizException(){
        super();
    }

    public SystemBizException(String errorCode,String errorMessage){
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
