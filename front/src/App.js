import { Link } from 'react-router-dom';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1>Bookkeeper</h1>
      <nav style={{borderBottom:"solid 1px", paddingBottom: "1rem",}}>
        <Link to="/invo">invo</Link> |{" "}
        <Link to="/expen">expen</Link>
        
      </nav>
    </div>
  );
}

export default App;
