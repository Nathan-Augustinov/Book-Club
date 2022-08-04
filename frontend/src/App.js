import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import StartPage from '../src/components/StartPage';
import LoginPage from '../src/components/LoginPage'; 

function App() {
  return (
    <Router>
        <Routes>
          <Route path="/" element={<StartPage/>} />
          <Route path="/login" element={<LoginPage/>} />
        </Routes>
    </Router>
  );
}

export default App;
