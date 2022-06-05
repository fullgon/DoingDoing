import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import ChangePassword from "./ChangePassword";
import UserInfo from "./UserInfo";


const Privacy = () =>{

    const [isPasswordChange, setIsPasswordChange] = useState(false);

    const changePassword = () => {
        setIsPasswordChange(false);
    }

    return(
        <div>
           {isPasswordChange? 
           <ChangePassword setIsPasswordChange={changePassword}/> :
           <div>
                <UserInfo/>
                <div onClick={() => setIsPasswordChange(true)}>비밀번호 변경</div>
                <div>회원탈퇴</div>   
            </div>}
            
            
            
        </div>
    )
}

export default Privacy;