import {useState} from "react"
import axios from "axios";
import {useNavigate} from "react-router-dom"

function SignIn(){

    const navigate = useNavigate();

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");

    //로그인 버튼 클릭 시 계정 확인 후 로그인 처리
    const logIn = () =>{
        
        axios.post("api/auth/sign-in",{
            userID: id,
            password: pwd,
        },{
            headers:{
                'Content-Type' : 'application/json',
                //'Authorization' : authToken
            }
        }).then((res)=>{
            //jwt토큰 로컬저장소에 저장
            if(res.reuslt.result == 'ok'){
                //navigate(`/schedule`);
            }
            else{
                alert("아이디 혹은 비밀번호가 잘못되었습니다.");
            }
        }).catch(error =>{
            alert("에러남");
        })
        navigate(`/schedule`);
    }

    //회원가입 버튼 클릭 시 회원가입페이지로 이동
    const goToSignUp = () =>{
        navigate(`/auth/signUp`);
    }
    //useEffect();

    const goTOFindPassword = () =>{
        navigate(`/auth/findPassword`);
    }

    return(
        <div>
            <p>
                아이디: 
                <input type="text" placeholder="아이디"
                 onChange={(e)=> {setId(e.target.value);}}>
                 </input>
            </p>
            <p>
                비밀번호: 
                <input type="password" placeholder="비밀번호"
                 onChange={(e)=> {setPwd(e.target.value);}}>
                </input>
            </p>
            <input type="submit" onClick={logIn} value="로그인"></input>
            <button onClick={goToSignUp}>회원가입</button>
            <a onClick={goTOFindPassword}>비밀번호 찾기</a>
        </div>
    )
}

export default SignIn;