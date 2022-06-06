import {useState} from "react"
import axios from "axios";
import {useNavigate} from "react-router-dom"
import styles from "./css/SignIn.module.css"

function SignIn(){

    const navigate = useNavigate();

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");

    //로그인 버튼 클릭 시 계정 확인 후 로그인 처리
    const logIn = () =>{
        if(id == "" || pwd == ""){
            alert("아이디와 비밀번호를 입력해주십시오.");
            return;
        }
        axios.post("/api/auth/sign-in",{
            userId: id,
            password: pwd,
        },{
            headers:{
                'Content-Type' : 'application/json',
            }
        }).then((res)=>{
            
            if(res.status == 200){
                //jwt토큰 로컬저장소에 저장
                console.log(res);
                localStorage.setItem('accessToken', res.data.jwt);
                localStorage.setItem('userId', id);
                navigate(`/schedule`);
                return;
            }
            alert("아이디 혹은 비밀번호가 잘못되었습니다.");
            
        }).catch(error =>{
            alert(error.response.data.error);
            console.log(error);
        })
        
        
    }

    //회원가입 버튼 클릭 시 회원가입페이지로 이동
    const goToSignUp = () =>{
        navigate(`/auth/signUp`);
    }

    const goTOFindPassword = () =>{
        navigate(`/auth/findPassword`);
    }

    return(
        <div className={styles.flexbox}>
            <div className={styles.container}>
                <div className={styles.login}>LOGIN</div>
                <p>
                    <input type="text" placeholder="아이디"
                    onChange={(e)=> {setId(e.target.value);}}>
                    </input>
                </p>
                <p>
                    <input type="password" placeholder="비밀번호"
                    onChange={(e)=> {setPwd(e.target.value);}}>
                    </input>
                </p>
                <p className={styles['btn-flex']}>
                    <button onClick={logIn} className={styles.button}>로그인</button>
                    <button onClick={goToSignUp} className={styles.button}>회원가입</button>
                    <a onClick={goTOFindPassword} className={styles['find-password']}>비밀번호 찾기</a>
                </p>
            </div>
        </div>
    )
}

export default SignIn;