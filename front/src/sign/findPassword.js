import axios from "axios";
import { useState } from "react";
import {useNavigate} from "react-router-dom"



const findPassword = () =>{
    
    const navigate = useNavigate();

    const [id, setId] = useState("");
    const [email, setEmail] = useState("");
    const [adress, setAdress] = useState("");
    const [authkey, setAuthkey] = useState("");
    
    const sendAuth = () =>{
        if(id == "" || id.length < 5){
            window.alert("아이디를 입력해주세요");
        }
        else if(email == "" || adress == ""){
            window.alert("이메일을 입력해주세요");
        }
        else{
            //인증번호 전송
            axios.post("api/auth/sendAuthkey",{
                userId: id,
                email: `${email}@${adress}`,
            }).then(res =>{
                //결과랑 메시지
                window.alert("인증번호 보냈음");
            }).catch(error =>{
                window.alert("인증번호 보내기 실패");
            })
        }
    }

    const checkAuth = () =>{
        //인증번호 확인
        axios.post("api/auth/checkAuthkey",{
            userId: id,
            authKey: authkey,
        }).then(res => {
            //jwt하고 result 받음
        })
        //로그인 페이지로 이동
        navigate(-1);
    }

    return(
        <div>
            <p>아이디: <input type="text" placeholder="아이디" onChange={(e)=> {setId(e.target.value);}}/></p>
            <p>
                e-mail: <input type="text" placeholder="e-mail" onChange={(e)=> {setEmail(e.target.value);}}/>
                @<input type="text" onChange={(e)=> {setAdress(e);}}/> 
            </p>
            <p>
                인증번호: <input type="text" placeholder="인증번호" onChange={(e)=> {setAuthkey(e.target.value);}}/>
                <button onClick={checkAuth}>인증</button>
            </p>
            <button onClick={sendAuth}>인증번호 전송</button>
        </div>
    )
}

export default findPassword