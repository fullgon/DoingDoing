import axios from "axios";
import { useEffect, useState } from "react";
import styles from "./userInfo.module.css"


const UserInfo = () => {
    const [user, setUser] = useState({});

    const getUser = async () =>{
        const userId = localStorage.getItem('userId');
        const res = await axios.get(`/api/users/${userId}`,{
            headers:{
                'Authorization' : localStorage.getItem('accessToken'),
            }
        })
        if(res.status == 200){
            setUser(res.data);
        }
    }

    useEffect(() => {
        getUser();
    },[])

    return(
        <div className={styles.container}>
            <div className={styles['item-list']}>
                <div>이름 :</div>
                <div>아이디 :</div>
                <div>email :</div>
                <div>회사 :</div>
            </div>
            <div className={styles.item}>
                <div>{user.name}</div>
                <div>{user.userId}</div>
                <div>{user.email}</div>
                <div>{user.company ? user.company : null}</div>
            </div>
        </div>
    )
}

export default UserInfo;