import axios from "axios";
import { useEffect, useState } from "react";


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
        <div>
            <div>개인정보</div>
            <div>이름 : {user.name}</div>
            <div>아이디 : {user.userId}</div>
            <div>email : {user.email}</div>
            <div>회사 :
                {user.company ? user.company : null}
            </div>
        </div>
    )
}

export default UserInfo;