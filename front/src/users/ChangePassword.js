import axios from "axios";
import { useState } from "react";
import styles from "./users.module.css"

const ChangePassword = ({setIsPasswordChange}) => {

    const [isPasswordCehck, setIsPasswordCheck] = useState(false);
    const [pwd, setPwd] = useState("");
    const [password, setPassword] = useState("");
    const [pwdConfirm, setPwdConfirm] = useState("");

    const userCheck = async () => {
        try{
            if(pwd == ""){
                alert("비밀번호를 입력해주십시오.");
                return true;
            }
            const res = await axios.post(`/api/auth/check/password`,{password : pwd},
            {
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })

            if(res.data.check){
                setIsPasswordCheck(res.data.check);
            }
        }catch(e){
            alert("에러");
            console.log(e);
        }
    }

    const resetPassword = async ()=>{
        try{
            if(pwd == ""){
                window.alert("비밀번호를 입력해주세요");
            }
            else if(pwd.length < 8){
                window.alert("비밀번호는 8자리 이상입니다");
            }
            else if(pwd != pwdConfirm){
                window.alert("비밀번호가 일치하지 않습니다");
            }
            else{
                const userId = localStorage.getItem('userId')
                const res = await axios.put("/api/auth/reset/password",{
                    userId,
                    password: pwd,
                },{
                    headers:{
                        'Content-Type' : 'application/json',
                        'Authorization' : localStorage.getItem('accessToken'),
                    }
                });

                if(res.status == 204){
                    window.alert("비밀번호 변경 완료");
                    setIsPasswordChange(false);
                }
            }

        }catch(e){
            alert("에러남");
            console.log(e);
        }
    }

    return (
        <div>
            {isPasswordCehck ? 
            <div className={styles.container}>
                <input type="password"  onChange={(e)=> {setPassword(e.target.value); }} value={password} placeholder="비밀번호"/>
                <input type="password" placeholder="비밀번호 확인"
                onChange={(e)=> {setPwdConfirm(e.target.value);}}/>
                <button onClick={resetPassword}>변경</button>
                <button onClick={()=>{setIsPasswordChange(false)}}>취소</button>
            </div>
            :
            <div className={styles.container}>
                <input type="password" placeholder="비밀번호" onChange={(e)=>{setPwd(e.target.value)}}/>
                <button onClick={userCheck}>사용자 확인</button>
                <button onClick={()=>{setIsPasswordChange(false)}}>취소</button>
            </div>
            }
        </div>
    )
}

export default ChangePassword;