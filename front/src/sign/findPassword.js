import axios from "axios";
import { useState } from "react";
import {useNavigate} from "react-router-dom"



const FindPassword = () =>{
    
    const navigate = useNavigate();

    const [isChange, setIsChange] = useState(false);
    const [isSend, setIsSend] = useState(false);
    const [id, setId] = useState("");
    const [email, setEmail] = useState("");
    const [adress, setAdress] = useState("");
    const [authkey, setAuthkey] = useState("");

    const [pwd, setPwd] = useState("");
    const [pwdConfirm, setPwdConfirm] = useState("");
    
    const sendAuth = () =>{
        if(id == "" || id.length < 5){
            window.alert("아이디를 입력해주세요");
        }
        else if(email == "" || adress == ""){
            window.alert("이메일을 입력해주세요");
        }
        else{
            //인증번호 전송
            axios.post("api/auth/sendAuthKey",{
                userId: id,
                email: `${email}@${adress}`,
            }).then(res =>{
                //결과랑 메시지
                window.alert("인증번호 보냈음");
                setIsSend(true);
            }).catch(error =>{
                window.alert("인증번호 보내기 실패");
            })
        }
    }

    const checkAuth = () =>{
        //인증번호 확인
        axios.post("api/auth/checkAuthKey",{
            userId: id,
            authKey: authkey,
        }).then(res => {
            //jwt하고 result 받음
            //result.result가 'ok'일 때 
            setIsChange(true);
            setIsSend(false);
        })
        //로그인 페이지로 이동
        navigate(-1);
    }

    //async/awit 형태
    /*
    const checkAuth = async () =>{
        //인증번호 확인
        try{
            const res = await axios.post("api/auth/checkAuthkey",{
                userId: id,
                authKey: authkey,
            });
            res에 토큰값과 result 저장됨
            //로그인 페이지로 이동
            navigate(-1);
        }catch(e){
            console.log(e);
        }
    }
    */
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
                const res = await axios.patch("/api/auth/resetPassword",{
                    userID: id,
                    password: pwd,
                },{
                    headers:{
                        "Content-Type": `application/json`,
                        //accessToken 넣기
                    }
                });
            }

        }catch(e){

        }
    }
    return(
        <div>
            {isChange ?
            //비밀번호 변경 양식 
            <div>
                <p>비밀번호: <input type="password" placeholder="비밀번호"
                 onChange={(e)=> {setPwd(e.target.value);}}/></p>
                <p>비밀번호 확인: <input type="password" placeholder="비밀번호 확인"
                 onChange={(e)=> {setPwdConfirm(e.target.value);}}/></p>
                 <button onClick={resetPassword}>변경</button>
            </div>
            :
            //이메일 인증 양식
            <div>
                <p>아이디: <input type="text" placeholder="아이디"
                 onChange={(e)=> {setId(e.target.value);}}/></p>
                <p>
                    e-mail: <input type="text" placeholder="e-mail"
                     onChange={(e)=> {setEmail(e.target.value);}}/>
                    @
                    <input type="text" onChange={(e)=> {setAdress(e);}}/> 
                    <button onClick={sendAuth}>전송</button>
                </p>
            </div>
            }
            
            { isSend ?
            <p>
                인증번호: <input type="text" placeholder="인증번호" onChange={(e)=> {setAuthkey(e.target.value);}}/>
                <button onClick={checkAuth}>인증</button>
            </p>
            : null
            }
            
        </div>
    )
}

export default FindPassword