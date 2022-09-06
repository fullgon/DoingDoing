import { useState } from 'react';
import styled from 'styled-components'

const Container = styled.div`
display: flex;
`;

const ToggleHistory = () => {

    const changeIcon = (e) =>{
        if(e.target.innerText =="▶"){
            e.target.innerText = "▼";
            return
        }
        e.target.innerText = "▶";            
    }
    return(
        <Container onClick={changeIcon}>
            ▶
            
        </Container>
    )
}

export default ToggleHistory;