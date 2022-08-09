import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import StartPage from '../src/components/StartPage';
import LoginPage from '../src/components/LoginPage'; 
import Dashboard from './components/Dashboard';

function App() {
  return (
    <Router>
        <Routes>
          <Route path="/" element={<StartPage/>} />
          <Route path="/login" element={<LoginPage/>} />
          <Route path="/dashboard" element={<Dashboard/>} />
        </Routes>
    </Router>
  );
}

export default App;
