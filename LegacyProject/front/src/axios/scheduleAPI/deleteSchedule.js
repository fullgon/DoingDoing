import axios from "axios";

const deleteSchedule = async (scheduleNo) =>{
    try{
        const userId = localStorage.getItem('userId');
        const res = await axios.delete(`/api/schedules/${userId}/${scheduleNo}`,
        {
            headers:{
                'Authorization' : localStorage.getItem('accessToken'),
            }
        });

        if(res.status == 200){
            console.log('게시글 삭제 완료');
        }
    }catch(e){
        console.log("게시글 삭제 에러",e);
        alert("게시글 삭제 에러");
    }
}

export default deleteSchedule;