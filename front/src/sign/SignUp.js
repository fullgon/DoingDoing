import axios from "axios";
import { useState } from "react";
import {useNavigate} from "react-router-dom"


function SignUp(){

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");
    const [pwdConfirm, setPwdConfirm] = useState("");
    const [email, setEmail] = useState("");
    const [adress, setAdress] = useState("");
    const [authkey, setAuthkey] = useState("");
    const [isSend, setIsSend] = useState(false);
    let isId  = false, isEmail = false;
    
    const signUp = () =>{
        if(name == ""){
            window.alert("이름을 입력해주세요");
        }
        else if(idCheck){
            window.alert("아이디 중복확인을 해주세요");
        }
        else if(isEmail){
            window.alert("이메일 인증을 해주세요");
        }
        else if(pwd == ""){
            window.alert("비밀번호를 입력해주세요");
        }
        else if(pwd.length < 8){
            window.alert("비밀번호는 8자리 이상입니다");
        }
        else if(pwd != pwdConfirm){
            window.alert("비밀번호가 일치하지 않습니다");
        }
        else{
            //회원가입 완료
            axios.post("/api.auth/sign-up",{
                userId: id,
                password: pwd,
                email: `${email}@${adress}`,
                name: name,
            },{
                headers:{
                    'Content-Type' : 'application/json',
                    //'Authorization' : authToken
                }
            }).then(res=>{
                if(res.result.result == 'ok'){
                    //로그인 페이지로 이동
                    window.alert("회원가입에 성공하였습니다.");
                    navigate(-1);
                }
                else{
                    window.alert("회원가입에 실패하였습니다.");
                }
            }).catch(e => alert("에러남"));
            
        }
    }

    const idCheck = () =>{ 
        //중복된 아이디가 없을 때
        axios.post("api/auth/check/user-id",{
            userId:id
        },{
            headers: {
                'Content-Type' : 'application/json',
                //'Authorization' : authToken
            }
        }).then(res => {
            if(res.result.result == 'ok'){
                //중복체크 완료
                isId = true;
            }
        }).catch(e => {
            alert("에러남");
        })
        
    }

    const sendAuth = () =>{
        if(!isId){
            window.alert("아이디 중복확인을 해주세요");
        }
        else if(email == "" || adress == ""){
            window.alert("이메일을 입력해주세요");
        }
        else{
            //인증번호 전송
            axios.post("api/auth/send/auth-key",{
                userId: id,
                email: `${email}@${adress}`,
            },{
                headers: {
                    'Content-Type' : 'application/json',
                    //'Authorization' : authToken
                }
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
        axios.post("api/auth/check/auth-key",{
            userId: id,
            //accessToken주기
            //authKey: authkey,
        },{
            headers: {
                'Content-Type' : 'application/json',
                //'Authorization' : authToken
            }
        }).then(res => {
            //jwt하고 result 받음
            //result.result가 'ok'일 때 
            if(res.result.result == 'ok'){
                isEmail = true;
                alert("인증이 완료되었습니다.");
            }
            else{
                alert("인증번호가 틀렸습니다.");
            }
        }).catch(e => {
            alert("에러남");
        })
    }

    return(
        <div>
            <p>이름: <input type="text" placeholder="이름" onChange={(e)=> {setName(e.target.value);}}/></p>
            <p>
                아이디: <input type="text" placeholder="아이디" onChange={(e)=> {setId(e.target.value);}}/>
                <button onClick={idCheck}>중복확인</button>
            </p>
            <p>
                e-mail: <input type="text" placeholder="e-mail" onChange={(e)=> {setEmail(e.target.value);}}/>
                @<input type="text" placeholder="직접입력" onChange={(e)=> {setAdress(e);}}/>
                <button onClick={sendAuth}>전송</button>
                { isSend ?
                    <p>
                        인증번호: <input type="text" placeholder="인증번호" onChange={(e)=> {setAuthkey(e.target.value);}}/>
                        <button onClick={checkAuth}>인증</button>
                    </p>
                    : null
                }
            </p>
            <p>비밀번호: <input type="password" placeholder="비밀번호" onChange={(e)=> {setPwd(e.target.value);}}/></p>
            <p>비밀번호 확인: <input type="password" placeholder="비밀번호" onChange={(e)=> {setPwdConfirm(e.target.value);}}/></p>
            <button onClick={signUp}>회원가입</button>
        </div>
    )
}

export default SignUp;