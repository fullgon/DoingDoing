import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ChangePassword from "./ChangePassword";
import UserInfo from "./UserInfo";


const Privacy = () =>{

    const navigate = useNavigate();
    const [isPasswordChange, setIsPasswordChange] = useState(false);

    const changePassword = (check) => {
        setIsPasswordChange(check);
    }

    const withdrawal = () =>{
        if(window.confirm("회원탈퇴를 진행하시겠습니까?")){
            axios.delete(`/api/users`, {
                headers:{
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            }).then((res)=>{
                if(res.status == 204){
                    alert("회원탈퇴 되었습니다");
                    navigate(`/`);
                }
            }).catch(error =>{
                alert("에러");
                console.log(error);
            })
        }
    }

    return(
        <div>
           {isPasswordChange? 
           <ChangePassword setIsPasswordChange={changePassword}/> :
           <div>
                <UserInfo/>
                <div onClick={() => setIsPasswordChange(true)}>비밀번호 변경</div>
                <div onClick={withdrawal}>회원탈퇴</div>   
            </div>}
            
            
            
        </div>
    )
}

export default Privacy;
