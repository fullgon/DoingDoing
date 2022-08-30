import styled from 'styled-components'

//체크박스 + 일정제목 + 쓰레기통표시
//체크박스 체크 시 일정제목 회색 및 가로줄
//일정에 마우스 올릴 시 오른쪽 끝에 휴지통 아이콘 생성

const Container = styled.div`
display: flex;
`;

const CheckBox = styled.input.attrs(props => ({
    type: "checkbox",
}))`

`;

const Schedule = () =>{
    return(
        <Container>
            <CheckBox/>
            안녕
            <CheckBox/>
        </Container>
    )
}

export default Schedule;