import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';

function App() {

  const navigate = useNavigate();

  useEffect(()=>{
    navigate(`auth/signIn`);
  },[])

  return (
    <div className="App">
     App입니다.
    </div>
  );
}

export default App;
