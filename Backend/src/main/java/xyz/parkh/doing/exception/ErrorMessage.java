package xyz.parkh.doing.exception;

public enum ErrorMessage {
    EMAILSENDFAIL("이메일 전송에 실패했습니다."),
    EMAILVALIEDFAIL("유효하지 않은 이메일 주소입니다."),
    NOREQUIREDPARAMETER("필수 인자가 없습니다."),
    EXISTID("이미 존재하는 ID 입니다."),
    EXISTEMAIL("이미 존재하는 Email 입니다."),
    EXISTEMAILORID("이미 존재하는 아이디 혹은 이메일입니다."),
    NOEXISTID("존재하지 않는 아이디 입니다."),
    NOEXISTEMAIL("존재하지 않는 이메일 입니다."),
    NOEXISTEMAILORID("아이디 혹은 이메일이 존재하지 않습니다."),
    NOEXISTAUTHKEY("조회된 인증번호가 없습니다."),
    NOEXISTSCHEDULE("일정이 없습니다."),
    DIFFRENTSERVICETYPE("요청한 타입과 인증 하려하는 타입이 다릅니다."),
    DIFFRENTEMAILANDID("아이디와 이메일이 일치하지 않습니다."),
    DIFFRENTPASSWORD("비밀번호가 일치하지 않습니다."),
    ACCESSDENIED("접근할 수 있는 권한이 없습니다.");


    final private String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
