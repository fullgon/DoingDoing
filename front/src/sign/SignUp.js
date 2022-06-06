import axios from "axios";
import { useState } from "react";
import {useNavigate} from "react-router-dom"
import styles from "./css/SignUp.module.css"


function SignUp(){

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [id, setId] = useState("");
    const [fixId, setFixId] = useState();
    const [pwd, setPwd] = useState("");
    const [pwdConfirm, setPwdConfirm] = useState("");
    const [email, setEmail] = useState("");
    const [adress, setAdress] = useState("");
    const [authkey, setAuthkey] = useState("");

    const [isSend, setIsSend] = useState(false);
    const [isId, setIsId] = useState(false);
    const [isEmail, setIsEmail] = useState(false);

    const signUp = () =>{

        if(name == ""){
            alert("이름을 입력해주세요");
        }
        else if(!isId){
            alert("아이디 중복확인을 해주세요");
        }
        else if(!isEmail){
            alert("이메일 인증을 해주세요");
        }
        else if(pwd == ""){
            alert("비밀번호를 입력해주세요");
        }
        else if(pwd.length < 8){
            alert("비밀번호는 8자리 이상입니다");
        }
        else if(pwd != pwdConfirm){
            alert("비밀번호가 일치하지 않습니다");
        }
        else{
            //회원가입 완료
            axios.post("/api/auth/sign-up",{
                userId: id,
                password: pwd,
                email: `${email}@${adress}`,
                name: name,
            },{
                headers:{
                    'Content-Type' : 'application/json',
                }
            }).then(res=>{
                console.log(res);
                if(res.status == 204){
                    //로그인 페이지로 이동
                    alert("회원가입에 성공하였습니다.");
                    navigate(-1);
                    return;
                }
                alert("회원가입에 실패하였습니다.");
                
            }).catch(e =>{
                alert("에러남")
                console.log(e);
            });
            
        }
    }

    const idCheck = () =>{
        if(id == ""){
            alert("아이디를 입력해주세요");
            return;
        }
        else if(id.length < 5){
            alert("아이디는 5자리 이상입니다");
            return;
        }
        axios.post("/api/auth/check/user-id",{
            userId: id
        },{
            headers: {
                'Content-Type' : 'application/json',
            }
        }).then(res => {
            if(res.status == 200 && !res.data.check){
                //중복체크 완료
                setFixId(id);
                setIsId(true);
                console.log(res);
                alert("사용가능한 아이디입니다");
                return;
            }
            alert("중복된 아이디입니다");
        }).catch(e => {
            alert(e);
            console.log(e.response);
        })
        
    }

    const sendAuth = () =>{
        if(id != fixId){
            //중복확인한 아이디와 입력된 아이디가 다를경우
            setIsId(false);
        }
        if(!isId){
            window.alert("아이디 중복확인을 해주세요");
        }
        else if(email == "" || adress == ""){
            window.alert("이메일을 입력해주세요");
        }
        else{
            //인증번호 전송
            const e_mail = `${email}@${adress}`;
            axios.post("/api/auth/send/auth-key",{
                userId: id,
                email: `${email}@${adress}`,
                type: 1,
            },{
                headers: {
                    'Content-Type' : 'application/json',
                }
            }).then(res =>{
                console.log(res);
                if(res.status == 204){
                    //결과랑 메시지
                    window.alert("인증번호를 보냈습니다");
                    setIsSend(true);
                }
            }).catch(error =>{
                window.alert("인증번호 보내기 실패");
            })
        }
    }

    const checkAuth = () =>{
        if(id != fixId){
            //중복확인한 아이디와 입력된 아이디가 다를경우
            setIsId(false);
        }
        if(!isId){
            window.alert("아이디 중복확인을 해주세요");
        }
        else{
            //인증번호 확인
            axios.post("/api/auth/check/auth-key",{
                userId: id,
                authKey: authkey,
                email: `${email}@${adress}`,
                type: 1,
            },{
                headers: {
                    'Content-Type' : 'application/json',
                }
            }).then(res => {
                if(res.status == 200){
                    setIsEmail(true);
                    alert("인증이 완료되었습니다.");
                    return;
                }
                alert("인증번호가 틀렸습니다.");
                
            }).catch(e => {
                alert("에러남");
                console.log(e);
            })
        }
    }

    return(
        <div className={styles.flexbox}>
            <div className={styles.container}>
                <div className={styles.signup}>SIGN UP</div>
                <br/>
                <input type="text" placeholder="이름" className={styles['input-id']}
                onChange={(e)=> {setName(e.target.value);}}/>
                <p>
                    <input type="text" className={styles['input-id']}
                    placeholder="아이디" onChange={(e)=> {setId(e.target.value);}}/>
                    <button className={styles.button} onClick={idCheck}>중복확인</button>
                </p>
                <p>
                    <input type="text" placeholder="e-mail" className={styles['input-email']}
                    onChange={(e)=> {setEmail(e.target.value);}}/>
                    @
                    <input type="text" placeholder="직접입력" className={styles['input-email']}
                    onChange={(e)=> {setAdress(e.target.value);}}/>
                    <button className={styles.button} onClick={sendAuth}>전송</button>
                    { isSend ?
                        <p>
                            <input type="text" placeholder="인증번호" className={styles['input-auth-key']}
                            onChange={(e)=> {setAuthkey(e.target.value);}}/>
                            <button className={styles.button} onClick={checkAuth}>인증</button>
                        </p>
                        : null
                    }
                </p>
                <input type="password" placeholder="비밀번호" className={styles['input-password']}
                onChange={(e)=> {setPwd(e.target.value);}}/>
                <br/>
                <input type="password" placeholder="비밀번호 확인" className={styles['input-password']}
                onChange={(e)=> {setPwdConfirm(e.target.value);}}/>
                <br/>
                <button className={styles.button} onClick={signUp}>회원가입</button>
                <button className={styles.button} onClick={()=> navigate(-1)}>취소</button>
            </div>
        </div>
    )
}

export default SignUp;