import axios from "axios";
import { useState } from "react";
import {useNavigate} from "react-router-dom"


function SignUp(){

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");
    const [email, setEmail] = useState("");
    const [adress, setAdress] = useState("");
    const [pwdConfirm, setPwdConfirm] = useState("");
    
    const signUp = () =>{
        if(name == ""){
            window.alert("이름을 입력해주세요");
        }
        else if(id == "" || id.length < 5){
            window.alert("아이디를 입력해주세요");
        }
        else if(email == "" || adress == ""){
            window.alert("이메일을 입력해주세요");
        }
        else if(pwd == "" || pwd.length < 8){
            window.alert("비밀번호를 입력해주세요");
        }
        else if(pwd != pwdConfirm){
            window.alert("비밀번호가 일치하지 않습니다.");
        }
        else{
            //회원가입 완료
            axios.post("/api.auth/signUp",{
                userId: id,
                password: pwd,
                email: `${email}@${adress}`,
                name: name,
            }).then(response=>{
                if(response.result.result == 'ok'){
                    //로그인 페이지로 이동
                    window.alert("회원가입에 성공하였습니다.");
                    navigate(-1);
                }
                else{
                    window.alert("회원가입에 실패하였습니다.");
                }
            })
            
        }
    }

    return(
        <div>
            <p>이름: <input type="text" placeholder="이름" onChange={(e)=> {setName(e.target.value);}}/></p>
            <p>아이디: <input type="text" placeholder="아이디" onChange={(e)=> {setId(e.target.value);}}/></p>
            <p>
                e-mail: <input type="text" placeholder="e-mail" onChange={(e)=> {setEmail(e.target.value);}}/>
                @<input type="text" onChange={(e)=> {setAdress(e);}}/> 
            </p>
            <p>비밀번호: <input type="password" placeholder="비밀번호" onChange={(e)=> {setPwd(e.target.value);}}/></p>
            <p>비밀번호 확인: <input type="password" placeholder="비밀번호" onChange={(e)=> {setPwdConfirm(e.target.value);}}/></p>
            <button onClick={signUp}>회원가입</button>
        </div>
    )
}

export default SignUp;