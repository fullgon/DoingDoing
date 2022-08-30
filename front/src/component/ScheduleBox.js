//스크롤 되는 박스 하나 생성
//박스안에 Schedule 컴포넌트 생성

//배열에 일정 담아보고
//그걸 뿌려주고
//버튼추가로 배열에 일정 추가해서 일정이 추가되어 표시되도록
import styled from 'styled-components'
import Schedule from './Schedule';

const Schedules = styled.div`
display: flex;
flex-direction: column;
height: 70vh;
border: solid 1px;
overflow: auto;
&::-webkit-scrollbar {
    width: 8px;
    height: 8px;
    border-radius: 6px;
    background: rgba(255, 255, 255, 0.4);
  }
  &::-webkit-scrollbar-thumb {
    background: rgba(0, 0, 0, 0.2);
    border-radius: 6px;
  }
`;

const ScheduleBox = ({title}) =>{
    return(
        <Schedules>
            <Schedule/>
        </Schedules>
    )
}

export default ScheduleBox;