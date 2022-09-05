import { useState } from 'react';
import styled from 'styled-components'

//체크박스 + 일정제목 + 쓰레기통표시
//체크박스 체크 시 일정제목 회색 및 가로줄
//일정에 마우스 올릴 시 오른쪽 끝에 휴지통 아이콘 생성

//마우스 올렸을 때 실행할 아이콘 보이게 될 함수 생성

const Container = styled.div`
display: flex;
`;

const CheckBox = styled.input.attrs(props => ({
    type: "checkbox",
}))`

`;

const Schedule = () =>{
    const [visible, setVisible] = useState(false);
    const visibleIcon = () =>{
        setVisible(true);
    }
    const hiddenIcon = () =>{
        setVisible(false);
    }
    return(
        <Container onMouseOver={visibleIcon} onMouseOut={hiddenIcon}>
            <CheckBox/>
            안녕
            {visible? <CheckBox/> : null}
        </Container>
    )
}

export default Schedule;