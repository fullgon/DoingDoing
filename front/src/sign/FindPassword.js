import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom"
import styles from "./css/FindPassword.module.css"


const FindPassword = () =>{
    
    const navigate = useNavigate();

    const [isAuth, setIsAuth] = useState(false);
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
            axios.post("/api/auth/send/auth-key",{
                userId: id,
                email: `${email}@${adress}`,
                type: 0,
            },{
                headers:{
                    'Content-Type' : 'application/json',
                }
            }).then(res =>{
                console.log(res);
                //결과랑 메시지
                if(res.status == 204){
                    window.alert("인증번호 보냈음");
                    setIsSend(true);   
                }
                else{
                    window.alert("아이디 또는 이메일이 존재하지 않습니다.");
                }
                
                
            }).catch(error =>{
                window.alert("인증번호 보내기 실패");
            })
        }
    }

    const checkAuth = () =>{
        //인증번호 확인
        axios.post("/api/auth/check/auth-key",{
            userId: id,
            authKey: authkey,
            email: `${email}@${adress}`,
            type: 0,
        },{
            headers: {
                'Content-Type' : 'application/json',
            }
        }).then(res => {
            console.log(res);
            //jwt하고 result 받음
            //result.result가 'ok'일 때 
            if(res.status == 200){
                setIsAuth(true);
                setIsSend(false);
                localStorage.setItem('authToken', res.data.jwt);
            }
            else{
                window.alert("인증번호가 틀렸습니다");
            }
            
        })
    }

    //async/awit 형태
    /*
    const checkAuth = async () =>{
        //인증번호 확인
        try{
            const res = await axios.post("api/auth/checkAuthkey",{
                userId: id,
                authKey: authkey,
            },{
                headers: {
                    'Content-Type' : 'application/json',
                    //'Authorization' : authToken
                }
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
                const res = await axios.put("/api/auth/reset/password",{
                    userId: id,
                    password: pwd,
                },{
                    headers:{
                        'Content-Type' : 'application/json',
                        'Authorization' : localStorage.getItem('authToken'),
                    }
                });

                if(res.status == 204){
                    window.alert("비밀번호 변경 완료");
                    navigate(-1);
                }
            }

        }catch(e){
            alert("에러남");
        }
    }

    return(
        <div className={styles.flexbox}>
            <div className={styles.container}>
            
                {!isAuth ?
                //이메일 인증 양식 
                <div>
                    <div className={styles['find-password']}>비밀번호 찾기</div>
                    <br/>
                    <p>
                        <input type="text" placeholder="아이디" className={styles['input-id']}
                        onChange={(e)=> {setId(e.target.value);}}/>
                    </p>
                    <p>
                        <input type="text" placeholder="e-mail" className={styles['input-email']}
                        onChange={(e)=> {setEmail(e.target.value);}}/>
                        @
                        <input type="text" placeholder="직접입력" className={styles['input-email']}
                        onChange={(e)=> {setAdress(e.target.value);}}/> 
                        <button className={styles.button} onClick={sendAuth}>전송</button>
                    </p>
                    { isSend ?
                        <p>
                            <input type="text" placeholder="인증번호" className={styles['input-auth-key']}
                            onChange={(e)=> {setAuthkey(e.target.value);}}/>
                            <button className={styles.button} onClick={checkAuth}>인증</button>
                        </p>
                        : null
                    }
                </div>
                :
                //비밀번호 변경 양식
                <div className={styles['align-password']}>
                    <div className={styles['find-password']}>비밀번호 변경</div>
                    <br/>
                    <a className={styles['small-font']}>비밀번호</a>
                    <input type="password" className={styles['input-id']}
                    onChange={(e)=> {setPwd(e.target.value);}}/>
                    <a className={styles['small-font']}>비밀번호 확인</a>
                    <input type="password" className={styles['input-id']}
                    onChange={(e)=> {setPwdConfirm(e.target.value);}}/>
                    <button className={styles.button} onClick={resetPassword}>변경</button>
                </div>
                }
                
            </div>
        </div>
    )
}

export default FindPassword
