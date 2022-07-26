import axios from "axios";

const isCompleteSchedule = async (isComplete, scheduleNo) => {
    try{
        const userId = localStorage.getItem('userId');
        const res = await axios.put(`/api/schedules/${userId}/${scheduleNo}`,
        { isComplete },
        {
            headers:{
                'Content-Type' : 'application/json',
                'Authorization' : localStorage.getItem('accessToken'),
            }
        })    
        if(res.status == 204){
            return true;
        }
    }catch(e){
        alert("에러");
        console.log("일정 완료 및 해제 에러", e);
    }
}

export default isCompleteSchedule;