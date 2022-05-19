import { useState } from "react";
import {useNavigate} from "react-router-dom"


function SignUp(){

    const navigate = useNavigate();

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");
    const [name, setName] = useState("");
    const [pwdConfirm, setPwdConfirm] = useState("");
    
    const signUp = () =>{
        if(id == "" || id.length < 5){
            window.alert("아이디를 입력해주세요");
        }
        else if(pwd == "" || pwd.length < 8){
            window.alert("비밀번호를 입력해주세요");
        }
        else if(pwd != pwdConfirm){
            window.alert("비밀번호가 일치하지 않습니다.");
        }
        else{
            //회원가입 완료 & 로그인 페이지로 이동
            navigate(-1);
        }
    }

    return(
        <div>
            아이디: <input type="text" placeholder="아이디" onChange={(e)=> {setId(e.target.value);}}/>
            이름: <input type="text" placeholder="이름" onChange={(e)=> {setName(e.target.value);}}/>
            비밀번호: <input type="password" placeholder="비밀번호" onChange={(e)=> {setPwd(e.target.value);}}/>
            비밀번호 확인: <input type="password" placeholder="비밀번호" onChange={(e)=> {setPwdConfirm(e.target.value);}}/>
            <button onClick={signUp}>회원가입</button>
        </div>
    )
}

export default SignUp;