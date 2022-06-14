import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ChangePassword from "./ChangePassword";
import UserInfo from "./UserInfo";
import styles from "./users.module.css"


const Privacy = () =>{

    const navigate = useNavigate();
    const [isPasswordChange, setIsPasswordChange] = useState(false);

    const changePassword = (check) => {
        setIsPasswordChange(check);
    }

    const withdrawal = () =>{
        if(window.confirm("회원탈퇴를 진행하시겠습니까?")){
            axios.delete(`/api/users`, {
                headers:{
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            }).then((res)=>{
                if(res.status == 204){
                    alert("회원탈퇴 되었습니다");
                    navigate(`/`);
                }
            }).catch(error =>{
                alert("에러");
                console.log(error);
            })
        }
    }

    const goToBack = () =>{
        navigate(`/schedule`);
    }

    return(
        <div className={styles.flexbox}>
           {isPasswordChange? 
           <ChangePassword setIsPasswordChange={changePassword}/> 
           :
           <div className={styles.container}>
                <div className={styles.title}>개인정보</div>
                <UserInfo/>
                <div className={styles['button-group']}>
                    <div className={styles.button} onClick={() => setIsPasswordChange(true)}>비밀번호 변경</div>
                    <div className={styles.button} onClick={withdrawal}>회원탈퇴</div>
                </div>
                <div onClick={goToBack} className={styles['back-btn']}>뒤로가기</div>
            </div>}
            
            
            
        </div>
    )
}

export default Privacy;
