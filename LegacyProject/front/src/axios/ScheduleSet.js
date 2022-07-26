import axios from "axios";

export const getSchedules = async (isComplete, hasDeadLine) =>{
    try{
        const userId = localStorage.getItem('userId');
        const res = await axios.get(`/api/schedules/${userId}`,{
            headers:{
                'Authorization' : localStorage.getItem('accessToken'),
            },
            params:{
                isComplete,
                hasDeadLine,
            }
        });
        if(res.status == 200){
            return res.data;
        }       
    }catch(e){
        alert("에러");
        console.log("스케줄 불러오기 에러",e);
    }
}
